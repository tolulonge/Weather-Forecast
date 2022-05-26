package com.tolulonge.weatherforecast.data.repository

import android.util.Log
import com.tolulonge.weatherforecast.core.util.ApiStatus
import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.mapper.DataForecastToDomainForecastMapper
import com.tolulonge.weatherforecast.domain.mapper.SingleDataForecastToDomainForecastMapper
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class WeatherForecastRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dataForecastToDomainForecastMapper: DataForecastToDomainForecastMapper,
    private val singleDataForecastToDomainForecastMapper: SingleDataForecastToDomainForecastMapper,
) : WeatherForecastRepository {

    override suspend fun getAllWeatherForecast(): Flow<Resource<List<DomainForecast>>> {
        return localDataSource.getAllWeatherForecastDb()
            .map { Resource.Success(dataForecastToDomainForecastMapper.map(it)) }
    }

    override suspend fun fetchFromRemoteAndUpdateDb() : Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading(true, "Refreshing..."))
           val response = remoteDataSource.getRemoteWeatherForecast()
           val dataForecastList = when (response.status) {
               ApiStatus.SUCCESS -> {
                   emit(Resource.Success("Refresh Successful"))
                   response.data
               }
               ApiStatus.ERROR -> {
                   response.message?.let {
                       emit(Resource.Error(it))
                   } ?: emit(Resource.Error("Can't Refresh Weather Now, Check your internet connection"))
                   null
               }
               ApiStatus.LOADING -> {
                   null
               }
           }

           dataForecastList?.let { results ->
              localDataSource.insertWeatherForecasts(results)
               localDataSource.deleteOldDataFromDb()
               emit(Resource.Loading(false))
           }
           emit(Resource.Loading(false))
       }
    }

    override fun getWeatherForecastByDate(date: String): Flow<Resource<DomainForecast>> {
        return flow {
            emit(Resource.Loading(true))
            val localWeatherForecast = localDataSource.getWeatherForecastByDate(date)
            emit(Resource.Loading(false))
            emit(Resource.Success(
                    singleDataForecastToDomainForecastMapper.map(localWeatherForecast)
                ))

        }
    }


}
