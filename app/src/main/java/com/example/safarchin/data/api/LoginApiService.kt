package com.example.safarchin.data.api

import com.example.safarchin.data.model.PhoneRequest
import com.example.safarchin.data.model.SendCodeResponse
import com.example.safarchin.data.model.VerifyCodeResponse
import com.example.safarchin.data.model.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("send-code")
    suspend fun sendCode(@Body request: PhoneRequest): SendCodeResponse

    @POST("verify-code")
    suspend fun verifyCode(@Body request: VerifyRequest): VerifyCodeResponse
}
