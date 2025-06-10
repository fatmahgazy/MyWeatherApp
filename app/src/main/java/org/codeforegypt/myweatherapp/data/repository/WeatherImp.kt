package org.codeforegypt.myweatherapp.data.repository

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.codeforegypt.myweatherapp.data.model.Weather
import org.codeforegypt.myweatherapp.domain.repository.WeatherRepository


class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    private val baseUrl = "https://api.open-meteo.com/v1/forecast"

    override suspend fun getWeatherData(latitude: Double, longitude: Double): Result<Weather> {
        return try {
            val response = httpClient.get(baseUrl) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append("current", "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,weather_code,pressure_msl,wind_speed_10m,uv_index")
                    parameters.append("hourly", "temperature_2m,weather_code,precipitation_probability")
                    parameters.append("daily", "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max")
                    parameters.append("wind_speed_unit", "kmh")
                    parameters.append("timezone", "auto")
                }
            }.body<Weather>()
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}