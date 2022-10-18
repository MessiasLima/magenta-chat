package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageDao
import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.test.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class ChatRepositoryTest : UnitTest<ChatRepository>() {
    private val mockMessageDao: MessageDao = mockk()
    private val mockChatUiModelMapper: ChatUiModelMapper = mockk()
    private val mockMessageEntityMapper: MessageEntityMapper = mockk()

    override fun buildSut() = ChatRepository(
        messageDao = mockMessageDao,
        chatUiModelMapper = mockChatUiModelMapper,
        messageEntityMapper = mockMessageEntityMapper
    )

    @Test
    fun `should send message`() = runTest {
        val fixtMessage = fixture<String>()
        val fixtSender = fixture<Sender>()
        val fixtEntity = fixture<MessageEntity>()

        every { mockMessageEntityMapper.map(fixtMessage, fixtSender) } returns fixtEntity
        coEvery { mockMessageDao.save(fixtEntity) } returns Unit

        sut.sendMessage(text = fixtMessage, sender = fixtSender)

        coVerify { mockMessageDao.save(fixtEntity) }
    }

    @Test
    fun `should find all messages`() = runTest {
        val fixtEntities = fixture<List<MessageEntity>>()
        val fixtUiModels = fixture<List<ChatUiModel>>()

        every { mockMessageDao.findAll() } returns flowOf(fixtEntities)
        every { mockChatUiModelMapper.map(fixtEntities) } returns fixtUiModels

        val actual = sut.findAdd().firstOrNull()

        assertEquals(fixtUiModels, actual)
    }
}