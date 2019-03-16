package com.juliensacre.findcar

import android.app.Application
import com.juliensacre.findcar.internal.appModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MainApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        //init log timber
        Timber.plant(Timber.DebugTree())

        // start Koin context
        startKoin(this, listOf(appModule))

    }
}