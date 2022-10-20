package com.example.coindeskexchange.repository

import com.example.coindeskexchange.data.remote.Coins

interface CoinsRepository {
    suspend fun getAllCoin():Coins
}