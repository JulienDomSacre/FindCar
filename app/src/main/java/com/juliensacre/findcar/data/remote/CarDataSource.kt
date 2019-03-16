package com.juliensacre.findcar.data.remote

import androidx.lifecycle.LiveData
import com.juliensacre.findcar.data.local.entity.Car

interface CarDataSource {

    val downloadedCars: LiveData<List<Car>>

    suspend fun fetchCarList()
}