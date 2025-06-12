package org.codeforegypt.myweatherapp.presentation.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.R
import org.codeforegypt.myweatherapp.presentation.BlurredCircle
import org.codeforegypt.myweatherapp.presentation.CurrentWeatherGrid
import org.codeforegypt.myweatherapp.presentation.DaysContainer
import org.codeforegypt.myweatherapp.presentation.LazyHorizontalGridItem
import org.codeforegypt.myweatherapp.presentation.TemberatureBox
import org.codeforegypt.myweatherapp.presentation.utils.WeatherCodeUtils.getWeatherDescription
import org.codeforegypt.myweatherapp.presentation.utils.WeatherIconUtils.getWeatherIcon
import org.codeforegypt.myweatherapp.presentation.viewModel.WeatherViewModel
import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
import org.codeforegypt.myweatherapp.ui.theme.MyWeatherAppTheme
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val uiState by viewModel.weatherState.collectAsState()
    val weather = uiState.weather
    val isDay = weather?.currentWeather?.isDay ?: true

    MyWeatherAppTheme(isDay = isDay) {
        val backgroundBrush = if (isDay) {
            Brush.verticalGradient(
                colors = listOf(Color(0xFF87CEFA), Color.White)
            )
        } else {
            Brush.verticalGradient(
                colors = listOf(Color(0xFF060414), Color(0xFF0D0C19))
            )
        }

        val weatherIcon = weather?.let {
            getWeatherIcon(it.currentWeather.weatherCode, it.currentWeather.isDay)
        }

        val scrollState = rememberLazyListState()
        var isCollapsed by remember { mutableStateOf(false) }

        LaunchedEffect(scrollState.firstVisibleItemScrollOffset) {
            isCollapsed = scrollState.firstVisibleItemScrollOffset > 50 // Adjust threshold as needed
        }

        val imageScale by animateFloatAsState(
            targetValue = if (isCollapsed) 0.8f else 1f,
            animationSpec = tween(durationMillis = 300)
        )
        val offsetX by animateFloatAsState(
            targetValue = if (isCollapsed) -100f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        val textOffsetX by animateFloatAsState(
            targetValue = if (isCollapsed) 100f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        val offsetY by animateFloatAsState(
            targetValue = if (isCollapsed) -50f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        val offsetCityY by animateFloatAsState(
            targetValue = if (isCollapsed) 90f else 0f,
            animationSpec = tween(durationMillis = 300)
        )

        val offsetImageY by animateFloatAsState(
            targetValue = if (isCollapsed) 130f else 0f,
            animationSpec = tween(durationMillis = 300)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundBrush)
                .systemBarsPadding()
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset( y = offsetCityY.dp)
                            .padding(top = 24.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "",
                            tint = if (isDay) Color.Black else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(0.75.dp))

                        Text(
                            text = uiState.cityName.ifBlank { "Unknown location" },
                            fontSize = 16.sp,
                            fontFamily = urbanistFont,
                            fontWeight = FontWeight.Medium,
                            color = if (isDay) Color.Black else Color.White,
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .offset(x = offsetX.dp , y = offsetImageY.dp)
                            .scale(imageScale)
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        BlurredCircle(
                            size = 300.dp,
                            color = Color.Cyan,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                        )

                        weatherIcon?.let { painterResource(id = it) }?.let {
                            Image(
                                painter = it,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .height(200.dp),
                                contentScale = ContentScale.FillHeight
                            )
                        }
                    }
                }

                item {
                    uiState.weather?.let {
                        val temperature = it.currentWeather.temperature.toInt().toString()
                        val weatherCode = it.currentWeather.weatherCode
                        val isDay = it.currentWeather.isDay
                        val weatherDesc = getWeatherDescription(weatherCode, isDay)

                        Column(
                            modifier = Modifier.offset(x = textOffsetX.dp, y = offsetY.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "$temperature°C",
                                fontSize = 64.sp,
                                fontFamily = urbanistFont,
                                fontWeight = FontWeight(600),
                                color = if (isDay) Color.Black else Color.White,
                            )
                            Text(
                                text = weatherDesc,
                                fontSize = 16.sp,
                                fontFamily = urbanistFont,
                                fontWeight = FontWeight(500),
                                color = if (isDay) backgroundColor else Color.LightGray,
                            )
                        }
                    }
                }

                item {
                    TemberatureBox(weather, viewModel ,  isCollapsed)
                }

                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }

                item {
                    Box {
                        CurrentWeatherGrid(viewModel)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Today",
                            fontSize = 20.sp,
                            fontFamily = urbanistFont,
                            fontWeight = FontWeight(600),
                            color = if (isDay)DarkBlueWithOpacity else Color.White
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    Box {
                        LazyHorizontalGridItem(viewModel)
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Next 7 days",
                            fontSize = 20.sp,
                            fontFamily = urbanistFont,
                            fontWeight = FontWeight(600),
                            color = if (isDay)DarkBlueWithOpacity else Color.White
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }

                item {
                    DaysContainer(viewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun WeatherScreenPreview(){
    WeatherScreen()
}