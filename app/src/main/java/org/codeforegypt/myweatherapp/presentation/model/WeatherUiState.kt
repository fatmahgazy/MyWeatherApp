package org.codeforegypt.myweatherapp.presentation.model

import org.codeforegypt.myweatherapp.domain.model.WeatherInfo

data class WeatherUiState(
    val weather: WeatherInfo? = null,
    val cityName: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)