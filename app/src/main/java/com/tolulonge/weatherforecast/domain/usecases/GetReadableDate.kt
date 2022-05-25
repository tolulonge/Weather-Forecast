package com.tolulonge.weatherforecast.domain.usecases

class GetReadableDate {
    operator fun invoke(time: String?): String {

        val year = time?.substring(0,4)
        val month = time?.substring(5,7)
        val day = time?.substring(8,10)


        return "$day\n${convertMonthToWord(month?.toInt())}\n$year"
    }

    private fun convertMonthToWord(month: Int?): String {
        return when (month) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sept"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> "Jan"
        }
    }
}
