package com.example.coindeskexchange.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coindeskexchange.data.local.PriceHistory

@Database(entities = [PriceHistory::class], exportSchema = false, version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun CoinDao(): CoinDao
}