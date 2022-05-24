package com.tolulonge.weatherforecast.remote.api

import com.tolulonge.weatherforecast.remote.model.weatherforecast.RemoteWeatherForecastResponse
import retrofit2.http.GET


interface WeatherForecastApi {

    @GET("api/estonia/forecast")
    suspend fun getWeatherForecast(): RemoteWeatherForecastResponse

    companion object {
        const val BASE_URL = "https://weather.aw.ee/"
    }
}
