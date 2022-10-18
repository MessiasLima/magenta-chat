package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import javax.inject.Inject

class ChatUiModelMapper @Inject constructor(
    private val sectionUiModelMapper: SectionUiModelMapper,
    private val messageTailDecider: MessageTailDecider,
    private val timestampSectionDecider: TimestampSectionDecider,
) {
    fun map(messages: List<MessageEntity>): List<ChatUiModel> {
        val uiModels = mutableListOf<ChatUiModel>()

        messages.forEachIndexed { index, message ->
            val previousMessage = messages.getOrNull(index + 1)
            val nextMessage = messages.getOrNull(index - 1)

            val shouldShowTail = messageTailDecider.shouldShowTail(
                currentMessage = message,
                nextMessage = nextMessage,
            )

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

            val shouldAddSection = timestampSectionDecider.shouldAddSection(
                currentMessage = message,
                previousMessage = previousMessage
            )

            if (shouldAddSection) {
                uiModels.add(sectionUiModelMapper.map(message))
            }
        }

        return uiModels
    }
}