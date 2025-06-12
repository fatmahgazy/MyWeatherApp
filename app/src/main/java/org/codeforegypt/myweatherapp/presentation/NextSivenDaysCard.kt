package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.R
import org.codeforegypt.myweatherapp.presentation.utils.WeatherIconUtils
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont
import org.codeforegypt.myweatherapp.ui.theme.white
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun DaysContainer(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()
    val isDay = weatherState.weather?.currentWeather?.isDay ?: true
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

        when {
            weatherState.isLoading -> {
                Box(
                    modifier = Modifier
                        .width(356.dp)
                        .height(435.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            weatherState.error != null -> {
                Box(
                    modifier = Modifier
                        .width(356.dp)
                        .height(435.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${weatherState.error}")
                }
            }

            weatherState.weather != null -> {
                val dailyForecast = weatherState.weather!!.dailyForecast.take(7)
                val items = dailyForecast.mapIndexed { index, daily ->
                    DayWeatherItem(
                        day = daily.date.getDayName(),
                        highTemp = "${daily.maxTemp.toInt()}°",
                        lowTemp = "${daily.minTemp.toInt()}°",
                        weatherCode = daily.weatherCode
                    )
                }

                Box(
                    modifier = Modifier
                        .width(356.dp)
                        .height(435.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(backgroundBrush)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)

                    ) {
                        items.forEachIndexed { index, item ->
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = item.day,
                                        fontSize = 16.sp,
                                        fontFamily = urbanistFont,
                                        fontWeight = FontWeight(400),
                                        color =  if (isDay) backgroundColor else Color.LightGray,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Box(
                                        modifier = Modifier.size(40.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(
                                                id = WeatherIconUtils.getWeatherIcon(
                                                    code = item.weatherCode,
                                                    isDay = isDay
                                                )
                                            ),
                                            contentDescription = "Weather icon",
                                            modifier = Modifier.size(35.dp)
                                        )
                                    }
                                    Row(
                                        Modifier
                                            .weight(1f)
                                            .padding(vertical = 8.dp, horizontal = 4.dp),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Icon(
                                            painter = painterResource(R.drawable.arrow_up),
                                            contentDescription = "Arrow up",
                                            tint = if (isDay) backgroundColor else white,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            text = "${item.highTemp}C",
                                            color = if (isDay) MaterialTheme.colorScheme.onSurface else white,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        VerticalDivider(
                                            modifier = Modifier
                                                .height(14.dp)
                                                .width(1.dp),
                                            color = Color.Black.copy(alpha = 0.24f)
                                        )
                                        Spacer(Modifier.width(2.5.dp))
                                        Icon(
                                            painter = painterResource(R.drawable.arrow_down),
                                            contentDescription = "Arrow down",
                                            tint = if (isDay) backgroundColor else white,
                                            modifier = Modifier.size(12.dp)
                                        )
                                        Spacer(Modifier.width(4.dp))
                                        Text(
                                            text = "${item.lowTemp}C",
                                            color = if (isDay) MaterialTheme.colorScheme.onSurface else white,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                                if (index != items.lastIndex) {
                                    Divider(
                                        modifier = Modifier.fillMaxWidth(),
                                        color = if (isDay) Color.Black.copy(.08f) else Color.DarkGray,
                                        thickness = 1.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
fun String.getDayName(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = inputFormat.parse(this)
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        outputFormat.format(date)
    } catch (e: Exception) {
        this
    }
}


data class DayWeatherItem(
    val day: String,
    val highTemp: String,
    val lowTemp: String,
    val weatherCode: Int
)