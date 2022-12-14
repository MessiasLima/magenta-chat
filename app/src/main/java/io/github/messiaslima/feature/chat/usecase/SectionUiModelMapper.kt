package io.github.messiaslima.feature.chat.usecase

import io.github.messiaslima.R
import io.github.messiaslima.core.database.message.MessageEntity
import io.github.messiaslima.util.isInThisWeek
import io.github.messiaslima.util.isToday
import io.github.messiaslima.util.isYesterday
import io.github.messiaslima.util.resourceprovider.ResourceProvider
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SectionUiModelMapper @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun map(message: MessageEntity): SectionUiModel = when {
        message.date.isToday -> parseTodaySection(message)
        message.date.isYesterday -> parseYesterdaySection(message)
        message.date.isInThisWeek -> parseThisWeekSection(message)
        else -> parseDefaultSection(message)
    }

    private fun parseDefaultSection(message: MessageEntity): SectionUiModel {
        val text = parseDate(message.date, "dd/MM/yyyy HH:mm")
        return SectionUiModel(text = text)
    }

    private fun parseThisWeekSection(message: MessageEntity): SectionUiModel {
        val text = parseDate(message.date, "EEE HH:mm")
        return SectionUiModel(text = text)
    }

    private fun parseYesterdaySection(message: MessageEntity): SectionUiModel {
        val time = parseDate(message.date, "HH:mm")
        val text = resourceProvider.getString(R.string.yesterdat_at, time)
        return SectionUiModel(text = text)
    }

    private fun parseTodaySection(message: MessageEntity): SectionUiModel {
        val time = parseDate(message.date, "HH:mm")
        val text = resourceProvider.getString(R.string.today_at, time)
        return SectionUiModel(text = text)
    }

    // TODO: Inject this through the constructor
    private fun parseDate(date: Date, pattern: String): String {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(date)
    }
}
