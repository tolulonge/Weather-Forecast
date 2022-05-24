package com.tolulonge.weatherforecast.remote.source

import com.tolulonge.weatherforecast.core.util.BaseRemoteRepository
import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.data.repository.RemoteDataSource
import com.tolulonge.weatherforecast.remote.api.WeatherForecastApi
import com.tolulonge.weatherforecast.remote.mapper.RemoteForecastToDataForecastMapper


class RemoteDataSourceImpl(
    private val weatherForecastApi: WeatherForecastApi,
    private val remoteForecastToDataForecastMapper: RemoteForecastToDataForecastMapper
) : RemoteDataSource, BaseRemoteRepository() {

    override suspend fun getRemoteWeatherForecast(): Resource<List<DataForecast>> = safeApiCall {
        remoteForecastToDataForecastMapper.map(weatherForecastApi.getWeatherForecast().forecasts)
    }

}
