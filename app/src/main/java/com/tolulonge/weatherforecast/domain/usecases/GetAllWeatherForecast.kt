package com.tolulonge.weatherforecast.domain.usecases

import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow

class GetAllWeatherForecast(
    private val repository: WeatherForecastRepository
) {

    operator fun invoke(fetchDataFromRemote: Boolean): Flow<Resource<List<DomainForecast>>> {
        return repository.getAllWeatherForecast(fetchDataFromRemote)
    }
}
