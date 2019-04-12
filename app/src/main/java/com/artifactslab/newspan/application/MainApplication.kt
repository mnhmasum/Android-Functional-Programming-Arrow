package com.artifactslab.newspan.retrofit

import android.app.Application

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRetrofit(this)
    }
}
