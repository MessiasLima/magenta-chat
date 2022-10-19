package io.github.messiaslima.feature.chat.usecase

import androidx.annotation.VisibleForTesting
import io.github.messiaslima.core.database.message.MessageEntity
import javax.inject.Inject

class MessageTailDecider @Inject constructor() {
    fun shouldShowTail(currentMessage: MessageEntity, nextMessage: MessageEntity?): Boolean {
        return nextMessage?.let {
            shouldShowMessageTail(currentMessage = currentMessage, nextMessage = it)
        } ?: true // If there is no next message, the current one is the most recent
    }

    private fun shouldShowMessageTail(currentMessage: MessageEntity, nextMessage: MessageEntity): Boolean {
        return nextMessageWasSentByOtherUser(currentMessage, nextMessage) ||
                nextMessageWasAfterTimeThreshold(currentMessage, nextMessage)
    }

    private fun nextMessageWasSentByOtherUser(
        currentMessage: MessageEntity,
        nextMessage: MessageEntity
    ) = currentMessage.sender != nextMessage.sender

    private fun nextMessageWasAfterTimeThreshold(
        currentMessage: MessageEntity,
        nextMessage: MessageEntity
    ) = nextMessage.date.time - currentMessage.date.time > TAIL_TIME_THRESHOLD

    companion object {
        @VisibleForTesting
        const val TAIL_TIME_THRESHOLD = 1000 * 20 // 20 seconds
    }
}