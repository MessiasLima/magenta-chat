package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.test.UnitTest
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class MessageTailDeciderTest : UnitTest<MessageTailDecider>() {
    override fun buildSut() = MessageTailDecider()

    @Test
    fun `should show tail if next message is null`() {
        val fixtCurrentMessage = fixture<MessageEntity>()

        val actual = sut.shouldShowTail(currentMessage = fixtCurrentMessage, nextMessage = null)

        assertTrue(actual)
    }

    @Test
    fun `should show tail if the messages has different senders`() {
        val fixtDate = fixture<Date>()
        val fixtCurrentMessage = fixture<MessageEntity>()
            .copy(sender = Sender.OTHER_USER, date = fixtDate)
        val fixtNextMessage = fixture<MessageEntity>()
            .copy(sender = Sender.MAIN_USER, date = fixtDate)

        val actual =
            sut.shouldShowTail(currentMessage = fixtCurrentMessage, nextMessage = fixtNextMessage)

        assertTrue(actual)
    }

    @Test
    fun `should show tail if the messages was sent after time threshold`() {
        val fixtDate = Date()

        // The next date is the current date plus the threshold plus one second to make sure that is after the threshold
        val nextMessageDate = Date(fixtDate.time + MessageTailDecider.TAIL_TIME_THRESHOLD + 1000)

        val fixtCurrentMessage = fixture<MessageEntity>().copy(
            sender = Sender.MAIN_USER,
            date = Date(),
        )
        val fixtNextMessage = fixture<MessageEntity>().copy(
            sender = Sender.MAIN_USER,
            date = nextMessageDate,
        )

        val actual = sut.shouldShowTail(
            currentMessage = fixtCurrentMessage,
            nextMessage = fixtNextMessage,
        )

        assertTrue(actual)
    }

    @Test
    fun `should not show tail if the messages was sent before time threshold`() {
        val fixtCurrentMessage = fixture<MessageEntity>()
            .copy(sender = Sender.MAIN_USER, date = Date())
        val fixtNextMessage = fixture<MessageEntity>()
            .copy(sender = Sender.MAIN_USER, date = Date())

        val actual =
            sut.shouldShowTail(currentMessage = fixtCurrentMessage, nextMessage = fixtNextMessage)

        assertFalse(actual)
    }
}