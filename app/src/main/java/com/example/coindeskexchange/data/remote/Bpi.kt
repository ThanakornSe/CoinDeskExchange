package com.example.coindeskexchange.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bpi(
    @Json(name = "EUR")
    var eUR: EUR,
    @Json(name = "GBP")
    var gBP: GBP,
    @Json(name = "USD")
    var uSD: USD
)