package com.juliensacre.findcar.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juliensacre.findcar.internal.Converters

@Entity(tableName = "car")
data class Car(
    @PrimaryKey
    val id : Int,
    val make: String,
    val model: String,
    val year: Int,
    val picture: String,
    @TypeConverters(Converters::class)
    val equipments: List<String>
)