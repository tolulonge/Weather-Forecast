package com.tolulonge.weatherforecast.data.repository

import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.data.model.DataForecast


interface RemoteDataSource {

    suspend fun getRemoteWeatherForecast(): Resource<List<DataForecast>>

}
