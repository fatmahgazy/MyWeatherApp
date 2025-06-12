package org.codeforegypt.myweatherapp.data.repository

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.codeforegypt.myweatherapp.data.model.Weather
import org.codeforegypt.myweatherapp.domain.repository.LocationService
import org.codeforegypt.myweatherapp.domain.repository.WeatherRepository


class WeatherRepositoryImpl(private val httpClient: HttpClient) : WeatherRepository {

    private val baseUrl = "https://api.open-meteo.com/v1/forecast"

    override suspend fun getWeatherData(latitude: Double, longitude: Double): Result<Weather> {
        try {
            val response = httpClient.get(baseUrl) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append(
                        "current",
                        "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,weather_code,pressure_msl,wind_speed_10m,uv_index"
                    )
                    parameters.append(
                        "hourly",
                        "temperature_2m,weather_code,precipitation_probability"
                    )
                    parameters.append(
                        "daily",
                        "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max"
                    )
                    parameters.append("wind_speed_unit", "kmh")
                    parameters.append("timezone", "auto")
                }
            }

            Log.d(
                "WeatherApiService",
                "2. API Response received successfully. Status: ${response.status}"
            )
            Log.d("WeatherApiService", "3. Attempting to deserialize response body...")

            val weatherData = response.body<Weather>()

            Log.d("WeatherApiService", "4. SUCCESS! Deserialization complete.")
            return Result.success(weatherData)
        } catch (e: Exception) {
            Log.e("WeatherApiService", "CRITICAL_ERROR: Failed to fetch or parse weather data.", e)
            return Result.failure(Exception("failed"))
        }
    }
}