package com.example.love_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.love_calculator.databinding.ActivityMain2Binding
import com.example.love_calculator.retrofit.Factory.LoveCalculatorFactory
import com.example.love_calculator.retrofit.Keys
import com.example.love_calculator.retrofit.RoomDatabase.LoveDao
import com.example.love_calculator.retrofit.RoomDatabase.LoveTable
import com.example.love_calculator.retrofit.RoomDatabase.Love_Database
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMain2Binding
    private lateinit var factory: LoveCalculatorFactory
    private lateinit var viewModel: LoveCalculatorViewModel
    private lateinit var loveTable: LoveTable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        val loveDao =Love_Database.getInstance(this).loveDao
        factory = LoveCalculatorFactory(LoveCalculatorRepository(loveDao))
        viewModel = ViewModelProvider(this, factory)[LoveCalculatorViewModel::class.java]

//        loveTable= Gson().fromJson(intent.getStringExtra("loveList"), LoveTable::class.java)
//
//        dataBinding.tvYourName.text = loveTable.Your_Name
//        dataBinding.tvPartnerName.text = loveTable.Partner_Name
//        dataBinding.tvPercentage.text= loveTable.Percentage
//        dataBinding.tvResult.text = loveTable.Result

        val bundle = intent.extras

        dataBinding.tvYourName.text = bundle?.getString(Keys.Your_Name)
        dataBinding.tvPartnerName.text =bundle?.getString(Keys.Partner_Name)
        dataBinding.tvPercentage.text =bundle?.getString(Keys.Percentage)+" %"
        dataBinding.tvResult.text =bundle?.getString(Keys.Result)

        val loveTable = LoveTable(0, dataBinding.tvYourName.text.toString(),
            dataBinding.tvPartnerName.text.toString(), dataBinding.tvPercentage.text.toString(),
            dataBinding.tvResult.text.toString() )

        if(dataBinding.tvPercentage.text.toString() < 50.toString()){
            dataBinding.btnSave.text = "Retry"

            if(dataBinding.btnSave.text == "Retry" ){
                dataBinding.btnSave.setOnClickListener{
                    onBackPressed()
                    finish()
                }
            }
        }else{
            dataBinding.btnSave.setOnClickListener {
                viewModel.saveLoveData(loveTable)
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this@MainActivity2, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}