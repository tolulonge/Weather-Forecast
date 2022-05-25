package com.tolulonge.weatherforecast.domain.usecases

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FindTempInStringAndConvertTest{

    private lateinit var findTempInStringAndConvert: FindTempInStringAndConvert
    private lateinit var convertTemperatureValueToWords: ConvertTemperatureValueToWords
    @Before
    fun setUp() {
        convertTemperatureValueToWords = ConvertTemperatureValueToWords()
        findTempInStringAndConvert = FindTempInStringAndConvert(convertTemperatureValueToWords)
    }

    @Test
    fun `should return a string with all temperature values modified to words case one`() {
        val word1 = "Clouds thickening during day. Rain showers spreading from " +
                "islands to northeast. Air temperature 18..24, on western coast 10..16°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Clouds thickening during day. Rain showers spreading from islands to northeast. Air temperature eighteen to twenty four degrees, on western coast ten to sixteen degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case two`() {
        val word1 = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature 6..9°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature six to nine degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case three`() {
        val word1 = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature 6..94°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature six to ninety four degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case four`() {
        val word1 = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature 26..94°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature twenty six to ninety four degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case five`() {
        val word1 = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature 2..9°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Before midnight weak variable wind, after midnight southeast, south wind 2-7 m/s. Wave height 0.2-0.7 m. Dry. Visibility good. Air temperature two to nine degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case six`() {
        val word1 = "Clouds thickening during day. Rain showers spreading from " +
                "islands to northeast. Air temperature 1..24, on western coast 8..16°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Clouds thickening during day. Rain showers spreading from islands to northeast. Air temperature one to twenty four degrees, on western coast eight to sixteen degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case seven`() {
        val word1 = "Clouds thickening during day. Rain showers spreading from " +
                "islands to northeast. Air temperature 1..6, on western coast 8..16°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Clouds thickening during day. Rain showers spreading from islands to northeast. Air temperature one to six degrees, on western coast eight to sixteen degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case eight`() {
        val word1 = "Clouds thickening during day. Rain showers spreading from " +
                "islands to northeast. Air temperature 19..48°C, on western coast 8..16°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Clouds thickening during day. Rain showers spreading from islands to northeast. Air temperature nineteen to forty eight degrees, on western coast eight to sixteen degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }

    @Test
    fun `should return a string with all temperature values modified to words case nine`() {
        val word1 = "Clear and slightly cloudy. Dry. In the morning on islands it is clouding up and rain showers locally. At first in continent weak variable wind, after midnight southeast, south wind 2-7, on islands 4-10 m/s. Air temperature 4..10, on western coast up to 12°C."
        val temperatureInWords = findTempInStringAndConvert(word1)
        val expected = "Clear and slightly cloudy. Dry. In the morning on islands it is clouding up and rain showers locally. At first in continent weak variable wind, after midnight southeast, south wind 2-7, on islands 4-10 m/s. Air temperature four to ten degrees, on western coast up to twelve degrees."

        assertThat(temperatureInWords).isEqualTo(expected)

    }




}