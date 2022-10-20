package com.example.coindeskexchange.network

import com.example.coindeskexchange.data.remote.Coins
import retrofit2.http.GET

interface CoinDeskApi {
    @GET("currentprice.json")
    suspend fun getAllCoins(): Coins
}