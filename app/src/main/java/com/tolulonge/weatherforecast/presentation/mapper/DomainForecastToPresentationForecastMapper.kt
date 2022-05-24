package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast

class DomainForecastToPresentationForecastMapper(
    private val domainDayToPresentationDay: DomainDayToPresentationDay,
    private val domainNightToPresentationNight: DomainNightToPresentationNight
): ListMapper<DomainForecast, PresentationForecast> {
    override fun map(input: List<DomainForecast>): List<PresentationForecast> = with(input) {
        map {
            PresentationForecast(
                date = it.date,
                day = domainDayToPresentationDay.map(it.day),
                night = domainNightToPresentationNight.map(it.night)
            )
        }
    }
}