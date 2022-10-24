package com.example.coindeskexchange.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.coindeskexchange.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

object AppConst {
    const val BASE_URL = BuildConfig.API_URL

    fun convertDateFormat(updateTime: String): String {
        val df = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = df.parse(updateTime) as Date
        val newDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH)
        newDateFormat.timeZone = TimeZone.getDefault()
        return newDateFormat.format(date)
    }

}