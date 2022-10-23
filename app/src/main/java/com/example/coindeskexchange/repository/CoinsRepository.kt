package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.local.EURPriceHistory
import com.example.coindeskexchange.data.local.GBPPriceHistory
import com.example.coindeskexchange.data.local.USDPriceHistory
import com.example.coindeskexchange.data.remote.Coins
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    suspend fun getAllCoin(): Coins

    fun getUSDHistoryRate(): Flow<List<USDPriceHistory>>

    fun getEURHistoryRate(): Flow<List<EURPriceHistory>>

    fun getGBPHistoryRate(): Flow<List<GBPPriceHistory>>

    suspend fun insertUSDRate(usd: USDPriceHistory)

    suspend fun insertEURRate(eur: EURPriceHistory)

    suspend fun insertGBPRate(gbp: GBPPriceHistory)
}