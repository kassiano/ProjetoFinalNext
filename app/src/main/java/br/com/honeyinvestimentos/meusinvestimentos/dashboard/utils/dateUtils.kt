package br.com.honeyinvestimentos.meusinvestimentos.dashboard.utils

import java.util.*



fun stringToCalendar(dateInput:String): Calendar{

    val date = dateInput.split("-")
    val year = date[0]
    val month = date[1]
    val day = date[2]

    val calendar = Calendar.getInstance()
    calendar.set(year.toInt(), month.toInt()-1, day.toInt())

    return calendar
}


fun dateToLong(cal:Calendar):Long{

    val day = cal.get(Calendar.DAY_OF_MONTH)
    val month = cal.get(Calendar.MONTH) + 1
    val year = cal.get(Calendar.YEAR)

    return "$year$month$day".toLong()
}


fun dateToString(cal:Calendar):String{

    val day = cal.get(Calendar.DAY_OF_MONTH)
    val month = cal.get(Calendar.MONTH) + 1
    val year = cal.get(Calendar.YEAR)

    return "$year-$month-$day"
}


fun dateSum(cal:Calendar, days:Int):Calendar{
    cal.add(Calendar.DATE, days)
    return cal
}