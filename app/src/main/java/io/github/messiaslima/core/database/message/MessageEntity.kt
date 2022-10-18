package io.github.messiaslima.core.database.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo val text: String,
    @ColumnInfo val sender: String,
    @ColumnInfo val timestamp: Long,
)
