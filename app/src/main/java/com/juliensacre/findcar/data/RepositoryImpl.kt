package com.juliensacre.findcar.data

import androidx.lifecycle.LiveData
import com.juliensacre.findcar.data.local.CarDao
import com.juliensacre.findcar.data.local.entity.Car
import com.juliensacre.findcar.data.remote.CarDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val carDataSource: CarDataSource,
    private val carDao: CarDao
) : Repository {
    init {
        //observeForever: because the repository doesn't have a lifecycle,
        // and when this repo being destroyed because the whole app is destroyed, the lifecycle doesn't
        // have a role in this observer
        carDataSource.downloadedCars.observeForever { newcar ->
            persistFetchedCars(newcar)
        }
    }
    override suspend fun getCars(): LiveData<List<Car>> {
        //use withContext because is return a result, GolbalScope doesn't
        return withContext(Dispatchers.IO){
            return@withContext carDao.getCars()
        }
    }

    private fun persistFetchedCars(fetchedCar: List<Car>){
        //use IO for don't depend of lifecycle of the app
        GlobalScope.launch(Dispatchers.IO) {
            carDao.upsert(fetchedCar)
        }
    }

    //doesn't want return because the result of this call is directly send to the observeForever
    private suspend fun initCarData(){
        carDataSource.fetchCarList()
    }
}