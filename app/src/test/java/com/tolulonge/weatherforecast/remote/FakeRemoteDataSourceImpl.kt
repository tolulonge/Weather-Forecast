package com.tolulonge.weatherforecast.remote

import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.data.repository.RemoteDataSource


class FakeRemoteDataSourceImpl: RemoteDataSource {

    override suspend fun getRemoteWeatherForecast(): Resource<List<DataForecast>> {
        return Resource.Success(TestRemoteData.listOfDataForecast)
    }

}