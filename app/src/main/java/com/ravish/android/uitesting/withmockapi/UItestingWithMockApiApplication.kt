package com.ravish.android.uitesting.withmockapi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UItestingWithMockApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}