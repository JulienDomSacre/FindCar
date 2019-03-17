package com.juliensacre.findcar.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.juliensacre.findcar.internal.Converters
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "car")
data class Car(
    val make: String,
    val model: String,
    val year: Int,
    @PrimaryKey //not a good idea, but the best solution now
    val picture: String,
    @TypeConverters(Converters::class)
    val equipments: List<String>

) : Parcelable