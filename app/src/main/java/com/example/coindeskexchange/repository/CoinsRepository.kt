package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.local.PriceHistory
import com.example.coindeskexchange.data.remote.Coins
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    suspend fun getAllCoin(): Coins

    fun getUSDHistoryRate(): Flow<List<PriceHistory>>

    fun getEURHistoryRate(): Flow<List<PriceHistory>>

    fun getGBPHistoryRate(): Flow<List<PriceHistory>>

    suspend fun insertPriceHistoryRate(gbp: PriceHistory)

    suspend fun deleteHistory()
}