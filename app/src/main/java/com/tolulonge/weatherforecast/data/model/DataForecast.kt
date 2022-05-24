package com.tolulonge.weatherforecast.data.model

data class DataForecast(
    val date: String,
    val day: DataDay,
    val night: DataNight
)