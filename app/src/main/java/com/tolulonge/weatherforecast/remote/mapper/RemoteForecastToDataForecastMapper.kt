package com.tolulonge.weatherforecast.remote.mapper

import com.tolulonge.weatherforecast.core.util.mapper.ListMapper
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.remote.model.weatherforecast.Forecast


class RemoteForecastToDataForecastMapper(
    private val remoteDayToDataDay: RemoteDayToDataDay,
    private val remoteNightToDataNight: RemoteNightToDataNight
) : ListMapper<Forecast, DataForecast> {
    override fun map(input: List<Forecast>): List<DataForecast> = with(input) {
        map {
            DataForecast(
                date = it.date,
                day = remoteDayToDataDay.map(it.day),
                night = remoteNightToDataNight.map(it.night)
            )
        }
    }


}