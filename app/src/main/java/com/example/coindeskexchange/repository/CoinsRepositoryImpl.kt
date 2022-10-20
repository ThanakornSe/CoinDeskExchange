package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.network.CoinDeskApi
import retrofit2.Response

class CoinsRepositoryImpl(private val coinDeskApi: CoinDeskApi) : CoinsRepository {
    override suspend fun getAllCoin():Coins {
        return coinDeskApi.getAllCoins()
    }
}