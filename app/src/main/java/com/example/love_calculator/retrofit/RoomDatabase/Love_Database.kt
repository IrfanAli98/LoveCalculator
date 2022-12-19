package com.example.love_calculator.retrofit.RoomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoveTable::class], version = 1)
abstract class Love_Database: RoomDatabase() {
     abstract val loveDao: LoveDao

     companion object {
         @Volatile
         private var INSTANCE: Love_Database? = null
         fun getInstance(context: Context): Love_Database {
             var instance = INSTANCE
             synchronized(this) {
                 if (instance == null) {
                     instance =
                         Room.databaseBuilder(context, Love_Database::class.java, "Love_DATABASE")
                             .build()
                     INSTANCE = instance
                 }
             }
             return instance!!
         }
     }
}
