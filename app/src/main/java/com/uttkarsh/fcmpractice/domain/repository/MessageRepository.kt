package com.uttkarsh.fcmpractice.domain.repository

import com.uttkarsh.fcmpractice.data.FcmApiService
import com.uttkarsh.fcmpractice.domain.model.MessageDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MessageRepository {

    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val fcmApi: FcmApiService = Retrofit.Builder()
        .baseUrl("http://172.20.10.11:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create()


    suspend fun sendMessage(message: MessageDTO){
        return fcmApi.sendMessageToBackend(message)
    }
}