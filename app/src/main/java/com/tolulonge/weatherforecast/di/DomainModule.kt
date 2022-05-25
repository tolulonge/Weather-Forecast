package com.tolulonge.weatherforecast.di

import com.tolulonge.weatherforecast.domain.repository.WeatherForecastRepository
import com.tolulonge.weatherforecast.domain.usecases.GetAllWeatherForecast
import com.tolulonge.weatherforecast.domain.usecases.GetWeatherForecastByDate
import com.tolulonge.weatherforecast.domain.usecases.WeatherForecastUseCases
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
    ): WeatherForecastUseCases {
        return WeatherForecastUseCases(
            getAllWeatherForecast = GetAllWeatherForecast(repository),
            getWeatherForecastByDate = GetWeatherForecastByDate(repository)
        )
    }
}
