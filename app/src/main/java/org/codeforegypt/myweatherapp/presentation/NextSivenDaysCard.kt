package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.R
import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont

data class DayWeatherItem(
    val day: String,
    val highTemp: String,
    val lowTemp: String
)

@Composable
fun DaysContainer(items: List<DayWeatherItem>) {
    Box(
        modifier = Modifier
            .width(356.dp)
            .height(435.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
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
                            color = backgroundColor,
                            modifier = Modifier.weight(1f)
                        )
                        Box(
                            modifier = Modifier.size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.clear_sky1),
                                contentDescription = "Weather icon",
                                modifier = Modifier
                                    .size(35.dp)

                            )
                        }
                        Row(
                            Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp, horizontal = 6.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.arrow_up),
                                contentDescription = "Arrow up",
                                modifier = Modifier
                                    .size(12.dp)
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = item.highTemp,
                                color = MaterialTheme.colorScheme.onSurface,
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
                            Spacer(Modifier.width(2.dp))


                            Icon(
                                painter = painterResource(R.drawable.arrow_down),
                                contentDescription = "Arrow down",
                                modifier = Modifier
                                    .size(12.dp)
                            )
                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = item.highTemp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }
                    }

                    if (index != items.lastIndex) {
                        Divider(
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DaysOfBoxList() {
    val weatherItems = listOf(
        DayWeatherItem("Monday", "32", "20"),
        DayWeatherItem("Tuesday", "32", "20"),
        DayWeatherItem("Wednesday", "32", "20"),
        DayWeatherItem("Thursday", "32", "20"),
        DayWeatherItem("Friday", "32", "20"),
        DayWeatherItem("Saturday", "32", "20"),
        DayWeatherItem("Sunday", "32", "20")
    )

    DaysContainer(items = weatherItems)
}
