package com.tolulonge.weatherforecast.domain.usecases

import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherForecastByDate(
    private val repository: WeatherForecastRepository
) {

    operator fun invoke(date: String): Flow<Resource<DomainForecast>> {
        return repository.getWeatherForecastByDate(date)
    }
}
