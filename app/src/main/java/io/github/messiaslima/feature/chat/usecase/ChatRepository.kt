package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.Sender
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val chatUiModelMapper: ChatUiModelMapper,
    private val messageEntityMapper: MessageEntityMapper,
) {
    suspend fun sendMessage(text: String, sender: Sender) {
        val entity = messageEntityMapper.map(text, sender)
        messageDao.save(entity)
    }

    fun findAdd() = messageDao.findAll()
        .map(chatUiModelMapper::map)
}
