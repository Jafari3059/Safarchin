package com.example.safarchin.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
object RetrofitInstance {

    private const val BASE_URL = "https://mock.apidog.com/m1/928817-911595-default/"

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val api: LoginApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(LoginApiService::class.java)
    }
}






