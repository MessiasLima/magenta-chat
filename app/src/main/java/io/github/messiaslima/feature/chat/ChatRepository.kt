package io.github.messiaslima.feature.chat

import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.domain.Message
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val messageDao: MessageDao
) {
    suspend fun sendMessage(newMessage: Message) {
        val entity = MessageEntity(
            id = null,
            text = newMessage.text,
            sender = newMessage.sender.name,
            timestamp = newMessage.date.time
        )

        messageDao.save(entity)
    }

    fun findAdd() = messageDao.findAll()
}
