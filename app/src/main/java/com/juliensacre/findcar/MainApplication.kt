package com.juliensacre.findcar

import android.app.Application
import com.juliensacre.findcar.internal.appModule
import org.koin.android.ext.android.startKoin

class MainApplication :Application(){
    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin(this, listOf(appModule))

    }
}