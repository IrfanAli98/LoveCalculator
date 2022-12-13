package com.example.love_calculator.retrofit.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.love_calculator.retrofit.Models.Percentage
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository

class LoveCalculatorViewModel(val repository: LoveCalculatorRepository):ViewModel() {
    var percent =MutableLiveData<Percentage>()
    val isLoading: MutableLiveData<Boolean> = repository.isLoading.apply {
        postValue(true)
    }

    fun getPercentage(fname: String, sname: String): LiveData<Percentage>{
        percent = repository.getPercentage(fname, sname)
        return percent
    }
}