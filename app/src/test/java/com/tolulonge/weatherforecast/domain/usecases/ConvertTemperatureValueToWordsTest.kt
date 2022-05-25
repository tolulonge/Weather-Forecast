package com.tolulonge.weatherforecast.domain.usecases

import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Test

class ConvertTemperatureValueToWordsTest{

    @Test
    fun `should convert temperature value to english equivalent in words`(){
        val convertTemperatureValueToWords = ConvertTemperatureValueToWords()
        val temperatureValue = 25.0
        val expected = "twenty five"
        val actual = convertTemperatureValueToWords(temperatureValue.toInt())
        Truth.assertThat(actual).isEqualTo(expected)
    }
}