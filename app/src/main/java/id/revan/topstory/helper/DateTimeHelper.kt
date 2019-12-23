package id.revan.topstory.helper

import java.text.SimpleDateFormat
import java.util.*

object DateTimeHelper {
    fun convertTimestampToReadableTime(time: Long): String {
        val date = Date(time)
        val newFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return newFormat.format(date.time)
    }

    fun getLastTime(): String {
        val date = Date()
        val newFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())

        return newFormat.format(date)
    }
}