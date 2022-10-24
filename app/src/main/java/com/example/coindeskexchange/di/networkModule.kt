package com.example.coindeskexchange.di

import com.example.coindeskexchange.network.CoinDeskApi
import com.example.coindeskexchange.utils.AppConst
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration

val networkModule = module {
    factory { providesOkHttpClient() }
    single { provideCoinDeskApi(get()) }
    factory { provideMoshiBuilder() }
    single { providesRetrofit(get(),get()) }
}

fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(AppConst.BASE_URL)
    .build()

fun provideMoshiBuilder(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
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
