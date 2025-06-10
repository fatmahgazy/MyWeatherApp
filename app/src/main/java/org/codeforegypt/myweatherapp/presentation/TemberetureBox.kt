package org.codeforegypt.myweatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeforegypt.myweatherapp.R
import org.codeforegypt.myweatherapp.ui.theme.BackGroundColor
import org.codeforegypt.myweatherapp.ui.theme.DividerColor
import org.codeforegypt.myweatherapp.ui.theme.backgroundColor
import org.codeforegypt.myweatherapp.ui.theme.colorGray
import org.codeforegypt.myweatherapp.ui.theme.urbanistFont

@Composable
fun TemberatureBox(){
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 96.dp)
            .height(35.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(colorGray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.
            padding(vertical = 8.dp , horizontal = 24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_up),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 5.dp )
                    .size(4.dp, 8.dp)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                //TODO
                text = "32°C",
                fontSize = 16.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Medium,
                color = backgroundColor

            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                .width(1.dp)
                    .height(20.dp)
                    .background(DividerColor)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 5.dp )
                    .size(4.dp, 8.dp)

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                //TODO
                text = "32°C",
                fontSize = 16.sp,
                fontFamily = urbanistFont,
                fontWeight = FontWeight.Medium,
                color = backgroundColor

            )

        }
    }
}