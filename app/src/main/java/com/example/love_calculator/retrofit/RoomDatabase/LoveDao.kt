package com.example.love_calculator.retrofit.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LoveDao {
    @Insert
    suspend fun insertLoveData(vararg loveTable: LoveTable)

    @Query("SELECT * FROM LoveTable")
    fun getLoveData():LiveData<List<LoveTable>>

    @Delete
    suspend fun deleteLoveData(loveTable: LoveTable)

    @Update
    suspend fun updateLoveData(loveTable: LoveTable)

    @Query("DELETE FROM LoveTable")
    suspend fun deleteAllLoveData()

}