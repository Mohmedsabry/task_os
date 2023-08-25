package com.example.graduationproject.data.presestance

import android.app.Application
import androidx.compose.runtime.rememberCoroutineScope

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        SharedObject.initContext(this)
    }
}