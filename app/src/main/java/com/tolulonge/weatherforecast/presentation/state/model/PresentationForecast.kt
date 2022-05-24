package com.tolulonge.weatherforecast.presentation.state.model


data class PresentationForecast(
    val date: String?,
    val day: PresentationDay?,
    val night: PresentationNight?
)

fun PresentationForecast.toPresentationForecastGallery() : PresentationForecastGallery =
    PresentationForecastGallery(
        date =  date,
        dPhenomenon = day?.phenomenon,
        dPeipsi = day?.peipsi,
        dSea = day?.sea,
        dTempmax = day?.tempmax,
        dTempmin = day?.tempmin,
        dText = day?.text,
        nPhenomenon = day?.phenomenon,
        nPeipsi = day?.peipsi,
        nSea = day?.sea,
        nTempmax = day?.tempmax,
        nTempmin = day?.tempmin,
        nText = day?.text
    )