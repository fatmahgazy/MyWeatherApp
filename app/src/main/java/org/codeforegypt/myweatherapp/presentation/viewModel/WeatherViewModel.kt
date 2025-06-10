package org.codeforegypt.myweatherapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.codeforegypt.myweatherapp.data.model.WeatherState
import org.codeforegypt.myweatherapp.domain.repository.WeatherRepository

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherState())
    val weatherState = _weatherState.asStateFlow()

    fun fetchWeather(latitude: Double = 30.4474, longitude: Double = 31.1784) {
        viewModelScope.launch {
            // 1. Set loading state
            _weatherState.value = WeatherState(isLoading = true)

            // 2. Fetch data from the repository
            val result = weatherRepository.getWeatherData(latitude, longitude)

            // 3. Update state based on the result
            result.onSuccess { weatherData ->
                _weatherState.value = WeatherState(weather = weatherData)
            }.onFailure { error ->
                _weatherState.value = WeatherState(errorMessage = error.localizedMessage)
            }
        }
    }
}
