package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.test.UnitTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class ChatUiModelMapperTest : UnitTest<ChatUiModelMapper>() {
    private val mockSectionUiModelMapper = mockk<SectionUiModelMapper>()
    private val mockMessageTailDecider = mockk<MessageTailDecider>()
    private val mockTimestampSectionDecider = mockk<TimestampSectionDecider>()

    override fun buildSut() = ChatUiModelMapper(
        sectionUiModelMapper = mockSectionUiModelMapper,
        messageTailDecider = mockMessageTailDecider,
        timestampSectionDecider = mockTimestampSectionDecider,
    )

    @Test
    fun `should map messages`() {
        val fixtDate = fixture<Date>()
        val fixtMainUserMessage = fixture<MessageEntity>()
            .copy(sender = Sender.MAIN_USER, date = fixtDate)
        val fixtOtherUserMessage = fixture<MessageEntity>()
            .copy(sender = Sender.OTHER_USER, date = fixtDate)

        val fixtEntities = listOf(
            fixtMainUserMessage,
            fixtOtherUserMessage
        )

        every { mockMessageTailDecider.shouldShowTail(any(), any()) } returns true
        every { mockTimestampSectionDecider.shouldAddSection(any(), any()) } returns false

        val actual = sut.map(fixtEntities)

        assertEquals(2, actual.size)

        assertTrue(actual[0] is MainUserMessageUiModel)
        assertEquals(fixtMainUserMessage.text, (actual[0] as MainUserMessageUiModel).text)
        assertTrue((actual[0] as MainUserMessageUiModel).hasTail)

        assertTrue(actual[1] is OtherUserMessageUiModel)
        assertEquals(fixtOtherUserMessage.text, (actual[1] as OtherUserMessageUiModel).text)
        assertTrue((actual[1] as OtherUserMessageUiModel).hasTail)
    }

    @Test
    fun `should map messages with section`() {
        val fixtDate = fixture<Date>()
        val fixtMainUserMessage = fixture<MessageEntity>()
            .copy(sender = Sender.MAIN_USER, date = fixtDate)
        val fixtOtherUserMessage = fixture<MessageEntity>()
            .copy(sender = Sender.OTHER_USER, date = fixtDate)
        val fixtSectionModel = fixture<SectionUiModel>()

        val fixtEntities = listOf(
            fixtMainUserMessage,
            fixtOtherUserMessage
        )

        every { mockMessageTailDecider.shouldShowTail(any(), any()) } returns true
        every { mockSectionUiModelMapper.map(fixtOtherUserMessage) } returns fixtSectionModel
        every {
            mockTimestampSectionDecider.shouldAddSection(
                currentMessage = fixtOtherUserMessage,
                previousMessage = null
            )
        } returns true
        every {
            mockTimestampSectionDecider.shouldAddSection(
                currentMessage = fixtMainUserMessage,
                previousMessage = fixtOtherUserMessage
            )
        } returns false

        val actual = sut.map(fixtEntities)

        assertEquals(3, actual.size)
        assertTrue(actual[2] is SectionUiModel)
        assertEquals(fixtSectionModel, actual[2])
    }
}