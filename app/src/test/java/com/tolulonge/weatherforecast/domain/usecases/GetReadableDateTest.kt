package com.tolulonge.weatherforecast.domain.usecases


import com.google.common.truth.Truth.assertThat
import org.junit.Test


class GetReadableDateTest {


    @Test
    fun `should return a customized time from weather date`() {
        val getReadableDate = GetReadableDate()
        val weatherDate = "2022-05-25"
        val expected = "25\nMay\n2022"
        val actual = getReadableDate(weatherDate)
        assertThat(actual).isEqualTo(expected)
    }
}