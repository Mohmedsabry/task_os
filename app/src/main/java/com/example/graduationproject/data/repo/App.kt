package com.example.graduationproject.data.repo

import android.app.Application

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        SharedObject.initContext(this)
    }
}