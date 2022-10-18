package io.github.messiaslima.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MagentaChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        const val NAME = "magenta-chat-database"
    }
}
