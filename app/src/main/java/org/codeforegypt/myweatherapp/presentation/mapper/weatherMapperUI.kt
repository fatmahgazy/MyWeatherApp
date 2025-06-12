package org.codeforegypt.myweatherapp.presentation.mapper

import org.codeforegypt.myweatherapp.data.model.Weather
import org.codeforegypt.myweatherapp.presentation.model.DayForecast
import org.codeforegypt.myweatherapp.presentation.model.WeatherUiModel
import org.codeforegypt.myweatherapp.presentation.utils.WeatherCodeUtils.getWeatherDescription

fun Weather.toUiModel(): WeatherUiModel {
    return WeatherUiModel(
        currentTemp = current.temperature_2m,
        description = getWeatherDescription(current.weather_code, current.is_day == 1),
        maxTemp = daily.temperature_2m_max.firstOrNull() ?: 0.0,
        minTemp = daily.temperature_2m_min.firstOrNull() ?: 0.0,
        isDay = current.is_day,
        weatherCode = current.weather_code,
        hourlyTemps = hourly.temperature_2m.take(24),
        next7Days = daily.time.indices.map { i ->
            DayForecast(
                date = daily.time[i],
                maxTemp = daily.temperature_2m_max[i],
                minTemp = daily.temperature_2m_min[i],
                weatherCode = daily.weather_code[i]
            )
        }
    )
}
