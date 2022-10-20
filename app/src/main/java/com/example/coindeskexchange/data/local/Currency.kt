package com.example.coindeskexchange.data.local

import com.squareup.moshi.Json

data class Currency(
    var code: String,
    var description: String,
    var rate: String,
    var rateFloat: Double,
    var symbol: String
)
