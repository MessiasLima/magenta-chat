package io.github.messiaslima.domain

import java.util.Date

data class Message(
    val id: Int?,
    val text: String,
    val sender: Sender,
    val date: Date,
)
