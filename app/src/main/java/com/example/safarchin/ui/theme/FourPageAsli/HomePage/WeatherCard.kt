package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont

@Composable
fun WeatherCard() {
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(115.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF93DDFF))
            .padding(14.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ستون سمت چپ (جزئیات هوا)
            Column(
                modifier = Modifier
                    .width(70.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "☁ نیمه‌ابری", fontSize = 10.sp, fontFamily = iranSans , textAlign = TextAlign.Right , modifier = Modifier.align(alignment = Alignment.End))
                Text(text = "🌡 ۲۷°C", fontSize = 10.sp, fontFamily = iranSans, textAlign = TextAlign.Right, modifier = Modifier.align(alignment = Alignment.End))
                Text(text = "💨 باد: ۱۴", fontSize = 10.sp, fontFamily = iranSans, textAlign = TextAlign.Right, modifier = Modifier.align(alignment = Alignment.End))
                Text(text = "💧%رطوبت: ۷۳", fontSize = 10.sp, fontFamily = iranSans, textAlign = TextAlign.Right, modifier = Modifier.align(alignment = Alignment.End))
            }

            // ستون سمت راست (عنوان)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = " شیراز",
                    fontFamily = irgitiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Right ,
                    style = TextStyle(
                        shadow = Shadow(Color.Black, Offset(0f, 0f), 6f)
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.End),

                )
                Text(
                    text = "امروز",
                    fontFamily = irgitiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(Color.Black, Offset(0f, 0f), 6f)
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreeevnPreview() {
    WeatherCard()
}