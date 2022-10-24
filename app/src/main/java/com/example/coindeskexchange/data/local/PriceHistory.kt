package com.example.coindeskexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "price_history_table")
data class PriceHistory(
    @PrimaryKey(autoGenerate = false)
    val timeStamp:String,
    val RateUSD:String?,
    val RateEUR:String?,
    val RateGBP:String?
)
