package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.domain.model.DomainForecast

class DataForecastToDomainForecastMapper(
    private val dataDayToDomainDay: DataDayToDomainDay,
    private val dataNightToDomainNight: DataNightToDomainNight
) : ListMapper<DataForecast, DomainForecast> {
    override fun map(input: List<DataForecast>): List<DomainForecast> = with(input) {
        map {
            DomainForecast(
                date = it.date,
                day = dataDayToDomainDay.map(it.day),
                night = dataNightToDomainNight.map(it.night)
            )
        }
    }
}