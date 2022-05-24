package com.tolulonge.weatherforecast.domain.repository


import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.model.DomainForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

interface WeatherForecastRepository {

    fun getAllWeatherForecast(fetchFromRemote: Boolean): Flow<Resource<List<DomainForecast>>>

    fun getWeatherForecastByDate(date: String): Flow<Resource<DomainForecast>>

    suspend fun <E, T> FlowCollector<Resource<List<E>>>.isFetchingResultFromDb(
        fetchFromRemote: Boolean,
        localData: List<T>,
        retrieveFromDb: suspend () -> List<E>
    ): Boolean

    suspend fun <E, T> FlowCollector<Resource<List<E>>>.retrieveContentFromRemote(response: Resource<List<T>>): List<T>?

    suspend fun <E, T> FlowCollector<Resource<List<E>>>.updateLocalFromRemoteAndEmitResult(
        remoteResult: List<T>?,
        insertToDb: suspend (List<T>) -> Unit,
        retrieveFromDb: suspend () -> List<E>
    )

    fun <T> checkIfDatabaseIsEmpty(localData: List<T>): Boolean
}
