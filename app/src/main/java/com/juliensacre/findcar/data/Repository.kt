package com.juliensacre.findcar.data

import androidx.lifecycle.LiveData
import com.juliensacre.findcar.data.local.entity.Car

interface Repository {
    suspend fun getCars() : LiveData<List<Car>>
}