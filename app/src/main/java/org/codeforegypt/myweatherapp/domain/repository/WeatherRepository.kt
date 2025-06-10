package org.codeforegypt.myweatherapp.domain.repository

import org.codeforegypt.myweatherapp.data.model.Weather

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Result<Weather>
}