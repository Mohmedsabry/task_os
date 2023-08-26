package com.example.graduationproject.data.network

import com.example.graduationproject.data.model.CompareModelGet
import com.example.graduationproject.data.model.CompareModelPost
import com.example.graduationproject.data.model.ConvertModel
import com.example.graduationproject.data.model.CurrencyApiList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path


interface NetworkService {
    @GET("currency/convert/{source}/{target}/{amount}")
    suspend fun convert(
        @Path("source") source: String,
        @Path("target") target: String,
        @Path("amount") amount: Double,
    ): ConvertModel

    @POST("currency/compare")
    suspend fun compare(@Body compareModelPost: CompareModelPost):CompareModelGet

    @GET("currency")
    suspend fun getList(): CurrencyApiList



}