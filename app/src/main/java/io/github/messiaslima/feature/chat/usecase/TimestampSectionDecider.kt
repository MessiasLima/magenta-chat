package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import javax.inject.Inject

private const val SECTION_TIME_THRESHOLD = 1000 * 60 * 60 // 1 hour

class TimestampSectionDecider @Inject constructor() {
    /**
     * Verify if we should show the time section
     * @param currentMessage is the current message that will appear right after the section
     * @param previousMessage the message before the current one.
     * @return a boolean that represents if we need to show the section
     * */
    fun shouldAddSection(
        currentMessage: MessageEntity,
        previousMessage: MessageEntity?
    ): Boolean {
        return previousMessage?.let {
            // if the time difference is more than 1 hour, whe should show the section
            return@let (currentMessage.date.time - it.date.time) > SECTION_TIME_THRESHOLD
        } ?: true // if there is no previous message, we should show the section
    }
}