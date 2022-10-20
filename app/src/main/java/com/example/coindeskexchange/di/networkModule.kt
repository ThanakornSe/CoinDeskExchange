package com.example.coindeskexchange.di

import com.example.coindeskexchange.network.CoinDeskApi
import com.example.coindeskexchange.utils.AppConst
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration

val networkModule = module {
    factory { providesOkHttpClient() }
    factory { provideCoinDeskApi(get()) }
    single { providesRetrofit(get()) }
}

fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(AppConst.BASE_URL)
    .build()

fun providesOkHttpClient(): OkHttpClient {
    val duration = Duration.ofSeconds(30)
    return OkHttpClient.Builder()
        .connectTimeout(duration)
        .readTimeout(duration)
        .writeTimeout(duration)
        .build()
}

fun provideCoinDeskApi(retrofit: Retrofit): CoinDeskApi = retrofit.create(CoinDeskApi::class.java)
