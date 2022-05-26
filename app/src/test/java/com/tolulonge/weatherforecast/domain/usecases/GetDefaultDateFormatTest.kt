package com.tolulonge.weatherforecast.domain.usecases

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetDefaultDateFormatTest {

    @Test
    fun `should return default date format`() {
        val useCase = GetDefaultDateFormat()
        val presentationDate = "22\nMay\n2022"
        val result = useCase(presentationDate)

        assertThat(result).isEqualTo("2022-05-22")
    }
}