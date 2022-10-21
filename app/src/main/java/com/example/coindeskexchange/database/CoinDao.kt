package com.example.coindeskexchange.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coindeskexchange.data.local.EURPriceHistory
import com.example.coindeskexchange.data.local.GBPPriceHistory
import com.example.coindeskexchange.data.local.USDPriceHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUSDHistory(usdPriceHistory: USDPriceHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEURHistory(eurPriceHistory: EURPriceHistory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGBPHistory(gbpPriceHistory: GBPPriceHistory)

    @Query("SELECT * FROM usd_price_history_table ORDER BY timeStamp DESC")
    fun getUsdRateHist(): Flow<List<USDPriceHistory>>

    @Query("SELECT * FROM eur_price_history_table ORDER BY timeStamp DESC")
    fun getEurRateHist(): Flow<List<EURPriceHistory>>

    @Query("SELECT * FROM gbp_price_history_table ORDER BY timeStamp DESC")
    fun getGbpRateHist(): Flow<List<GBPPriceHistory>>
}