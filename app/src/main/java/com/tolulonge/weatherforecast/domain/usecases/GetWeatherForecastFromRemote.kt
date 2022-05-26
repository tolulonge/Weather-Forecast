package com.tolulonge.weatherforecast.domain.usecases

import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherForecastFromRemote(
    private val repository: WeatherForecastRepository
) {

    suspend operator fun invoke(): Flow<Resource<String>> {
        return repository.fetchFromRemoteAndUpdateDb()
    }
}
