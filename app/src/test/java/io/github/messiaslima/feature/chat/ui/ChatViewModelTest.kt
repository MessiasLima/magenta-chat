package io.github.messiaslima.feature.chat.ui

import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.feature.chat.usecase.ChatRepository
import io.github.messiaslima.feature.chat.usecase.ChatUiModel
import io.github.messiaslima.test.MainDispatcherRule
import io.github.messiaslima.test.UnitTest
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ChatViewModelTest : UnitTest<ChatViewModel>() {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockChatRepository = mockk<ChatRepository> {
        every { findAdd() } returns flowOf(emptyList())
    }

    override fun buildSut() = ChatViewModel(mockChatRepository)

    @Test
    fun `should toggle sender`() {
        assertEquals(Sender.MAIN_USER, sut.sender.value)

        sut.toggleSender()

        assertEquals(Sender.OTHER_USER, sut.sender.value)

        sut.toggleSender()

        assertEquals(Sender.MAIN_USER, sut.sender.value)
    }

    @Test
    fun `should load messages`() = runTest {
        val fixtUiModelsList: List<ChatUiModel> = fixture()

        every { mockChatRepository.findAdd() } returns flowOf(fixtUiModelsList)

        val localSut = ChatViewModel(mockChatRepository)

        val actual = localSut.messages.firstOrNull()

        assertEquals(fixtUiModelsList, actual)
    }

    @Test
    fun `should send message`() = runTest(TestCoroutineScheduler()) {
        val fixtMessage: String = fixture()

        sut.sendMessage(fixtMessage)

        coVerify { mockChatRepository.sendMessage(fixtMessage, sut.sender.value) }
    }
}