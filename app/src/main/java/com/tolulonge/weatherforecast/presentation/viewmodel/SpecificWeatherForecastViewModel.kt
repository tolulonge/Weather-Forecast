package com.tolulonge.weatherforecast.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolulonge.weatherforecast.core.util.Resource
import com.tolulonge.weatherforecast.domain.usecases.WeatherForecastUseCases
import com.tolulonge.weatherforecast.presentation.event.WeatherForecastEvent
import com.tolulonge.weatherforecast.presentation.mapper.SingleDomainForecastToPresentationForecastMapper
import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificWeatherForecastViewModel @Inject constructor(
    private val weatherForecastUseCases: WeatherForecastUseCases,
    private val singleDomainForecastToPresentationForecastMapper: SingleDomainForecastToPresentationForecastMapper
): ViewModel() {


    private val _specificDayWeather = MutableStateFlow(PresentationForecast(null,null,null))
    val specificDayWeather = _specificDayWeather.asStateFlow()



    fun onEvent(event: WeatherForecastEvent) {
        when (event) {
            is WeatherForecastEvent.GetForecastByDate -> {
                getSpecificDayWeatherForecast(event.date)
            }
            else -> {}
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
            weatherForecastUseCases.getWeatherForecastByDate(weatherForecastUseCases.getDefaultDateFormat(date))
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { forecast ->
                                _specificDayWeather.value =
                                    modifyPresentationForecast(
                                    singleDomainForecastToPresentationForecastMapper.map(
                                     forecast
                                ))
                            }
                        }
                        else -> {}
                    }
                }
        }
    }


    private fun modifyPresentationForecast(presentationForecast: PresentationForecast) : PresentationForecast{
        return  presentationForecast.copy(
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
    }

}