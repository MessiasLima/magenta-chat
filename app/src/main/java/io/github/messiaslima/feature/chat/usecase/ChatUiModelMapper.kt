package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import javax.inject.Inject

private const val TAIL_TIME_THRESHOLD = 1000 * 20 // 20 seconds
private const val SECTION_TIME_THRESHOLD = 1000 * 60 * 60 // 1 hour

class ChatUiModelMapper @Inject constructor(
    private val sectionUiModelMapper: SectionUiModelMapper
) {
    fun map(messages: List<MessageEntity>): List<ChatUiModel> {
        val uiModels = mutableListOf<ChatUiModel>()

        messages.forEachIndexed { index, message ->
            val previousMessage = messages.getOrNull(index + 1)
            val nextMessage = messages.getOrNull(index - 1)

            if (shouldAddSection(currentMessage = message, previousMessage = previousMessage)) {
                uiModels.add(sectionUiModelMapper.map(message))
            }

            val shouldShowTail = nextMessage?.let {
                shouldShowTail(currentMessage = message, nextMessage = it)
            } ?: true // If there is no next message, the current one is the most recent

            val messageModel = when (message.sender) {
                Sender.MAIN_USER -> MainUserMessageUiModel(
                    text = message.text,
                    hasTail = shouldShowTail,
                )

                Sender.OTHER_USER -> OtherUserMessageUiModel(
                    text = message.text,
                    hasTail = shouldShowTail,
                )
            }

            uiModels.add(messageModel)
        }

        return uiModels
    }

    /**
     * Verify if we should show the time section
     * @param currentMessage is the current message that will appear right after the section
     * @param previousMessage the message before the current one.
     * @return a boolean that represents if we need to show the section
     * */
    private fun shouldAddSection(
        currentMessage: MessageEntity,
        previousMessage: MessageEntity?
    ): Boolean {
        return previousMessage?.let {
            // if the time difference is more than 1 hour, whe should show the section
            return@let it.date.time - currentMessage.date.time > SECTION_TIME_THRESHOLD
        } ?: true // if there is no previous message, we should show the section
    }

    private fun shouldShowTail(currentMessage: MessageEntity, nextMessage: MessageEntity): Boolean {
        return nextMessageWasSentByOtherUser(currentMessage, nextMessage) ||
                nextMessageWasAfterTimeThreshold(currentMessage, nextMessage)
    }

    private fun nextMessageWasSentByOtherUser(currentMessage: MessageEntity, nextMessage: MessageEntity) =
        currentMessage.sender != nextMessage.sender

    private fun nextMessageWasAfterTimeThreshold(currentMessage: MessageEntity, nextMessage: MessageEntity) =
        nextMessage.date.time - currentMessage.date.time > TAIL_TIME_THRESHOLD
}