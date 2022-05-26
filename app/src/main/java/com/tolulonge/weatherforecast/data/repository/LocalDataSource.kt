package com.tolulonge.weatherforecast.data.repository

import com.tolulonge.weatherforecast.data.model.DataForecast
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {

    suspend fun getAllWeatherForecastDb(): Flow<List<DataForecast>>

    suspend fun insertWeatherForecasts(allWeatherForecasts: List<DataForecast>)

    suspend fun getWeatherForecastByDate(date: String): DataForecast

    suspend fun deleteOldDataFromDb()

}
