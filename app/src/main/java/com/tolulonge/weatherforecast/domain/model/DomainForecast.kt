package com.tolulonge.weatherforecast.domain.model

data class DomainForecast(
    val date: String,
    val day: DomainDay,
    val night: DomainNight
)