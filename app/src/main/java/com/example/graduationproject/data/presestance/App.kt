package com.example.graduationproject.data.presestance

import android.app.Application

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        SharedObject.initContext(this)
    }
}