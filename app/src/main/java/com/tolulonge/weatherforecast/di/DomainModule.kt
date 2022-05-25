package com.tolulonge.weatherforecast.di

import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import com.tolulonge.weatherforecast.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideWeatherForecastUseCases(
        repository: WeatherForecastRepository,
        convertTemperatureValueToWords: ConvertTemperatureValueToWords
    ): WeatherForecastUseCases {
        return WeatherForecastUseCases(
            getAllWeatherForecast = GetAllWeatherForecast(repository),
            getWeatherForecastByDate = GetWeatherForecastByDate(repository),
            getReadableDate = GetReadableDate(),
            convertTemperatureValueToWords = convertTemperatureValueToWords,
            findTempInStringAndConvert = FindTempInStringAndConvert(convertTemperatureValueToWords)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideConvertTemperatureValueToWordsUseCase(
    ): ConvertTemperatureValueToWords {
        return ConvertTemperatureValueToWords()
    }


}
