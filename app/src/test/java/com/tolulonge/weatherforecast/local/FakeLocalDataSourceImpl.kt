package com.tolulonge.weatherforecast.local

import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.data.repository.LocalDataSource
import com.tolulonge.weatherforecast.local.mapper.LocalDataForecastListMapper
import com.tolulonge.weatherforecast.local.mapper.SingleDataForecastToLocalForecastMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocalDataSourceImpl(
    private val fakeDatabase: FakeDatabase,
    private val localDataForecastListMapper: LocalDataForecastListMapper,
    private val singleDataForecastToLocalForecastMapper: SingleDataForecastToLocalForecastMapper,
    ): LocalDataSource {


    override suspend fun getAllWeatherForecastDb(): Flow<List<DataForecast>> {
        return flow {
            emit(localDataForecastListMapper.mTo(fakeDatabase.listOfLocalForecast.sortedBy { it.date }))
        }
    }

    override suspend fun insertWeatherForecasts(allWeatherForecasts: List<DataForecast>) {

        fakeDatabase.listOfLocalForecast.addAll(
            localDataForecastListMapper.from(allWeatherForecasts).toMutableList())
    }

    override suspend fun getWeatherForecastByDate(date: String): DataForecast {
        return  singleDataForecastToLocalForecastMapper.mTo(fakeDatabase.listOfLocalForecast.first { it.date == date })
    }

    override suspend fun deleteOldDataFromDb() {
        if(fakeDatabase.listOfLocalForecast.size > 4) {
           fakeDatabase.listOfLocalForecast.subList(0, fakeDatabase.listOfLocalForecast.size - 4).clear()
        }
    }
}