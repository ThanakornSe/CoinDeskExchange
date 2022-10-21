package com.example.coindeskexchange.data.ui

data class Currency(
    var code: String,
    var description: String,
    var rate: String,
    var rateFloat: Double,
    var symbol: String
)
