package io.github.messiaslima.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DatabaseModule::class])
object DatabaseTestModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): MagentaChatDatabase {
        return Room.inMemoryDatabaseBuilder(
            applicationContext,
            MagentaChatDatabase::class.java,
        ).build()
    }

    @Provides
    fun provideMessageDao(magentaChatDatabase: MagentaChatDatabase) =
        magentaChatDatabase.messageDao()
}