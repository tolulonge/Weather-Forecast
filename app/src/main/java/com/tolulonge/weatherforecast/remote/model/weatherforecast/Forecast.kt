package com.tolulonge.weatherforecast.remote.model.weatherforecast

data class Forecast(
    val date: String,
    val day: Day,
    val night: Night
)
