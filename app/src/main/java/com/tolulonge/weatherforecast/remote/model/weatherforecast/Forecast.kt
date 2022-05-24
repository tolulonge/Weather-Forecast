package com.tolulonge.weatherforecast.remote.model.weatherforecast

import com.tolulonge.weatherforecast.data.model.DataDay
import com.tolulonge.weatherforecast.data.model.DataPlace
import com.tolulonge.weatherforecast.data.model.DataWind

data class Forecast(
    val date: String,
    val day: Day,
    val night: Night
)
