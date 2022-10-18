package io.github.messiaslima.core.database.message

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MessageDao {
    @Insert
    fun save(message: MessageEntity)
}
