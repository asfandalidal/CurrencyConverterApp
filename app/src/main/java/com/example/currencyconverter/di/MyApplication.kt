package com.example.currencyconverter.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@MyApplication)
            // modules
            modules(appModule)
        }
    }
}
