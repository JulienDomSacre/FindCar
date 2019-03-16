package com.juliensacre.findcar.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juliensacre.findcar.data.local.entity.Car
import com.juliensacre.findcar.internal.NoConnectivityException

class CarDataSourceImpl(
    private val carAPIService: CarAPIService
) : CarDataSource {
    // use this because liveData is not mutable but i doesn't want to share a mutable data
    private val _downloadedCars = MutableLiveData<List<Car>>()
    override val downloadedCars: LiveData<List<Car>>
        get() = _downloadedCars // automatic cast on LiveData
    override suspend fun fetchCarList() {

        try {
            val fetchedCarList = carAPIService.getCars().await()
            _downloadedCars.postValue(fetchedCarList)
        }catch (e: NoConnectivityException){
            Log.e("connectivity", "No internet connection",e)
        }
    }
}