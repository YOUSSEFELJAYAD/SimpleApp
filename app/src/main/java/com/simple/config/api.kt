package com.simple.config

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object Const {
    const val BASE_URL :String = "https://url.com/api/"
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCorrectFormatFromDateString(dateString: String): String {
    val firstZdt = ZonedDateTime.parse(dateString)
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm ")
    return dtf.format(firstZdt.withZoneSameInstant(ZoneId.systemDefault()).toOffsetDateTime()).toString()
}