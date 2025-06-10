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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.ui.theme.DarkBlueWithOpacity
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor

@Composable
fun LazyHorizontalGridItem(items: List<TodayGridItem>){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp)
            .height(130.dp)
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(horizontal = 8.dp)
    ){
        items(items) { item ->
            TodayGridItemView(item)
        }
    }
}
@Composable
fun TodayGridItemView(item: TodayGridItem) {
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
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .background(Color.White)
                .padding(horizontal = 8.dp, vertical = 10.dp)

        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = item.number,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = DarkBlueWithOpacity,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.hour,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = backgroundColor,
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

data class TodayGridItem(
    val icon: Painter,
    val number: String,
    val hour: String
)