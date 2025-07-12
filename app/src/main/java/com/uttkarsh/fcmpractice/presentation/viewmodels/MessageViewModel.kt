package com.uttkarsh.fcmpractice.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import com.uttkarsh.fcmpractice.util.MessageState
import com.uttkarsh.fcmpractice.data.FcmApiService
import com.uttkarsh.fcmpractice.domain.model.MessageDTO
import com.uttkarsh.fcmpractice.domain.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.math.log

class MessageViewModel: ViewModel() {

    val repository: MessageRepository = MessageRepository()

    private val _state = MutableStateFlow<MessageState>(MessageState.Idle)
    val state: StateFlow<MessageState> = _state

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    fun onTitleChanged(newTitle: String) {
        title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        description.value = newDescription
    }

    fun sendMessage(){
        viewModelScope.launch {
            try {
                Log.d("Token", FirebaseMessaging.getInstance().token.await())
                val messageDto = MessageDTO(
                    ids = listOf(1L, 2L),   //hardcoding it here(created users using postman, by getting token from logs at above line)
                    title = title.value,
                    description = description.value
                )
                repository.sendMessage(messageDto)

                _state.value = MessageState.success

            }catch (e: Exception){
                _state.value = MessageState.Error(e.message.toString())
            }
        }
    }
}