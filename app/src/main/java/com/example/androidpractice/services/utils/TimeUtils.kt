package com.example.androidpractice.services.utils

import android.os.Build
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object TimeUtils {
    fun getDateTime(minutesToAdd: Long = 0): String {
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val pattern = "HH:mm:ss"
            val nowInUtc = OffsetDateTime.now(ZoneOffset.UTC)
            val someMinutesLater = nowInUtc.plusMinutes(minutesToAdd)
            someMinutesLater.format(DateTimeFormatter.ofPattern(pattern))
        } else {
            "bad version of Android"
        }
    }

    fun getCurrentHour(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.HOUR)
    }

    fun getCurrentMinute(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MINUTE)
    }

    fun getCurrentSecond(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.SECOND)
    }

    fun getCurrentMilliSecond(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MILLISECOND)
    }
}