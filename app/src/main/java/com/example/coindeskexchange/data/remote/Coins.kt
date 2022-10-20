package com.example.coindeskexchange.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coins(
    @Json(name = "bpi")
    var bpi: Bpi,
    @Json(name = "chartName")
    var chartName: String,
    @Json(name = "disclaimer")
    var disclaimer: String,
    @Json(name = "time")
    var time: Time
)