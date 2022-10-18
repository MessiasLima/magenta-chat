package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val chatUiModelMapper: ChatUiModelMapper,
) {
    suspend fun sendMessage(text: String, sender: Sender) {
        val entity = MessageEntity(
            id = null,
            text = text,
            sender = sender,
            date = Date()
        )

        messageDao.save(entity)
    }

    fun findAdd() = messageDao.findAll()
        .map(chatUiModelMapper::map)
}
