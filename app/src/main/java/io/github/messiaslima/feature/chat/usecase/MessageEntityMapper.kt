package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import java.util.*
import javax.inject.Inject

class MessageEntityMapper @Inject constructor() {
    fun map(text: String, sender: Sender) = MessageEntity(
        id = null,
        text = text,
        sender = sender,
        date = Date()
    )
}