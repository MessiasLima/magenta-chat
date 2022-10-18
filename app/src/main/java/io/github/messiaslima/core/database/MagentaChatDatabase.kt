package io.github.messiaslima.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.messiaslima.core.database.common.DateTypeConverter
import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class MagentaChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        const val NAME = "magenta-chat-database"
    }
}
