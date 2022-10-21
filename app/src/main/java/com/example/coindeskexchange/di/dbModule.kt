package com.example.coindeskexchange.di

import android.content.Context
import androidx.room.Room
import com.example.coindeskexchange.database.CoinDao
import com.example.coindeskexchange.database.CoinDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { provideDataBase(androidContext()) }
}

fun provideDataBase(context: Context): CoinDao =
    Room.databaseBuilder(context, CoinDatabase::class.java, "coin_history.db").build().CoinDao()