package com.example.coindeskexchange.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coindeskexchange.data.local.PriceHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceHistory(priceHistory: PriceHistory)

    @Query("SELECT timeStamp, RateUSD FROM price_history_table ORDER BY timeStamp DESC")
    fun getUsdRateHist(): Flow<List<PriceHistory>>

    @Query("SELECT timeStamp, RateEUR FROM price_history_table ORDER BY timeStamp DESC")
    fun getEurRateHist(): Flow<List<PriceHistory>>

    @Query("SELECT timeStamp, RateGBP FROM price_history_table ORDER BY timeStamp DESC")
    fun getGbpRateHist(): Flow<List<PriceHistory>>
}