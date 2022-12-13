package com.example.love_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.love_calculator.databinding.ActivityMainBinding
import com.example.love_calculator.retrofit.Factory.LoveCalculatorFactory
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.key.LoveData
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: LoveCalculatorFactory
    private lateinit var viewModel: LoveCalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        factory = LoveCalculatorFactory(LoveCalculatorRepository())
        viewModel = ViewModelProvider(this, factory)[LoveCalculatorViewModel::class.java]

        dataBinding.btnCalculate.setOnClickListener {
            intent = Intent(this@MainActivity, MainActivity2::class.java)
            val loveData: LoveData = LoveData(
                dataBinding.etYourName.text.toString(),
                dataBinding.etPartnerName.text.toString()
            )

            intent.putExtra("jsonData", Gson().toJson(loveData))
            startActivity(intent)
        }
    }
}