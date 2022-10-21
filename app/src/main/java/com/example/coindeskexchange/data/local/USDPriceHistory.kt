package com.example.coindeskexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usd_price_history_table")
data class USDPriceHistory(
    @PrimaryKey(autoGenerate = false)
    val timeStamp:String,
    val price:String
)
