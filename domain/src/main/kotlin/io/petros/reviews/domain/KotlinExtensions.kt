package io.petros.reviews.domain

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* GENERAL */

fun emptyString() = ""

fun space() = " "

fun dash() = "-"

fun questionMark() = "?"

fun star() = "★"

fun leftParentheses() = "("

fun rightParentheses() = ")"

fun String.withParentheses(): String {
    return leftParentheses() + this + rightParentheses()
}

/* DATE */

const val REVIEW_DATE_FORMAT = "MMMM dd, yyyy"

fun String.toDate(dateFormat: String): Date {
    try {
        return SimpleDateFormat(dateFormat, Locale.US).parse(this)
    } catch (pe: ParseException) {
        throw ParseException("Cannot parse date. [Date Format: $dateFormat]", pe.errorOffset)
    }
}

fun Date.toString(dateFormat: String): String {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val date = SimpleDateFormat(dateFormat, Locale.US)
    return date.format(calendar.time)
}
