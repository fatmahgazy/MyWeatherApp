package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import org.codeforegypt.myweatherapp.ui.theme.IconColor
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont

@Composable
fun CurrentWeatherGrid(){
    val items = listOf(
        GridItem(painterResource(id = R.drawable.wind), "13 KM/h", "Wind"),
        GridItem(painterResource(id = R.drawable.humidity), "24%", "Humidity"),
        GridItem(painterResource(id = R.drawable.rain), "2%", "Rain"),
        GridItem(painterResource(id = R.drawable.uv), "2", "UV Index"),
        GridItem(painterResource(id = R.drawable.pressure), "1012 hPa", "Pressure"),
        GridItem((painterResource(id = R.drawable.temperature)), "22°C", "Feels like")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth()
            .height(250.dp)
            .padding( horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(items) { item ->
            GridItemView(item)
        }
    }
}
@Composable
fun GridItemView(item: GridItem){
    Box(
        modifier = Modifier
            .height(115.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
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
                fontSize = 20.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight(500),
                color = DarkBlueWithOpacity
            )

            Text(
                text = item.text,
                fontSize = 14.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight(400),
                color = backgroundColor
            )
        }
    }
}
data class GridItem(
    val icon: Painter,
    val number: String,
    val text: String
)