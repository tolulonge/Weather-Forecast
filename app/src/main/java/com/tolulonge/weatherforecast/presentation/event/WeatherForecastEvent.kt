package com.tolulonge.weatherforecast.presentation.event

/**
 * Event class to help handle UI interactions
 */
sealed class WeatherForecastEvent {
    object RefreshWeatherForecast : WeatherForecastEvent()
    data class GetForecastByDate(val date: String) : WeatherForecastEvent()
}
