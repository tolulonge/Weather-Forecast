package com.tolulonge.weatherforecast.local

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.tolulonge.weatherforecast.core.util.parser.GsonParser
import com.tolulonge.weatherforecast.core.util.parser.JsonParser
import com.tolulonge.weatherforecast.core.util.readFileFromAssets
import com.tolulonge.weatherforecast.data.model.DataForecast
import com.tolulonge.weatherforecast.local.converter.Converters
import com.tolulonge.weatherforecast.local.database.ForecastDao
import com.tolulonge.weatherforecast.local.database.WeatherForecastDatabase
import com.tolulonge.weatherforecast.local.entities.LocalForecast
import com.tolulonge.weatherforecast.model.LocalForecastTest
import com.tolulonge.weatherforecast.remote.model.weatherforecast.RemoteWeatherForecastResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ForecastDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()



    private lateinit var database: WeatherForecastDatabase
    private lateinit var dao: ForecastDao
    private lateinit var jsonResponse : LocalForecastTest

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().context

        val gson = Gson()
        val jsonParser = GsonParser(gson)
        val converter = Converters(
            jsonParser
        )
        val jsonBody = readFileFromAssets("weather_response.json", appContext)
        jsonResponse = gson.fromJson(jsonBody, LocalForecastTest::class.java)
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherForecastDatabase::class.java
        ).allowMainThreadQueries()
            .addTypeConverter(converter)
            .build()
        dao = database.forecastDao
    }


    @Test
    fun insertAllWeatherForecast() = runTest {
        val forecasts = jsonResponse.listOfLocalForecast.take(2)
        dao.insertAllWeatherForecasts(forecasts)

       val allWeatherForecasts = dao.getAllWeatherForecasts()

        ///assertThat(allWeatherForecasts).contains(forecasts)
    }

    @After
    fun teardown() {
        database.close()
    }

}