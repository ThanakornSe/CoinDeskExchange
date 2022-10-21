package com.example.coindeskexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gbp_price_history_table")
data class GBPPriceHistory(
    @PrimaryKey(autoGenerate = false)
    val timeStamp:String,
    val price:String
)
