package io.github.messiaslima.core.database.message

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MessageDao {
    @Insert
    suspend fun save(message: MessageEntity)
}
