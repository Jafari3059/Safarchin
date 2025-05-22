package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.zIndex
import androidx.compose.ui.text.style.TextAlign

@Composable
fun FullMapScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
    ) {
        Image(
            painter = painterResource(id = R.drawable.full_map),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "برگشت",
                tint = Color.Black
            )
        }

        // لیست مکان‌ها با مختصات حدودی (x, y)
        val locations = listOf(
            "میدان نقش جهان (امام)" to Offset(280f, 180f),
            "بازار بزرگ" to Offset(310f, 250f),
            "رستوران شهرزاد" to Offset(250f, 330f),
            "پارک آبشار" to Offset(180f, 270f),
            "کافه رخ" to Offset(120f, 140f),
            "مرکز خرید سیتی سنتر" to Offset(200f, 420f),
            "پل خواجو" to Offset(320f, 460f),
            "سی و سه پل" to Offset(100f, 540f),
            "شهربازی رویاها" to Offset(360f, 520f)
        )

        val density = LocalDensity.current

        locations.forEach { (name, offset) ->
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = with(density) { offset.x.dp.roundToPx() },
                            y = with(density) { offset.y.dp.roundToPx() }
                        )
                    }
                    .zIndex(1f)
                    .clickable { println("Clicked on $name") }
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = name,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = name,
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}
