package com.tolulonge.weatherforecast.local.source

import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.data.repository.LocalDataSource
import com.tolulonge.weatherforecast.local.database.ForecastDao
import com.tolulonge.weatherforecast.local.mapper.LocalDataForecastListMapper
import com.tolulonge.weatherforecast.local.mapper.SingleDataForecastToLocalForecastMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalDataSourceImpl(
    private val localDataForecastListMapper: LocalDataForecastListMapper,
    private val singleDataForecastToLocalForecastMapper: SingleDataForecastToLocalForecastMapper,
    private val forecastDao: ForecastDao,
) : LocalDataSource {

    override suspend fun getAllWeatherForecastDb(): Flow<List<DataForecast>> {
        return forecastDao.getAllWeatherForecasts().map {
            localDataForecastListMapper.mTo(it)
        }
    }

    override suspend fun insertWeatherForecasts(allWeatherForecasts: List<DataForecast>) {
        forecastDao.insertAllWeatherForecasts(localDataForecastListMapper.from(allWeatherForecasts))
    }

    override suspend fun getWeatherForecastByDate(date: String): Flow<DataForecast> {
       return forecastDao.getWeatherForecastByDate(date).map {
            singleDataForecastToLocalForecastMapper.mTo(it)
        }
    }

}
