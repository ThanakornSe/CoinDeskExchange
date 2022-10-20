package com.example.coindeskexchange.network

import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.utils.AppConst
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface CoinDeskApi {
    @GET("currentprice.json")
    suspend fun getAllCoins(): Coins
}