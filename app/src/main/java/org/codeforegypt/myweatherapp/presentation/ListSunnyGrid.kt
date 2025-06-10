package org.codeforegypt.myweatherapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import org.codeforegypt.myweatherapp.R

@Composable
fun listWeatherItem():List<TodayGridItem>  {
    return listOf(
        TodayGridItem(
            icon = painterResource(id = R.drawable.weather_icon),
            "25°C",
            "11:00 "
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "12:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "01:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.weather_icon),
            "25°C",
            "02:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "03:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "04:00"
        ),

        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "05:00"
        ),

        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "06:00"
        ),

        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "07:00"
        ),

        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "08:00"
        ),

        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "09:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "10:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "11:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "12:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "01:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "02:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "03:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "04:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "05:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "06:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "07:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "08:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "09:00"
        ),
        TodayGridItem(
            icon = painterResource(id = R.drawable.clear_sky1),
            "25°C",
            "10:00"
        ),



        )
}