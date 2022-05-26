package com.tolulonge.weatherforecast.domain.repository


import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {

    suspend fun getAllWeatherForecast(): Flow<Resource<List<DomainForecast>>>

    fun getWeatherForecastByDate(date: String): Flow<Resource<DomainForecast>>

    suspend fun fetchFromRemoteAndUpdateDb(): Flow<Resource<String>>
}
