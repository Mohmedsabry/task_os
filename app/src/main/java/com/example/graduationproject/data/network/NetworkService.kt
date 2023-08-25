package com.example.graduationproject.data.network

import com.example.graduationproject.data.model.ConvertModel
import com.example.graduationproject.data.model.CurrencyApiItem
import retrofit2.http.GET

interface NetworkService {
    @GET("")
    suspend fun convert(): ConvertModel
    suspend fun compare()

    @GET("countries")
    suspend fun getList(): List<CurrencyApiItem>
}