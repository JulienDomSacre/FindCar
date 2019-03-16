package com.juliensacre.findcar.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.juliensacre.findcar.data.local.entity.Car
import com.juliensacre.findcar.internal.Converters

@Database(
    entities = [Car::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CarDatabase: RoomDatabase(){
    abstract fun carDao() : CarDao

    //Singleton
    companion object {
        //@Volatile: writes to this field are immediately made visible to other threads.
        @Volatile private var instance: CarDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, CarDatabase::class.java,"car.db")
                .build()
    }
}