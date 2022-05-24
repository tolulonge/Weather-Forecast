package com.tolulonge.weatherforecast.local.source

import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.data.repository.LocalDataSource
import com.tolulonge.weatherforecast.local.database.ForecastDao
import com.tolulonge.weatherforecast.local.mapper.LocalDataForecastListMapper
import com.tolulonge.weatherforecast.local.mapper.SingleDataForecastToLocalForecastMapper


class LocalDataSourceImpl(
    private val localDataForecastListMapper: LocalDataForecastListMapper,
    private val singleDataForecastToLocalForecastMapper: SingleDataForecastToLocalForecastMapper,
    private val forecastDao: ForecastDao,
) : LocalDataSource {

    override suspend fun getAllWeatherForecastDb(): List<DataForecast> {
        return localDataForecastListMapper.mTo(forecastDao.getAllWeatherForecasts())
    }

    override suspend fun insertWeatherForecasts(allWeatherForecasts: List<DataForecast>) {
        forecastDao.insertAllWeatherForecasts(localDataForecastListMapper.from(allWeatherForecasts))
    }

    override suspend fun getWeatherForecastByDate(date: String): DataForecast {
        return singleDataForecastToLocalForecastMapper.mTo(forecastDao.getWeatherForecastByDate(date))
    }

}
