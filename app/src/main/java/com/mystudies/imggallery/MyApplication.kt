package com.mystudies.imggallery

import android.app.Application
import com.mystudies.imggallery.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(applicationModules)
        }
    }

}