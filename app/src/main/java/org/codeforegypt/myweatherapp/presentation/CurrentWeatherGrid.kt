package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.R
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
import org.codeforegypt.myweatherapp.ui.theme.IconColor
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont

@Composable
fun CurrentWeatherGrid(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()

    when {
        weatherState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }


        weatherState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${weatherState.error}")
            }
        }

        weatherState.weather != null -> {
            val weatherData = weatherState.weather!!
            val items = listOf(
                GridItem(
                    icon = painterResource(id = R.drawable.wind),
                    number = "${weatherData.currentWeather.windSpeed} KM/h",
                    text = "Wind"
                ),
                GridItem(
                    icon = painterResource(id = R.drawable.humidity),
                    number = "${weatherData.currentWeather.humidity}%",
                    text = "Humidity"
                ),
                GridItem(
                    icon = painterResource(id = R.drawable.rain),
                    number = "${weatherData.currentWeather.rainChance}%",
                    text = "Rain"
                ),
                GridItem(
                    icon = painterResource(id = R.drawable.uv),
                    number = weatherData.currentWeather.uvIndex.toString(),
                    text = "UV Index"
                ),
                GridItem(
                    icon = painterResource(id = R.drawable.pressure),
                    number = "${weatherData.currentWeather.pressure} hPa",
                    text = "Pressure"
                ),
                GridItem(
                    icon = painterResource(id = R.drawable.temperature),
                    number = "${weatherData.currentWeather.feelsLike}°C",
                    text = "Feels like"
                )
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                items(items) {
                    GridItemView(it, viewModel)
                }
            }
        }
    }
}


@Composable
fun GridItemView(item: GridItem, viewModel: WeatherViewModel) {

    val uiState by viewModel.weatherState.collectAsState()
    val weather = uiState.weather
    val isDay = weather?.currentWeather?.isDay ?: true

    MyWeatherAppTheme(isDay = isDay) {
        val backgroundBrush = if (isDay) {
            Brush.verticalGradient(
                colors = listOf(Color(0xFFFFFFFF), Color.White)
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(Color(0xFF060414), Color(0xFF0D0C19))
            )
        }
        Box(
            modifier = Modifier
                .height(115.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(backgroundBrush)
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = IconColor
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.number,
                    fontSize = 18.sp,
                    fontFamily = urbanistFont,
                    fontWeight = FontWeight(500),
                    color = if (isDay) DarkBlueWithOpacity else Color.White,

                    )

                Text(
                    text = item.text,
                    fontSize = 14.sp,
                    fontFamily = urbanistFont,
                    fontWeight = FontWeight(400),
                    color = if (isDay) backgroundColor else Color.LightGray
                )
            }
        }
    }
}
data class GridItem(
    val icon: Painter,
    val number: String,
    val text: String
)