package org.codeforegypt.myweatherapp.domain.model
// package: data.model

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeather,
    val hourly: HourlyWeather,
    val daily: DailyWeatherDto
)

data class CurrentWeather(
    val time: String,
    val temperature_2m: Double,
    val wind_speed_10m: Double,
    val relative_humidity_2m: Int,
    val rain: Double,
    val weather_code: Int,
    val apparent_temperature: Double,
    val pressure_msl: Double,
    val is_day: Int,
    val precipitation: Double
)

data class HourlyWeather(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val weather_code: List<Int>,
    val is_day: List<Int>
)

data class DailyWeatherDto(
    val time: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val weather_code: List<Int>,
    val uv_index_max: List<Double>
)
