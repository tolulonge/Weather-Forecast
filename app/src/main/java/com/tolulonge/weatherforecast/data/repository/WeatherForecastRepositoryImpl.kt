package com.tolulonge.weatherforecast.data.repository

import com.tolulonge.weatherforecast.core.util.ApiStatus
import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.mapper.DataForecastToDomainForecastMapper
import com.tolulonge.weatherforecast.domain.mapper.SingleDataForecastToDomainForecastMapper
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow


class WeatherForecastRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dataForecastToDomainForecastMapper: DataForecastToDomainForecastMapper,
    private val singleDataForecastToDomainForecastMapper: SingleDataForecastToDomainForecastMapper,
) : WeatherForecastRepository {

    override fun getAllWeatherForecast(fetchFromRemote: Boolean): Flow<Resource<List<DomainForecast>>> {
        return flow {
            emit(Resource.Loading(true))
            val localWeatherForecasts = localDataSource.getAllWeatherForecastDb()

            // Determines whether to fetch the result from database or proceed to make the remote call

            if (isFetchingResultFromDb(fetchFromRemote, localWeatherForecasts) {
                    dataForecastToDomainForecastMapper.map(
                        localWeatherForecasts
                    )
                }
            ) return@flow
            emit(Resource.Loading(true))
            val response = remoteDataSource.getRemoteWeatherForecast()
            val dataForecastList = retrieveContentFromRemote(response)



            updateLocalFromRemoteAndEmitResult(
                dataForecastList,
                { localDataSource.insertWeatherForecasts(it) },
                {
                    dataForecastToDomainForecastMapper.map(
                        localDataSource.getAllWeatherForecastDb()
                    )
                }
            )
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

    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.isFetchingResultFromDb(
        fetchFromRemote: Boolean,
        localData: List<T>,
        retrieveFromDb: suspend () -> List<E>
    ): Boolean {

        val isDbEmpty = checkIfDatabaseIsEmpty(localData)
        val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
        if (shouldJustLoadFromCache) {
            emit(Resource.Loading(false))
            emit(
                Resource.Success(
                    data = retrieveFromDb.invoke(),
                    message = null
                )
            )
            return true
        }
        return false
    }


    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.retrieveContentFromRemote(
        response: Resource<List<T>>
    ): List<T>? {
        val result = when (response.status) {
            ApiStatus.SUCCESS -> {
                response.data
            }
            ApiStatus.ERROR -> {
                response.message?.let {
                    emit(Resource.Error(it))
                } ?: emit(Resource.Error("An unknown error occurred"))
                null
            }
            ApiStatus.LOADING -> {
                null
            }
        }
        return result
    }

    override suspend fun <E, T> FlowCollector<Resource<List<E>>>.updateLocalFromRemoteAndEmitResult(
        remoteResult: List<T>?,
        insertToDb: suspend ((List<T>) -> Unit),
        retrieveFromDb: suspend () -> List<E>
    ) {
        remoteResult?.let { results ->
            insertToDb.invoke(results)
            emit(Resource.Loading(false))
            emit(
                Resource.Success(
                    data = retrieveFromDb.invoke(),
                    message = null
                )
            )
        }
    }

    override fun <T> checkIfDatabaseIsEmpty(
        localData: List<T>
    ): Boolean {
        return localData.isEmpty()
    }


}
