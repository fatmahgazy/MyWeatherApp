//package org.codeforegypt.myweatherapp.data.dto.mapper
//
//
//import org.codeforegypt.myweatherapp.data.model.Current
//import org.codeforegypt.myweatherapp.data.model.Daily
//import org.codeforegypt.myweatherapp.data.model.Hourly
//import org.codeforegypt.myweatherapp.data.model.Weather
//import org.codeforegypt.myweatherapp.data.model.CurrentWeather
//import org.codeforegypt.myweatherapp.data.model.DailyWeather
//import org.codeforegypt.myweatherapp.data.model.HourlyWeather
//import org.codeforegypt.myweatherapp.data.model.WeatherResponse
//
//fun Weather.toModel(): WeatherResponse {
//    return WeatherResponse(
//        latitude = latitude,
//        longitude = longitude,
//        current = current.toModel(),
//        hourly = hourly.toModel(),
//        daily = daily.toModel()
//    )
//}
//
//fun Current.toModel(): CurrentWeather {
//    return CurrentWeather(
//        time = time,
//        temperature_2m = temperature_2m,
//        wind_speed_10m = wind_speed_10m,
//        relative_humidity_2m = relative_humidity_2m,
//        weather_code = weather_code,
//        apparent_temperature = apparent_temperature,
//        pressure_msl = pressure_msl,
//        is_day = is_day,
//        precipitation_probability = precipitation_probability,
//        uv_index = uv_index
//    )
//}
//
//fun Hourly.toModel(): HourlyWeather {
//    return HourlyWeather(
//        time = time,
//        temperature_2m = temperature_2m,
//        weather_code = weather_code,
//        precipitation_probability = precipitation_probability
//    )
//}
//
//fun Daily.toModel(): DailyWeather {
//    return DailyWeather(
//        time = time,
//        temperature_2m_max = temperature_2m_max,
//        temperature_2m_min = temperature_2m_min,
//        weather_code = weather_code,
//        precipitation_probability_max = precipitation_probability_max
//    )
//}
//
//data class WeatherState(
//    val weather: Weather? = null,
//    val isLoading: Boolean = false,
//    val errorMessage: String? = null
//)