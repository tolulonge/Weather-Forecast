package com.tolulonge.weatherforecast.domain.usecases

/**
 * This is a use case that converts temperature values to english words
 */
class ConvertTemperatureValueToWords {


    operator fun invoke(temperatureValue: Int): String? {
        if (temperatureValue < 100) {
            return convertValueLessThanHundred(temperatureValue)
        }
        if (temperatureValue < 1000) {
            return convertValueLessThanOneThousand(temperatureValue)
        }
        return temperatureValue.toString()
    }


    private var zeroToNineteen = arrayOf(
        "zero", "one", "two", "three", "four", "five", "six",
        "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen",
        "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    )
    private var tens =
        arrayOf("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    private fun convertValueLessThanHundred(value: Int): String? {
        if (value < 20) return zeroToNineteen[value]
        for (i in tens.indices) {
            val tensValue = tens[i]
            val digitValue = 20 + 10 * i
            if (digitValue + 10 > value) {
                return if (value % 10 != 0) "$tensValue ${zeroToNineteen[value % 10]}" else tensValue
            }
        }
        return null
    }

    private fun convertValueLessThanOneThousand(value: Int): String? {
        var englishWord = ""
        val remainder = value / 100
        val modulus = value % 100
        if (remainder > 0) {
            englishWord = "${zeroToNineteen[remainder]} hundred"
            if (modulus > 0) {
                englishWord = "$englishWord "
            }
        }
        if (modulus > 0) {
            englishWord += convertValueLessThanHundred(modulus)
        }
        return englishWord
    }

}