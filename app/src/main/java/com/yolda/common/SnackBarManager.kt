package com.yolda.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

data class Message(val id: Long, val message: String)

object SnackBarManager {

    private val _message: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val message: StateFlow<List<Message>> get() = _message.asStateFlow()

    fun showMessage(messageText: String) {
        _message.update { currentMessage ->
            currentMessage + Message(
                id = UUID.randomUUID().mostSignificantBits,
                message = messageText
            )
        }
    }

    fun setMessageShown(messageId: Long) {
        _message.update { currentMessage ->
            currentMessage.filterNot { it.id == messageId }
        }
    }
}