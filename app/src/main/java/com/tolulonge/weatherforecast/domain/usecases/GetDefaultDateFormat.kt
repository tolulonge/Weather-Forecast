package com.tolulonge.weatherforecast.domain.usecases

class GetDefaultDateFormat {
    operator fun invoke(date: String?): String {

     val string = date?.split("\n")


        return "${string?.get(2)}-${convertWordToMonth(string?.get(1))}-${string?.get(0)}"
    }

    private fun convertWordToMonth(month: String?): String {
        return when (month) {
            "Jan" -> "01"
            "Feb" -> "02"
            "Mar" -> "03"
            "Apr" -> "04"
            "May" -> "05"
            "Jun" -> "06"
           "Jul" -> "07"
            "Aug" -> "08"
            "Sep" -> "09"
            "Oct" -> "10"
            "Nov" -> "11"
            "Dec" -> "12"
            else -> "01"
        }
    }
}
