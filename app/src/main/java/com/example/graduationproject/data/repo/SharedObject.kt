package com.example.graduationproject.data.repo

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SharedObject {
    lateinit var appContext: Context
        private set

    fun initContext(context: Context) {
        appContext = context
    }

    fun getInstanceOfRetrofit():Retrofit {
      return  Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}