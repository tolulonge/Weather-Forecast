package com.tolulonge.weatherforecast.domain.model

data class DomainNight(
    val peipsi: String?,
    val phenomenon: String?,
    val places: List<DomainPlace>?,
    val sea: String?,
    val tempmax: Double?,
    val tempmin: Double?,
    val text: String?,
    val winds: List<DomainWind>?
)