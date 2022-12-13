package com.example.love_calculator.retrofit.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository

class LoveCalculatorFactory(val repository: LoveCalculatorRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoveCalculatorViewModel::class.java)){
            return LoveCalculatorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}