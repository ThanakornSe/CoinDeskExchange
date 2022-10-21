package com.example.coindeskexchange.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coindeskexchange.data.local.EURPriceHistory
import com.example.coindeskexchange.data.local.GBPPriceHistory
import com.example.coindeskexchange.data.local.USDPriceHistory

@Database(entities = [USDPriceHistory::class, GBPPriceHistory::class, EURPriceHistory::class], exportSchema = false, version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun CoinDao(): CoinDao
}