package com.yolda

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YoldaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}