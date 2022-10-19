package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.Sender
import io.github.messiaslima.test.UnitTest
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class MessageEntityMapperTest : UnitTest<MessageEntityMapper>() {
    override fun buildSut() = MessageEntityMapper()

    @Test
    fun `should map message entity`() {
        val fixtText = fixture<String>()
        val fixtSender = fixture<Sender>()
        val fixtDate = fixture<Date>()

        val actual = sut.map(text = fixtText, sender = fixtSender, date = fixtDate)

        assertNull(actual.id)
        assertEquals(fixtText, actual.text)
        assertEquals(fixtSender, actual.sender)
        assertEquals(fixtDate, actual.date)
    }
}