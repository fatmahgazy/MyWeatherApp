package org.codeforegypt.myweatherapp.data.model

import kotlinx.serialization.Serializable
import org.codeforegypt.myweatherapp.domain.model.WeatherInfo

@Serializable
data class Weather(
    val current: Current,
    val current_units: CurrentUnits,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)

@Serializable
data class Current(
    val apparent_temperature: Double,
    val interval: Int,
    val is_day: Int,
    val precipitation_probability: Int,
    val pressure_msl: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val uv_index: Double? = null,
    val weather_code: Int,
    val wind_speed_10m: Double
)

@Serializable
data class CurrentUnits(
    val apparent_temperature: String,
    val interval: String,
    val is_day: String,
    val precipitation_probability: String,
    val pressure_msl: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val uv_index: String,
    val weather_code: String,
    val wind_speed_10m: String
)

@Serializable
data class Daily(
    val precipitation_probability_max: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)

@Serializable
data class DailyUnits(
    val precipitation_probability_max: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val weather_code: String
)

@Serializable
data class Hourly(
    val precipitation_probability: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>
)

@Serializable
data class HourlyUnits(
    val precipitation_probability: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String
)


