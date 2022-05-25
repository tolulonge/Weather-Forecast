package com.tolulonge.weatherforecast.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.usecases.WeatherForecastUseCases
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.mapper.DomainForecastToPresentationForecastMapper
import com.tolulonge.weatherforecast.presentation.mapper.SingleDomainForecastToPresentationForecastMapper
import com.tolulonge.weatherforecast.presentation.state.MainWeatherFragmentUiState
import com.tolulonge.weatherforecast.presentation.state.SpecificDayWeatherFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainWeatherViewModel @Inject constructor(
    private val weatherForecastUseCases: WeatherForecastUseCases,
    private val domainForecastToPresentationForecastMapper: DomainForecastToPresentationForecastMapper,
    private val singleDomainForecastToPresentationForecastMapper: SingleDomainForecastToPresentationForecastMapper
): ViewModel() {

    private val _allWeatherForecast =
        MutableStateFlow<MainWeatherFragmentUiState>(MainWeatherFragmentUiState.Empty)
    val allWeatherForecast = _allWeatherForecast.asStateFlow()


    private val _specificDayWeather =
        MutableStateFlow<SpecificDayWeatherFragmentUiState>(SpecificDayWeatherFragmentUiState.Empty)
    val specificDayWeather = _specificDayWeather.asStateFlow()


    init {
        getWeatherForecasts(true)
    }

    fun onEvent(event: WeatherForecastEvent) {
        when (event) {
            is WeatherForecastEvent.RefreshWeatherForecast -> getWeatherForecasts(true)
            is WeatherForecastEvent.GetForecastByDate -> {
                getSpecificDayWeatherForecast(event.date)
            }
        }
    }

    /**
     * Emits respective flow based on response from remote or database
     */
    private fun getWeatherForecasts(fetchFromRemote: Boolean) {
        viewModelScope.launch {
            weatherForecastUseCases.getAllWeatherForecast(fetchFromRemote)
                .collect {
                        result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { forecasts ->
                                _allWeatherForecast.value = MainWeatherFragmentUiState.Loaded(
                                    domainForecastToPresentationForecastMapper.map(
                                        forecasts
                                    ).map { presentationForecast ->
                                        presentationForecast.copy(
                                            date = weatherForecastUseCases.getReadableDate(presentationForecast.date),
                                            day = presentationForecast.day?.copy(
                                                tempmax = convertTempValueToWords(presentationForecast.day.tempmax),
                                                tempmin = convertTempValueToWords(presentationForecast.day.tempmin),
                                                places = presentationForecast.day.places?.map {
                                                    it.copy(
                                                        tempmax = convertTempValueToWords(it.tempmax),
                                                        tempmin = convertTempValueToWords(it.tempmin)
                                                    )
                                                },
                                                peipsi = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.day.peipsi),
                                                sea = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.day.sea),
                                                text = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.day.text)
                                            ),
                                            night = presentationForecast.night?.copy(
                                                tempmax = convertTempValueToWords(
                                                    presentationForecast.night.tempmax
                                                ),
                                                tempmin = convertTempValueToWords(
                                                    presentationForecast.night.tempmin
                                                ),
                                                places = presentationForecast.night.places?.map {
                                                    it.copy(
                                                        tempmax = convertTempValueToWords(it.tempmax),
                                                        tempmin = convertTempValueToWords(it.tempmin)
                                                    )
                                                },
                                                peipsi = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.night.peipsi),
                                                sea = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.night.sea),
                                                text = weatherForecastUseCases.findTempInStringAndConvert(presentationForecast.night.text)
                                            )
                                        )

                                    },
                                    result.message ?: ""
                                )
                                }
                            }
                        is Resource.Error -> {
                            _allWeatherForecast.value =
                                MainWeatherFragmentUiState.Error(result.message ?: "")
                            _allWeatherForecast.value = MainWeatherFragmentUiState.Loading(false)

                        }
                        is Resource.Loading -> {
                            _allWeatherForecast.value = MainWeatherFragmentUiState.Loading(result.isLoading)
                        }
                    }
                }
        }
    }

    private fun convertTempValueToWords(tempValue: String?): String?{
        val tempValueInInt = tempValue?.toDouble()?.toInt() ?: return null
        return weatherForecastUseCases.convertTemperatureValueToWords(tempValueInInt)
    }

    /**
     * Emits respective flow based on response from remote or database
     */
    private fun getSpecificDayWeatherForecast(date: String) {
        viewModelScope.launch {
            weatherForecastUseCases.getWeatherForecastByDate(date)
                .collect {
                        result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { forecast ->
                                _specificDayWeather.value = SpecificDayWeatherFragmentUiState.Loaded(
                                    singleDomainForecastToPresentationForecastMapper.map(
                                        forecast
                                    ),
                                    result.message ?: ""
                                )
                            }
                        }
                        is Resource.Error -> {
                            _specificDayWeather.value =
                                SpecificDayWeatherFragmentUiState.Error(result.message ?: "")
                            _specificDayWeather.value = SpecificDayWeatherFragmentUiState.Loading(false)

                        }
                        is Resource.Loading -> {
                            _specificDayWeather.value = SpecificDayWeatherFragmentUiState.Loading(result.isLoading)
                        }
                    }
                }
        }
    }

}