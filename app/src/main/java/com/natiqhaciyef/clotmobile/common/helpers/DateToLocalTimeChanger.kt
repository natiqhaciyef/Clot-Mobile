package com.natiqhaciyef.clotmobile.common.helpers

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


fun splitterTimeDate(formattedDate: String): Map<String, String> {
    val map = mutableMapOf<String, String>()
    val time = formattedDate.substring(0 until 5)
    val date = formattedDate.substring(6 until formattedDate.length)

    map["time"] = time
    map["date"] = date
    return map
}

fun dateToLocalTime(formattedDate: String): String {
    val dayIndex = fromDateToDay(formattedDate)
    val day = formattedDate.substring(dayIndex until 2)
    val month = fromDateToMonth(formattedDate.substring(3 until 5))

    return "$day $month"
}


fun fromDateToMonth(month: String) = when (month) {
    "01" -> {
        "Yanvar"
    }

    "02" -> {
        "Fevral"
    }

    "03" -> {
        "Mart"
    }

    "04" -> {
        "Aprel"
    }

    "05" -> {
        "May"
    }

    "06" -> {
        "Iyun"
    }

    "07" -> {
        "Iyul"
    }

    "08" -> {
        "Avqust"
    }

    "09" -> {
        "Sentyabr"
    }

    "10" -> {
        "Oktyabr"
    }

    "11" -> {
        "Noyabr"
    }

    "12" -> {
        "Dekabr"
    }

    else -> "Time left"
}

fun fromDateToDay(day: String): Int = if (day.startsWith("0")) 1 else 0


fun fromDoubleToTimeLine(d: Double = 7.5): String {
    val hours = d.toInt()
    val minutes = (60 * (d - hours)).toInt()

    return "$hours saat\n$minutes dəqiqə"
}

fun formattedDate(dateTime: Date = Calendar.getInstance().time): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return formatter.format(dateTime)
}