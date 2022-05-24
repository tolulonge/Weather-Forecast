package com.tolulonge.weatherforecast.domain.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.core.util.mapper.Mapper
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.domain.model.DomainForecast

class SingleDataForecastToDomainForecastMapper(
    private val dataDayToDomainDay: DataDayToDomainDay,
    private val dataNightToDomainNight: DataNightToDomainNight
) : Mapper<DataForecast, DomainForecast> {
    override fun map(input: DataForecast): DomainForecast = with(input) {
            DomainForecast(
                date = date,
                day = dataDayToDomainDay.map(day),
                night = dataNightToDomainNight.map(night)
            )
    }
}