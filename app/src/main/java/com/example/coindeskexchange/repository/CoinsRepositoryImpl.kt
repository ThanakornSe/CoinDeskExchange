package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.local.PriceHistory
import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.database.CoinDao
import com.example.coindeskexchange.network.CoinDeskApi
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl(private val coinDeskApi: CoinDeskApi, private val coinDao: CoinDao) : CoinsRepository {
    override suspend fun getAllCoin():Coins {
        return coinDeskApi.getAllCoins()
    }

    override fun getUSDHistoryRate(): Flow<List<PriceHistory>> {
        return coinDao.getUsdRateHist()
    }

    override fun getEURHistoryRate(): Flow<List<PriceHistory>> {
        return coinDao.getEurRateHist()
    }

    override fun getGBPHistoryRate(): Flow<List<PriceHistory>> {
        return coinDao.getGbpRateHist()
    }

    override suspend fun insertPriceHistoryRate(priceHistory: PriceHistory) {
        coinDao.insertPriceHistory(priceHistory)
    }

    override suspend fun deleteHistory() {
        coinDao.deleteHistory()
    }

}