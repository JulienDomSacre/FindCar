package com.juliensacre.findcar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juliensacre.findcar.internal.Converters

@Entity(tableName = "car")
data class Car(
    val make: String,
    val model: String,
    val year: Int,
    @PrimaryKey //not a good idea, but the best solution now
    val picture: String,
    @TypeConverters(Converters::class)
    val equipments: List<String>

)