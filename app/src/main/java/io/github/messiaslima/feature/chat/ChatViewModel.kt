package io.github.messiaslima.feature.chat

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.messiaslima.domain.Sender
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor() : ViewModel() {
    private val _sender = MutableStateFlow(Sender.MAIN_USER)
    val sender = _sender.asStateFlow()

    fun toggleSender() {
        _sender.value = when(_sender.value) {
            Sender.MAIN_USER -> Sender.OTHER_USER
            Sender.OTHER_USER -> Sender.MAIN_USER
        }
    }
}
