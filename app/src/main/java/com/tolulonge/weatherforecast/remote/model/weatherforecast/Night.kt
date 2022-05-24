package com.tolulonge.weatherforecast.remote.model.weatherforecast

data class Night(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<Place>?,
    val sea: String?,
    val tempmax: Double?,
    val tempmin: Double?,
    val text: String?,
    val winds: List<Wind>?
)