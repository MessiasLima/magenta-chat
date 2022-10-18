package io.github.messiaslima.core.database.common

import io.github.messiaslima.test.UnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class DateTypeConverterTest : UnitTest<DateTypeConverter>() {
    override fun buildSut() = DateTypeConverter()

    @Test
    fun `should convert long to date`() {
        val date = Date()

        val actual = sut.toDate(date.time)

        assertEquals(date.time, actual.time)
    }

    @Test
    fun `should convert date to long`() {
        val date = Date()

        val actual = sut.fromDate(date)

        assertEquals(date.time, actual)
    }
}