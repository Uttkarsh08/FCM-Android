package com.uttkarsh.fcmpractice.util

sealed class MessageState {
    object Idle: MessageState()
    object Loading: MessageState()
    object success: MessageState()
    data class Error(val message: String): MessageState()

}