package org.codeforegypt.myweatherapp.presentation.utils


object WeatherCodeUtils {

    fun getWeatherDescription(weatherCode: Int, isDay: Boolean): String {
        return when (weatherCode) {
            0 -> if (isDay) "Clear sky" else "Clear night"
            1, 2 -> "Mainly clear"
            3 -> "Partly cloudy"
            45, 48 -> "Fog"
            51, 53, 55 -> "Drizzle"
            61, 63, 65 -> "Rain"
            71, 73, 75 -> "Snow"
            95, 96, 99 -> "Thunderstorm"
            else -> "Unknown"
        }
    }

}
