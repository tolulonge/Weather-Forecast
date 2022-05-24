package com.tolulonge.weatherforecast.presentation.state.model

data class PresentationDay(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<PresentationPlace>?,
    val sea: String?,
    val tempmax: Double?,
    val tempmin: Double?,
    val text: String?,
    val winds: List<PresentationWind>?
)
