package com.tolulonge.weatherforecast.local.entities

data class LocalNight(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<LocalPlace>?,
    val sea: String?,
    val tempmax: Double?,
    val tempmin: Double?,
    val text: String?,
    val winds: List<LocalWind>?
)