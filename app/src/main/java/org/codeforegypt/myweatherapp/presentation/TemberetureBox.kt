package org.codeforegypt.myweatherapp.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import org.codeforegypt.myweatherapp.domain.model.WeatherInfo
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.DividerColor
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.colorGray
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont

@Composable
fun TemberatureBox(weather: WeatherInfo?, viewModel: WeatherViewModel, isCollapsed: Boolean) {
    val weatherState by viewModel.weatherState.collectAsState()
    val isDay = weatherState.weather?.currentWeather?.isDay ?: true

    MyWeatherAppTheme(isDay = isDay) {
        val backgroundBrush = if (isDay) {
            Brush.verticalGradient(
                colors = listOf(colorGray, colorGray)
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(Color.DarkGray, Color.DarkGray)
            )
        }

        val textOffsetX by animateFloatAsState(
            targetValue = if (isCollapsed) 100f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        val offsetY by animateFloatAsState(
            targetValue = if (isCollapsed) -50f else 0f,
            animationSpec = tween(durationMillis = 300)
        )

        weather?.let {
            val todayForecast = it.dailyForecast.firstOrNull()
            val maxTemp = todayForecast?.maxTemp?.toInt() ?: 0
            val minTemp = todayForecast?.minTemp?.toInt() ?: 0

            Box(
                modifier = Modifier
                    .offset(x = textOffsetX.dp, y = offsetY.dp)
                    .padding(vertical = 12.dp, horizontal = 96.dp)
                    .height(35.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(backgroundBrush),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_up),
                        contentDescription = "",
                        tint = if (isDay) backgroundColor else Color.White,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .size(4.dp, 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$maxTemp°C",
                        fontSize = 16.sp,
                        fontFamily = urbanistFont,
                        fontWeight = FontWeight.Medium,
                        color = if (isDay) backgroundColor else Color.White
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .padding(vertical = 2.dp)
                            .width(1.dp)
                            .height(20.dp)
                            .background(if (isDay) DividerColor else Color.LightGray)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_down),
                        contentDescription = "",
                        tint = if (isDay) backgroundColor else MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .size(4.dp, 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "$minTemp°C",
                        fontSize = 16.sp,
                        fontFamily = urbanistFont,
                        fontWeight = FontWeight.Medium,
                        color = if (isDay) backgroundColor else Color.White
                    )
                }
            }
        }
    }
}