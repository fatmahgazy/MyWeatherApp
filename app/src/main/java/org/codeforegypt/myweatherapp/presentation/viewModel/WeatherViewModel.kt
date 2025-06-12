package org.codeforegypt.myweatherapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeforegypt.myweatherapp.data.dto.mapper.toWeatherInfo
import org.codeforegypt.myweatherapp.domain.repository.LocationService
import org.codeforegypt.myweatherapp.domain.repository.WeatherRepository
import org.codeforegypt.myweatherapp.presentation.model.WeatherUiState

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationService: LocationService
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherUiState())
    val weatherState = _weatherState.asStateFlow()

     fun fetchWeather() {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState(isLoading = true)
            val location = locationService.getCurrentLocation()
            if (location != null) {
                val city = locationService.getCityName(location) ?: "Unknown"
                val result = weatherRepository.getWeatherData(location.latitude, location.longitude)
                result.fold(onSuccess = { dto ->
                    val weatherInfo = dto.toWeatherInfo()
                    _weatherState.value = WeatherUiState(weather = weatherInfo, cityName = city, isLoading = false)
                }, onFailure = { e ->
                    _weatherState.value = WeatherUiState(error = "Failed to load weather: ${e.message}", isLoading = false)
                })
            } else {
                _weatherState.value = WeatherUiState(error = "Location permission not granted or unavailable", isLoading = false)
            }
        }
    }
}
