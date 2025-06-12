package org.codeforegypt.myweatherapp.presentation.model

data class WeatherUiModel(
    val currentTemp: Double,
    val description: String,
    val maxTemp: Double,
    val minTemp: Double,
    val isDay: Int,
    val weatherCode: Int,
    val hourlyTemps: List<Double>,
    val next7Days: List<DayForecast>
)

data class DayForecast(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherCode: Int
)
