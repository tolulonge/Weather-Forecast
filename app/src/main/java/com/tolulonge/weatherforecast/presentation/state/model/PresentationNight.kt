package com.tolulonge.weatherforecast.presentation.state.model

data class PresentationNight(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<PresentationPlace>?,
    val sea: String?,
    val tempmax: String?,
    val tempmin: String?,
    val text: String?,
    val winds: List<PresentationWind>?
)