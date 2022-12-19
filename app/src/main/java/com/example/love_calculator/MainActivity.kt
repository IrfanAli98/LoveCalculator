package com.example.love_calculator

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.love_calculator.databinding.ActivityMainBinding
import com.example.love_calculator.retrofit.Factory.LoveCalculatorFactory
import com.example.love_calculator.retrofit.Keys
import com.example.love_calculator.retrofit.RoomDatabase.Love_Database
import com.example.love_calculator.retrofit.ViewModels.LoveCalculatorViewModel
import com.example.love_calculator.retrofit.repository.LoveCalculatorRepository
import kotlin.time.Duration.Companion.seconds

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: LoveCalculatorFactory
    private lateinit var viewModel: LoveCalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val loveDao = Love_Database.getInstance(this).loveDao

        factory = LoveCalculatorFactory(LoveCalculatorRepository(loveDao))
        viewModel = ViewModelProvider(this, factory)[LoveCalculatorViewModel::class.java]

        dataBinding.btnCalculate.setOnClickListener {
            // TODO: for Progress Bar
            viewModel.isLoading.observe(this, Observer {
                if (it) {
                    dataBinding.btnCalculate.text = ""
                    dataBinding.tvProgress.visibility = View.VISIBLE
                    dataBinding.tvProgress.progress.seconds.div(4)
                }
            })

            viewModel.getPercentage(
                dataBinding.etYourName.text.toString(),
                dataBinding.etPartnerName.text.toString()
            )
                .observe(this, Observer {
                    it

                    if (it != null) {
                        val bundle = Bundle()
                        bundle.putString(Keys.Your_Name, it.yourName)
                        bundle.putString(Keys.Partner_Name, it.partnerName)
                        bundle.putString(Keys.Percentage, it.percentage.toString())
                        bundle.putString(Keys.Result, it.result)
                        val intent = Intent(applicationContext, MainActivity2::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, "Something is wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.it_history->{
                val intent = Intent(this@MainActivity, HistoryActivity::class.java)
                startActivity(intent)
                finish()

            }
            R.id.it_love_quote->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        this.finish()
        super.onStop()
    }
}
