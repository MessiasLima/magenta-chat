package io.github.messiaslima.core.database.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert
    suspend fun save(message: MessageEntity)

    @Query("select * from MessageEntity order by timestamp desc")
    fun findAll(): Flow<List<MessageEntity>>
}
