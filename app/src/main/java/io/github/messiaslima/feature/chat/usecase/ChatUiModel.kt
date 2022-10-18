package io.github.messiaslima.feature.chat.usecase

sealed interface ChatUiModel

sealed class MessageUiModel(val text: String, val hasTail: Boolean) : ChatUiModel

class MainUserMessageUiModel(text: String, hasTail: Boolean) :
    MessageUiModel(text = text, hasTail = hasTail)

class OtherUserMessageUiModel(text: String, hasTail: Boolean) :
    MessageUiModel(text = text, hasTail = hasTail)

data class SectionUiModel(val text: String): ChatUiModel

