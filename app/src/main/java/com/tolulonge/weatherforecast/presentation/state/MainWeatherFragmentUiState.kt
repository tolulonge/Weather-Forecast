package com.tolulonge.weatherforecast.presentation.state

import com.tolulonge.weatherforecast.presentation.state.model.PresentationForecast
import kotlinx.parcelize.Parcelize

/**
 * Sealed for that holds the state of the MainWeatherFragment
 */
sealed class MainWeatherFragmentUiState {
    object Empty : MainWeatherFragmentUiState()
    data class Loading(val isLoading: Boolean, val message: String = "") : MainWeatherFragmentUiState()
    data class Loaded(val data: String) : MainWeatherFragmentUiState()
    data class Error(val message: String) : MainWeatherFragmentUiState()
}

















