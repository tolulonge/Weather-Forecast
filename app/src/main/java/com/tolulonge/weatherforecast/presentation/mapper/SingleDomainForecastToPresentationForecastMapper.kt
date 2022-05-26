package com.tolulonge.weatherforecast.presentation.mapper

import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast

class SingleDomainForecastToPresentationForecastMapper(
    private val domainDayToPresentationDay: DomainDayToPresentationDay,
    private val domainNightToPresentationNight: DomainNightToPresentationNight
): Mapper<DomainForecast, PresentationForecast> {

    override fun map(input: DomainForecast): PresentationForecast = with(input) {
        PresentationForecast(
            date = date,
            day = domainDayToPresentationDay.map(day),
            night = domainNightToPresentationNight.map(night)
        )
    }
}