package com.tolulonge.weatherforecast.presentation.state

import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import kotlinx.parcelize.Parcelize

/**
 * Sealed for that holds the state of the MainWeatherFragment
 */
sealed class SpecificDayWeatherFragmentUiState {
    object Empty : SpecificDayWeatherFragmentUiState()
    data class Loading(val isLoading: Boolean) : SpecificDayWeatherFragmentUiState()
    data class Loaded(val data: PresentationForecast, val message: String) : SpecificDayWeatherFragmentUiState()
    data class Error(val message: String) : SpecificDayWeatherFragmentUiState()
}

















