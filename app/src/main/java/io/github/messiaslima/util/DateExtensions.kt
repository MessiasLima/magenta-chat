package io.github.messiaslima.util

import java.util.Calendar
import java.util.Date

/**
 * Util class based in [DateExtensions](https://github.com/cesarferreira/tempo/blob/master/library/src/main/kotlin/com/cesarferreira/tempo/DateExtensions.kt)
 */

val Date.isToday: Boolean
    get() = isDateIn(this, 0)

val Date.isYesterday: Boolean
    get() = isDateIn(this, -1)

val Date.isInThisWeek: Boolean
    get() {
        val now = Calendar.getInstance()

        val subjectDate = Calendar.getInstance()
        subjectDate.timeInMillis = time

        val startOfTheWeek = Calendar.getInstance().apply {
            add(Calendar.DATE, -7)
        }

        return subjectDate.timeInMillis < now.timeInMillis &&
            subjectDate.timeInMillis > startOfTheWeek.timeInMillis
    }

private fun isDateIn(date: Date, variable: Int): Boolean {
    val now = Calendar.getInstance()
    val cdate = Calendar.getInstance()
    cdate.timeInMillis = date.time

    now.add(Calendar.DATE, variable)

    return (now.get(Calendar.YEAR) == cdate.get(Calendar.YEAR) && now.get(Calendar.MONTH) == cdate.get(
        Calendar.MONTH
    ) && now.get(Calendar.DATE) == cdate.get(Calendar.DATE))
}