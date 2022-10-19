package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.test.UnitTest
import io.github.messiaslima.util.isToday
import io.github.messiaslima.R
import io.github.messiaslima.util.isInThisWeek
import io.github.messiaslima.util.isYesterday
import io.github.messiaslima.util.resourceprovider.ResourceProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date

class SectionUiModelMapperTest : UnitTest<SectionUiModelMapper>() {
    private val mockResourceProvider = mockk<ResourceProvider>()

    override fun buildSut() = SectionUiModelMapper(resourceProvider = mockResourceProvider)

    @Test
    fun `should parse today section`() {
        val mockDate = createDate("01/01/2000 12:34:53")
        val fixtMessage = fixture<MessageEntity>().copy(date = mockDate)

        mockkStatic("io.github.messiaslima.util.DateExtensionsKt")
        every { fixtMessage.date.isToday } returns true
        every {
            mockResourceProvider.getString(
                R.string.today_at,
                "12:34"
            )
        } returns "Today at 12:34"

        val actual = sut.map(fixtMessage)

        assertEquals("Today at 12:34", actual.text)
    }

    @Test
    fun `should parse yesterday section`() {
        val mockDate = createDate("01/01/2000 12:34:53")
        val fixtMessage = fixture<MessageEntity>().copy(date = mockDate)

        mockkStatic("io.github.messiaslima.util.DateExtensionsKt")
        every { fixtMessage.date.isToday } returns false
        every { fixtMessage.date.isYesterday } returns true
        every {
            mockResourceProvider.getString(
                R.string.yesterdat_at,
                "12:34"
            )
        } returns "Yesterday at 12:34"

        val actual = sut.map(fixtMessage)

        assertEquals("Yesterday at 12:34", actual.text)
    }

    @Test
    fun `should parse this week section`() {
        val mockDate = createDate("01/01/2000 12:34:53")
        val fixtMessage = fixture<MessageEntity>().copy(date = mockDate)

        mockkStatic("io.github.messiaslima.util.DateExtensionsKt")
        every { fixtMessage.date.isToday } returns false
        every { fixtMessage.date.isYesterday } returns false
        every { fixtMessage.date.isInThisWeek } returns true

        val actual = sut.map(fixtMessage)

        assertEquals("Sat 12:34", actual.text)
    }

    @Test
    fun `should parse default section`() {
        val mockDate = createDate("01/01/2000 12:34:53")
        val fixtMessage = fixture<MessageEntity>().copy(date = mockDate)

        mockkStatic("io.github.messiaslima.util.DateExtensionsKt")
        every { fixtMessage.date.isToday } returns false
        every { fixtMessage.date.isYesterday } returns false
        every { fixtMessage.date.isInThisWeek } returns false

        val actual = sut.map(fixtMessage)

        assertEquals("01/01/2000 12:34", actual.text)
    }

    private fun createDate(dateString: String): Date {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        return dateFormat.parse(dateString)!!
    }
}