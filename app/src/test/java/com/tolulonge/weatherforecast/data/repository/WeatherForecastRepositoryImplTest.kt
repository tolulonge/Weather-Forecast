package com.tolulonge.weatherforecast.data.repository

import com.google.common.truth.Truth.assertThat
import com.tolulonge.weatherforecast.core.util.ApiStatus
import com.tolulonge.weatherforecast.domain.mapper.*
import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import com.tolulonge.weatherforecast.local.FakeDatabase
import com.tolulonge.weatherforecast.local.FakeLocalDataSourceImpl
import com.tolulonge.weatherforecast.local.mapper.*
import com.tolulonge.weatherforecast.remote.FakeRemoteDataSourceImpl
import com.tolulonge.weatherforecast.remote.api.WeatherForecastApi
import com.tolulonge.weatherforecast.remote.mapper.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class WeatherForecastRepositoryImplTest {

    private lateinit var remoteDatasource: RemoteDataSource
    private lateinit var localDataSource: LocalDataSource
    private lateinit var repository: WeatherForecastRepository


    @Before
    fun setUp() {

        val dataPlaceToDomainPlace = DataPlaceToDomainPlace()
        val dataWindToDomainWind = DataWindToDomainWind()
        val dataDayToDomainDay = DataDayToDomainDay(dataPlaceToDomainPlace, dataWindToDomainWind)
        val dataNightToDomainNight =
            DataNightToDomainNight(dataPlaceToDomainPlace, dataWindToDomainWind)
        val localDataPlace = LocalDataPlace()
        val localDataWind = LocalDataWind()
        val localDataDay = LocalDataDay(localDataPlace, localDataWind)
        val localDataNight = LocalDataNight(localDataPlace, localDataWind)
        val dataForecastToDomainForecastMapper = DataForecastToDomainForecastMapper(
            dataDayToDomainDay, dataNightToDomainNight
        )
        remoteDatasource = FakeRemoteDataSourceImpl()
        localDataSource = FakeLocalDataSourceImpl(
            fakeDatabase = FakeDatabase(),
            localDataForecastListMapper = LocalDataForecastListMapper(
                localDataDay, localDataNight
            ),
            singleDataForecastToLocalForecastMapper = SingleDataForecastToLocalForecastMapper(
                localDataDay, localDataNight
            ),
        )

        repository = WeatherForecastRepositoryImpl(
            remoteDataSource = remoteDatasource,
            localDataSource = localDataSource,
            dataForecastToDomainForecastMapper = dataForecastToDomainForecastMapper,
            singleDataForecastToDomainForecastMapper = SingleDataForecastToDomainForecastMapper(
                dataDayToDomainDay,
                dataNightToDomainNight
            )
        )

    }


    @Test
    fun `fetch result from database returns expected result`() {
        runBlocking {
            val response = repository.getAllWeatherForecast().first()

            assertThat(response.data?.get(0)?.date).isEqualTo("2022-05-17")
        }
    }

    @Test
    fun `should return weather forecast by date`() {
        runBlocking {
            val date = "2022-05-19"
            val response = repository.getWeatherForecastByDate(date).last()

            assertThat(response.data?.day?.phenomenon).isEqualTo("Risk of glaze")
        }
    }


}