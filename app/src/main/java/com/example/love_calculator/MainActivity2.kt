package com.example.love_calculator

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.love_calculator.databinding.ActivityMain2Binding
import com.example.love_calculator.retrofit.Factory.LoveCalculatorFactory
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.key.LoveData
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMain2Binding
    private lateinit var factory: LoveCalculatorFactory
    private lateinit var viewModel: LoveCalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        factory = LoveCalculatorFactory(LoveCalculatorRepository())
        viewModel = ViewModelProvider(this, factory)[LoveCalculatorViewModel::class.java]

        val dataInString = intent.getStringExtra("jsonData")
        val lovedata = Gson().fromJson<LoveData>(dataInString, LoveData::class.java)

        var progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")
        viewModel.isLoading.observe(this, Observer {
            if (it) progressDialog.show()
            else progressDialog.hide()
        })

        viewModel.getPercentage(lovedata.yourName, lovedata.partnerName)
            .observe(this, Observer {
                it
                dataBinding.tvAllDetails.text =
                    "Your Name: " + it.yourName + "\n" +
                            "Partner Name: " + it.partnerName + "\n" +
                            "Love Percentage: " + it.percentage + "\n" +
                            "Result: " + it.result
            })
    }
}