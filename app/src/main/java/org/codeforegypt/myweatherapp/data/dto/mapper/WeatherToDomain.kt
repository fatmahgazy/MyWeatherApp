package org.codeforegypt.myweatherapp.data.dto.mapper

import org.codeforegypt.myweatherapp.data.model.Weather
import org.codeforegypt.myweatherapp.domain.model.CurrentWeather
import org.codeforegypt.myweatherapp.domain.model.DailyWeather
import org.codeforegypt.myweatherapp.domain.model.HourlyWeather
import org.codeforegypt.myweatherapp.domain.model.WeatherInfo
import org.codeforegypt.myweatherapp.presentation.utils.WeatherCodeUtils

fun Weather.toWeatherInfo(): WeatherInfo {
    val isDay = current.is_day == 1

    val current = CurrentWeather(
        description = WeatherCodeUtils.getWeatherDescription(current.weather_code, isDay),
        temperature = current.temperature_2m,
        feelsLike = current.apparent_temperature,
        windSpeed = current.wind_speed_10m,
        humidity = current.relative_humidity_2m,
        rainChance = current.precipitation_probability,
        uvIndex = current.uv_index,
        pressure = current.pressure_msl,
        weatherCode = current.weather_code,
        isDay = isDay
    )

    val hourlyForecast = hourly.time.mapIndexed { index, time ->
        HourlyWeather(
            time = time,
            temperature = hourly.temperature_2m.getOrNull(index) ?: 0.0,
                weatherCode = hourly.weather_code.getOrNull(index) ?: 0
            )
        }


    val dailyForecast = daily.time.mapIndexed { index, date ->
        DailyWeather(
            date = date,
            maxTemp = daily.temperature_2m_max.getOrNull(index) ?: 0.0,
            minTemp = daily.temperature_2m_min.getOrNull(index) ?: 0.0,
            weatherCode = daily.weather_code.getOrNull(index) ?: 0
        )
    }

    return WeatherInfo(
        currentWeather = current,
        hourlyForecast = hourlyForecast,
        dailyForecast = dailyForecast
    )
}
