//package org.codeforegypt.myweatherapp.presentation.screen
//
//import android.app.Application
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Divider
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberMultiplePermissionsState
//import org.codeforegypt.myweatherapp.Manifest
//import org.codeforegypt.myweatherapp.data.model.CurrentWeather
//import org.codeforegypt.myweatherapp.data.model.DailyWeather
//import org.codeforegypt.myweatherapp.data.model.HourlyWeather
//import org.codeforegypt.myweatherapp.data.model.WeatherResponse
//import org.codeforegypt.myweatherapp.di.appModule.appModule
//import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
//import org.koin.android.ext.koin.androidContext
//import org.koin.android.ext.koin.androidLogger
//import org.koin.androidx.compose.koinViewModel
//import org.koin.core.context.startKoin
//
//// Weather App Composable
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun WeatherApp() {
//    val locationPermissions = rememberMultiplePermissionsState(
//        permissions = listOf(
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        )
//    )
//
//    LaunchedEffect(Unit) {
//        locationPermissions.launchMultiplePermissionRequest()
//    }
//
//    when {
//        locationPermissions.allPermissionsGranted -> {
//            WeatherScreen()
//        }
//        locationPermissions.shouldShowRationale -> {
//            PermissionRationaleScreen {
//                locationPermissions.launchMultiplePermissionRequest()
//            }
//        }
//        else -> {
//            PermissionDeniedScreen()
//        }
//    }
//}
//
//@Composable
//fun WeatherScreen() {
//    val viewModel: WeatherViewModel = koinViewModel()
//    val uiState by viewModel.uiState.collectAsState()
//
//    LaunchedEffect(Unit) {
//        viewModel.loadWeatherData()
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text(
//            text = "Weather App",
//            style = MaterialTheme.typography.headlineMedium,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        when {
//            uiState.isLoading -> {
//                Box(
//                    modifier = Modifier.fillMaxWidth(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
//
//            uiState.error != null -> {
//                Card(
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
//                ) {
//                    Text(
//                        text = uiState.error!!,
//                        modifier = Modifier.padding(16.dp),
//                        color = MaterialTheme.colorScheme.onErrorContainer
//                    )
//                }
//
//                Button(
//                    onClick = { viewModel.loadWeatherData() },
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                ) {
//                    Text("Retry")
//                }
//            }
//
//            else -> {
//                uiState.weatherData?.let { weatherData ->
//                    WeatherContent(weatherData = weatherData)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun WeatherContent(weatherData: WeatherResponse) {
//    LazyColumn(
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        // Current Weather
//        item {
//            CurrentWeatherCard(current = weatherData.current)
//        }
//
//        // Today's Hourly Weather (next 24 hours)
//        item {
//            TodayWeatherCard(hourly = weatherData.hourly)
//        }
//
//        // Next 7 Days
//        item {
//            WeeklyWeatherCard(daily = weatherData.daily)
//        }
//    }
//}
//
//@Composable
//fun CurrentWeatherCard(current: CurrentWeather) {
//    Card(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = "Current Weather",
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            Text("Temperature: ${current.temperature_2m.toInt()}°C")
//            Text("Feels like: ${current.apparent_temperature.toInt()}°C")
//            Text("Humidity: ${current.relative_humidity_2m}%")
//            Text("Wind Speed: ${current.wind_speed_10m} km/h")
//            Text("Pressure: ${current.pressure_msl.toInt()} hPa")
//            Text("Rain: ${current.rain} mm")
//        }
//    }
//}
//
//@Composable
//fun TodayWeatherCard(hourly: HourlyWeather) {
//    Card(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = "Today's Hourly Weather",
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(minOf(24, hourly.time.size)) { index ->
//                    HourlyWeatherItem(
//                        time = hourly.time[index],
//                        temperature = hourly.temperature_2m[index],
//                        weatherCode = hourly.weather_code[index]
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun HourlyWeatherItem(time: String, temperature: Double, weatherCode: Int) {
//    Card(
//        modifier = Modifier.width(80.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(8.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = time.substring(11, 16), // Extract time (HH:MM)
//                style = MaterialTheme.typography.bodySmall
//            )
//            Text(
//                text = "${temperature.toInt()}°C",
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight.Bold
//            )
//        }
//    }
//}
//
//@Composable
//fun WeeklyWeatherCard(daily: DailyWeather) {
//    Card(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = "Next 7 Days",
//                style = MaterialTheme.typography.titleLarge,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//
//            daily.time.forEachIndexed { index, date ->
//                DailyWeatherItem(
//                    date = date,
//                    maxTemp = daily.temperature_2m_max[index],
//                    minTemp = daily.temperature_2m_min[index],
//                    weatherCode = daily.weather_code[index],
//                    uvIndex = daily.uv_index_max[index]
//                )
//                if (index < daily.time.size - 1) {
//                    Divider(modifier = Modifier.padding(vertical = 4.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DailyWeatherItem(
//    date: String,
//    maxTemp: Double,
//    minTemp: Double,
//    weatherCode: Int,
//    uvIndex: Double
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = date.substring(5), // Remove year (MM-DD)
//            style = MaterialTheme.typography.bodyMedium
//        )
//
//        Text(
//            text = "${maxTemp.toInt()}° / ${minTemp.toInt()}°",
//            style = MaterialTheme.typography.bodyMedium,
//            fontWeight = FontWeight.Bold
//        )
//
//        Text(
//            text = "UV: ${uvIndex.toInt()}",
//            style = MaterialTheme.typography.bodySmall,
//            color = MaterialTheme.colorScheme.secondary
//        )
//    }
//}
//
//@Composable
//fun PermissionRationaleScreen(onRequestPermission: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Location Permission Required",
//            style = MaterialTheme.typography.headlineSmall,
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "This app needs location permission to provide weather information for your current location.",
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Button(onClick = onRequestPermission) {
//            Text("Grant Permission")
//        }
//    }
//}
//
//@Composable
//fun PermissionDeniedScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Location Permission Denied",
//            style = MaterialTheme.typography.headlineSmall,
//            textAlign = TextAlign.Center
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "Please enable location permission in app settings to use this feature.",
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
//    }
//}
//
//// Application Class
//class WeatherApplication : Application() {
//    override fun onCreate() {
//        super.onCreate()
//
//        startKoin {
//            androidLogger()
//            androidContext(this@WeatherApplication)
//            modules(appModule)
//        }
//    }
//}