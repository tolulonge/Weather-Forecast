package com.tolulonge.weatherforecast.local.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ToAndFroListMapper
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.local.entities.LocalForecast


class LocalDataForecastListMapper(
    private val localDataDay: LocalDataDay,
    private val localDataNight: LocalDataNight
): ToAndFroListMapper<LocalForecast, DataForecast> {

    override fun from(e: List<DataForecast>): List<LocalForecast> {
        return e.map { toLocal(it) }
    }

    override fun mTo(t: List<LocalForecast>): List<DataForecast> {
        return t.map { toData(it) }
    }

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