package com.tolulonge.weatherforecast.domain.usecases

/**
 * This are use_cases that are used to interact with the domain layer by the presentation layer
 */
class WeatherForecastUseCases(
    val getAllWeatherForecast: GetAllWeatherForecast,
    val getWeatherForecastByDate: GetWeatherForecastByDate,
    val getReadableDate: GetReadableDate,
    val convertTemperatureValueToWords: ConvertTemperatureValueToWords,
    val findTempInStringAndConvert: FindTempInStringAndConvert
)