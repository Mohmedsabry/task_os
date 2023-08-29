package com.example.graduationproject.data.presestance

import android.content.Context
import com.example.graduationproject.data.model.Currency
import com.example.graduationproject.data.model.CurrencyRoomDBItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SharedObject {
    lateinit var appContext: Context
        private set
    lateinit var countriesList: List<Currency>
        private set
    fun initContext(context: Context) {
        appContext = context
    }

    fun initList(list: List<Currency>) {
        countriesList = list
    }

    fun getInstanceOfRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://ec2-18-134-206-213.eu-west-2.compute.amazonaws.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}
