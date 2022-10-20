package com.example.coindeskexchange.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Time(
    @Json(name = "updated")
    var updated: String,
    @Json(name = "updatedISO")
    var updatedISO: String,
    @Json(name = "updateduk")
    var updateduk: String
)