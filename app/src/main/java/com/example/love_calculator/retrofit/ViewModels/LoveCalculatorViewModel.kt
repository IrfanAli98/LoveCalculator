package com.example.love_calculator.retrofit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.love_calculator.retrofit.Models.Percentage
import com.example.love_calculator.retrofit.RoomDatabase.LoveTable
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import kotlinx.coroutines.launch

class LoveCalculatorViewModel(val repository: LoveCalculatorRepository):ViewModel() {
    var percent =MutableLiveData<Percentage>()
    val isLoading: MutableLiveData<Boolean> = repository.isLoading.apply {
        postValue(true)
    }

    fun getPercentage(fname: String, sname: String): LiveData<Percentage>{
        percent = repository.getPercentage(fname, sname)
        return percent
    }

    fun saveLoveData(loveTable: LoveTable){
        viewModelScope.launch {
            repository.saveLoveData(loveTable)
        }
    }

    val love_List = repository.love_List

    fun updateLoveData(loveTable: LoveTable){
        viewModelScope.launch {
            repository.updateLoveData(loveTable)
        }
    }

    fun deleteLoveData(loveTable: LoveTable){
        viewModelScope.launch {
            repository.deleteLoveData(loveTable)
        }
    }

    fun deleteAllLoveData(){
        viewModelScope.launch {
            repository.deleteAllLoveData()
        }
    }
}