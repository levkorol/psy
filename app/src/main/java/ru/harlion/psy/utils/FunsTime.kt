package ru.harlion.psy.utils

import android.content.res.Resources
import ru.harlion.psy.R
import java.text.SimpleDateFormat
import java.util.*

//fun formatTimeHours(millis: Long, resources: Resources): String {
//    val seconds = millis / 1000
//
//    val minutes = seconds / 60
//    val secsInMin = seconds % 60
//
//    val hours = minutes / 60
//    val minsInHour = minutes % 60
//
//    return resources.getString(R.string.time_hours_minutes_seconds_formatter, hours, minsInHour, secsInMin)
//}
//
//fun formatTimeMinsSec(millis: Long, resources: Resources): String {
//    val seconds = millis / 1000
//
//    val minutes = seconds / 60
//    val secsInMin = seconds % 60
//
//    return resources.getString(R.string.time_minutes_seconds_formatter, minutes, secsInMin)
//}
//
//fun formatTimeMins(millis: Long, resources: Resources): String {
//    val seconds = millis / 1000
//
//    val secsInMin = seconds % 60
//
//    return resources.getString(R.string.time_minutes_formatter,  secsInMin)
//}

fun dateToString(date: Long): String {
    val dateFormat = SimpleDateFormat("EEEE, dd MMMM ", Locale.getDefault())
    return dateFormat.format(date)
}

fun dateAndTimeToString(date: Long): String {
    val dateFormat = SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault())
    return dateFormat.format(date)
}

fun dateToStringShort(date: Long): String {
    val dateFormat = SimpleDateFormat(" EE, dd MMM", Locale.getDefault())
    return dateFormat.format(date)
}

fun timeToString(time: Long): String {
    val tim = SimpleDateFormat("HH:mm", Locale.getDefault())
    tim.timeZone = TimeZone.getTimeZone("UTC")
    return tim.format(time)
}