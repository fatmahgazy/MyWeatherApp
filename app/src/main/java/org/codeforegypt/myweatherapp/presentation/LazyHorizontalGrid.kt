package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import org.codeforegypt.myweatherapp.presentation.utils.WeatherIconUtils
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import java.text.SimpleDateFormat
import java.util.Locale
@Composable
fun LazyHorizontalGridItem(viewModel: WeatherViewModel) {
    val weatherState by viewModel.weatherState.collectAsState()
    val isDay = weatherState.weather?.currentWeather?.isDay ?: true

        when {
            weatherState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            weatherState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: ${weatherState.error}")
                }
            }

            weatherState.weather != null -> {
                val hourlyData = weatherState.weather!!.hourlyForecast.take(8)

                val items = hourlyData.map { hourData ->
                    TodayGridItem(
                        icon = painterResource(
                            id = WeatherIconUtils.getWeatherIcon(
                                code = hourData.weatherCode,
                                isDay = isDay
                            )
                        ),
                        number = "${hourData.temperature.toInt()}°",
                        hour = hourData.time.formatHour()
                    )
                }

                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp)
                        .height(130.dp)
                        .padding(start = 12.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.Center,
                    contentPadding = PaddingValues(start = 8.dp)
                ) {
                    items(items) { item ->
                        TodayGridItemView(item, viewModel)
                    }
                }
            }
        }
    }
    fun String.formatHour(): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            val date = inputFormat.parse(this)
            val outputFormat = SimpleDateFormat("h a", Locale.getDefault())
            outputFormat.format(date)
        } catch (e: Exception) {
            this.takeLast(5)
        }
    }

    @Composable
    fun TodayGridItemView(item: TodayGridItem, viewModel: WeatherViewModel) {

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
            Box(
                modifier = Modifier
                    .width(88.dp)
                    .height(145.dp)

            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .align(Alignment.BottomCenter)
                        .width(108.dp)
                        .height(135.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(backgroundBrush)
                        .padding(horizontal = 8.dp, vertical = 10.dp)
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center)
                            .padding(top = 40.dp)
                    ) {
                        Text(
                            text = "${item.number}C",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color =  if (isDay) DarkBlueWithOpacity else Color.White,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = item.hour,
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color =  if (isDay)  DarkBlueWithOpacity else Color(0x99FFFFFF),
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }


                Icon(
                    painter = item.icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp, 58.dp)
                        .align(Alignment.TopCenter),
                    tint = Color.Unspecified
                )
            }
        }
    }

data class TodayGridItem(
    val icon: Painter,
    val number: String,
    val hour: String
)