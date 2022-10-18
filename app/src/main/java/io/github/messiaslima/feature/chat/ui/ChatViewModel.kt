package io.github.messiaslima.feature.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.feature.chat.usecase.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {
    private val _sender = MutableStateFlow(Sender.MAIN_USER)
    val sender = _sender.asStateFlow()

    val messages = chatRepository.findAdd()

    fun toggleSender() {
        _sender.value = when (_sender.value) {
            Sender.MAIN_USER -> Sender.OTHER_USER
            Sender.OTHER_USER -> Sender.MAIN_USER
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            chatRepository.sendMessage(text = message, sender = _sender.value)
        }
    }
}
