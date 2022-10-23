package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.local.EURPriceHistory
import com.example.coindeskexchange.data.local.GBPPriceHistory
import com.example.coindeskexchange.data.local.USDPriceHistory
import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.database.CoinDao
import com.example.coindeskexchange.network.CoinDeskApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CoinsRepositoryImpl(private val coinDeskApi: CoinDeskApi, private val coinDao: CoinDao) : CoinsRepository {
    override suspend fun getAllCoin():Coins {
        return coinDeskApi.getAllCoins()
    }

    override fun getUSDHistoryRate(): Flow<List<USDPriceHistory>> {
        return coinDao.getUsdRateHist()
    }

    override fun getEURHistoryRate(): Flow<List<EURPriceHistory>> {
        return coinDao.getEurRateHist()
    }

    override fun getGBPHistoryRate(): Flow<List<GBPPriceHistory>> {
        return coinDao.getGbpRateHist()
    }

    override suspend fun insertUSDRate(usd: USDPriceHistory) {
        coinDao.insertUSDHistory(usd)
    }

    override suspend fun insertEURRate(eur: EURPriceHistory) {
        coinDao.insertEURHistory(eur)
    }

    override suspend fun insertGBPRate(gbp: GBPPriceHistory) {
        coinDao.insertGBPHistory(gbp)
    }
}