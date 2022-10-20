package com.example.coindeskexchange

import android.app.Application
import com.example.coindeskexchange.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(networkModule)
        }
    }
}