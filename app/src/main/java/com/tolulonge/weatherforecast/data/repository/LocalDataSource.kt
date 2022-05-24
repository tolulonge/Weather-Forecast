package com.tolulonge.weatherforecast.data.repository

import com.tolulonge.weatherforecast.data.model.DataForecast


interface LocalDataSource {

    suspend fun getAllWeatherForecastDb(): List<DataForecast>

    suspend fun insertWeatherForecasts(allWeatherForecasts: List<DataForecast>)

    suspend fun getWeatherForecastByDate(date: String): DataForecast

}
