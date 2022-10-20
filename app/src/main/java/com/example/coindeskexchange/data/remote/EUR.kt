package com.example.coindeskexchange.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EUR(
    @Json(name = "code")
    var code: String,
    @Json(name = "description")
    var description: String,
    @Json(name = "rate")
    var rate: String,
    @Json(name = "rate_float")
    var rateFloat: Double,
    @Json(name = "symbol")
    var symbol: String
)