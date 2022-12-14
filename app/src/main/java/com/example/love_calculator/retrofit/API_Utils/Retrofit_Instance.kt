package com.example.love_calculator.retrofit.API_Utils

import com.example.love_calculator.retrofit.Base_End_URL.BaseUrl
import com.example.love_calculator.retrofit.Base_End_URL.EndUrl
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_Instance {

    val retrofitInstance: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BaseUrl.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    }

    val endUrl:EndUrl by lazy {
        retrofitInstance.build().create(EndUrl::class.java)
    }
}