package com.example.love_calculator.retrofit.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.love_calculator.retrofit.API_Utils.Retrofit_Instance
import com.example.love_calculator.retrofit.Models.Percentage
import com.example.love_calculator.retrofit.RoomDatabase.LoveDao
import com.example.love_calculator.retrofit.RoomDatabase.LoveTable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoveCalculatorRepository(val loveDao: LoveDao) {
    val percent = MutableLiveData<Percentage>()
    var isLoading = MutableLiveData<Boolean>()


    fun getPercentage(fname:String, sname:String):MutableLiveData<Percentage>{
        val call = Retrofit_Instance.endUrl.getPercentage(fname, sname)
        call.enqueue(object : Callback<Percentage>{
            override fun onResponse(call: Call<Percentage>, response: Response<Percentage>) {
                if(response.isSuccessful){
                    isLoading.postValue(false)
                    val body  = response.body()
                    percent.value = body!!

                }else{
                    isLoading.postValue(false)
                    Log.d("Error", "onResponse: " + response.message())
                }
            }

            override fun onFailure(call: Call<Percentage>, t: Throwable) {
                Log.d("FAIL", "onFailure: " + t.message)
            }
        })
        return percent
    }

    suspend fun saveLoveData(loveTable: LoveTable){
        loveDao.insertLoveData(loveTable)
    }

    suspend fun updateLoveData(loveTable: LoveTable){
        loveDao.updateLoveData(loveTable)
    }

    suspend fun deleteLoveData(loveTable: LoveTable){
        loveDao.deleteLoveData(loveTable)
    }

    suspend fun deleteAllLoveData(){
        loveDao.deleteAllLoveData()
    }

    val love_List = loveDao.getLoveData()
}