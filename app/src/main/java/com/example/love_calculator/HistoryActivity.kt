package com.example.love_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.love_calculator.databinding.ActivityHistoryBinding
import com.example.love_calculator.retrofit.Factory.LoveCalculatorFactory
import com.example.love_calculator.retrofit.Keys
import com.example.love_calculator.retrofit.OnItemClickListener
import com.example.love_calculator.retrofit.RoomDatabase.LoveTable
import com.example.love_calculator.retrofit.RoomDatabase.Love_Database
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.adapters.HistoryRecyclerViewAdapter
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import com.google.gson.Gson

class HistoryActivity : AppCompatActivity() {
    private lateinit var  dataBinding:ActivityHistoryBinding
    private lateinit var viewModel : LoveCalculatorViewModel
    private lateinit var factory: LoveCalculatorFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_history)

        dataBinding.recyclerViewHistory.layoutManager = LinearLayoutManager(this)
        val loveDao = Love_Database.getInstance(this).loveDao

        factory = LoveCalculatorFactory(LoveCalculatorRepository(loveDao))
        viewModel = ViewModelProvider(this, factory)[LoveCalculatorViewModel::class.java]
        viewModel.love_List.observe(this, Observer {
            val adapter = HistoryRecyclerViewAdapter(it, object:OnItemClickListener{
                override fun onItemClick(loveTable: LoveTable, position: Int) {
//                    val intent = Intent(this@HistoryActivity, MainActivity2::class.java)
//                    intent.putExtra("loveList", Gson().toJson(loveTable))
//                    startActivity(intent)
                    Toast.makeText(applicationContext, "This action is not Allowed", Toast.LENGTH_SHORT).show()
                }
            })
            dataBinding.recyclerViewHistory.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.it_delete->{
                viewModel.deleteAllLoveData()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}