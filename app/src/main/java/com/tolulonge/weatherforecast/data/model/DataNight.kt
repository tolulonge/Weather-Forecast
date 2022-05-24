package com.tolulonge.weatherforecast.data.model

data class DataNight(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<DataPlace>?,
    val sea: String?,
    val tempmax: Double?,
    val tempmin: Double?,
    val text: String?,
    val winds: List<DataWind>?
)