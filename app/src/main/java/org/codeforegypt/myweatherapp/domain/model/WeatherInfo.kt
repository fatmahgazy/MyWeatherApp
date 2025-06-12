package org.codeforegypt.myweatherapp.domain.model


data class WeatherInfo(
    val currentWeather: CurrentWeather,
    val hourlyForecast: List<HourlyWeather>,
    val dailyForecast: List<DailyWeather>
)

data class CurrentWeather(
    val description: String,
    val temperature: Double,
    val feelsLike: Double,
    val windSpeed: Double,
    val humidity: Int,
    val rainChance: Int,
    val uvIndex: Double?,
    val pressure: Double,
    val weatherCode: Int,
    val isDay: Boolean
)

data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val weatherCode: Int
)

data class DailyWeather(
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherCode: Int
)

