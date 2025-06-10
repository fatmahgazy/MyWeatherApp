package org.codeforegypt.myweatherapp.domain.usecase

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.codeforegypt.myweatherapp.data.model.Weather

class GetWeatherUseCase(private val client: HttpClient) {
    suspend fun fetchWeather(lat: Double, lon: Double): Weather {
        return client.get("https://api.open-meteo.com/v1/forecast") {
            parameter("latitude", lat)
            parameter("longitude", lon)
            parameter("daily", "temperature_2m_max,temperature_2m_min,weather_code")
            parameter("hourly", "temperature_2m,uv_index,relative_humidity_2m")
            parameter("current", "temperature_2m,rain,wind_speed_10m,relative_humidity_2m")
            parameter("timezone", "auto")
        }.body()
    }
}
