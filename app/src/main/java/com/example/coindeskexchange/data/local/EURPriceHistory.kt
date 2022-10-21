package com.example.coindeskexchange.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eur_price_history_table")
data class EURPriceHistory(
    @PrimaryKey(autoGenerate = false)
    val timeStamp:String,
    val price:String
)