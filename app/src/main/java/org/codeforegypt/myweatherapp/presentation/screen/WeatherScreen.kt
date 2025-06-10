//package org.codeforegypt.myweatherapp.presentation.screen
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import org.codeforegypt.myweatherapp.R
//import org.codeforegypt.myweatherapp.presentation.CurrentWeatherGrid
//import org.codeforegypt.myweatherapp.presentation.DaysOfBoxList
//import org.codeforegypt.myweatherapp.presentation.LazyHorizontalGridItem
//import org.codeforegypt.myweatherapp.presentation.TemberatureBox
//import org.codeforegypt.myweatherapp.presentation.listWeatherItem
//import org.codeforegypt.myweatherapp.ui.theme.BackGroundColor
//import org.codeforegypt.myweatherapp.ui.theme.CityColor
//import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
//import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
//import org.codeforegypt.myweatherapp.ui.theme.urbanistFont
//
//@Composable
//fun WeatherScreen() {
//    val weatherItems = listWeatherItem()
//    Box(
//        modifier = Modifier.fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    listOf(BackGroundColor, Color.White)
//                )
//            )
//    ) {
//        LazyColumn(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 24.dp),
//                    verticalAlignment = Alignment.Top,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.location),
//                        contentDescription = "",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(0.75.dp))
//
//                    Text(
//                        text = "Bagdad",
//                        fontSize = 16.sp,
//                        fontFamily = urbanistFont,
//                        fontWeight = FontWeight.Medium,
//                        color = CityColor,
//                    )
//                }
//            }
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.mainly_clear_4x),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .padding(start = 67.dp)
//                            .size(250.dp),
//                        contentScale = ContentScale.Inside
//                    )
//                }
//            }
//
//            item {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(
//                        text = "24°C",
//                        fontSize = 64.sp,
//                        fontFamily = urbanistFont,
//                        fontWeight = FontWeight(600),
//                        color = CityColor,
//                    )
//                    Text(
//                        text = "Partly cloudy",
//                        fontSize = 16.sp,
//                        fontFamily = urbanistFont,
//                        fontWeight = FontWeight(500),
//                        color = backgroundColor,
//                    )
//                }
//            }
//            item {
//                TemberatureBox()
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(15.dp))
//            }
//
//            item {
//                Box {
//                CurrentWeatherGrid()
//            }
//                }
//
//            item {
//                Spacer(modifier = Modifier.height(24.dp))
//            }
//
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp)
//                ) {
//                    Text(
//                        text = "Today",
//                        fontSize = 20.sp,
//                        fontFamily = urbanistFont,
//                        fontWeight = FontWeight(600),
//                    )
//                }
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(12.dp))
//            }
//
//            item {
//                Box {
//                    LazyHorizontalGridItem(weatherItems)
//                }
//            }
//            item {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp)
//                ) {
//                    Text(
//                        text = "Next 7 days",
//                        fontSize = 20.sp,
//                        fontFamily = urbanistFont,
//                        fontWeight = FontWeight(600),
//                        color = DarkBlueWithOpacity
//                    )
//                }
//            }
//                item{
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            item {
//                DaysOfBoxList()
//            }
//            }
//        }
//    }
//@Preview
//@Composable
//fun WeatherScreenPreview(){
//    WeatherScreen()
//}