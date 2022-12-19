package com.example.love_calculator.retrofit.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoveTable(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "Id")
    var id: Int,

    @ColumnInfo(name = "Your_Name")
    var Your_Name: String,

    @ColumnInfo(name = " Partner_Name")
    var Partner_Name: String,

    @ColumnInfo(name = "Percentage")
    var Percentage: String,

    @ColumnInfo(name = "Result")
    val Result: String
)
