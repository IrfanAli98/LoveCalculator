package com.example.love_calculator.retrofit

import com.example.love_calculator.retrofit.RoomDatabase.LoveTable

interface OnItemClickListener {
    fun onItemClick(loveTable: LoveTable, position: Int )
}