package com.uttkarsh.fcmpractice.data

import com.uttkarsh.fcmpractice.domain.model.MessageDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmApiService {

    @POST("/message")
    suspend fun sendMessageToBackend(
        @Body messageDTO: MessageDTO
    )

}