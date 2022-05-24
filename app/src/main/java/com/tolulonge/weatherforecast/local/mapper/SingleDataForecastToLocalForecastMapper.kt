package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroMapper
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.local.entities.LocalForecast

class SingleDataForecastToLocalForecastMapper(
    private val localDataDay: LocalDataDay,
    private val localDataNight: LocalDataNight
) : ToAndFroMapper<LocalForecast, DataForecast> {

    override fun from(e: DataForecast): LocalForecast =
        toLocal(e)

    override fun mTo(t: LocalForecast): DataForecast =
        toData(t)

    private fun toData(from: LocalForecast): DataForecast {
        return DataForecast(
            date = from.date,
            day = localDataDay.mTo(from.day),
            night =localDataNight.mTo(from.night)
        )
    }

    private fun toLocal(from: DataForecast): LocalForecast {
        return LocalForecast(
            date = from.date,
            day = localDataDay.from(from.day),
            night = localDataNight.from(from.night)
        )
    }
}