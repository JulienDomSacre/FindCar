package com.juliensacre.findcar.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juliensacre.findcar.data.local.entity.Car

@Dao
interface CarDao {
    //update and insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(car : List<Car>)

    @Query("select * from car")
    fun getCars() : LiveData<List<Car>>

}