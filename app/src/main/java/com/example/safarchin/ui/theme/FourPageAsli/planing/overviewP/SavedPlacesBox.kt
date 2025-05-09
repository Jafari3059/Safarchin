package com.example.safarchin.ui.theme.FourPageAsli.planing.overviewP

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SavedPlacesBox() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val boxWidth = screenWidth * 0.90f
    val boxHeight = screenHeight * 0.11f
    val fontSize = screenWidth.value.times(0.028).sp

    Box(
        modifier = Modifier
            .width(boxWidth)
            .height(boxHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "ذخیره شده‌ها",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = iranSans
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = null,
                    tint = Color(0xFF939B62),
                    modifier = Modifier.size(screenWidth * 0.055f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "هنوز مکان ذخیره شده ای برای این شهر نداری!!!",
                fontSize = fontSize.times(0.9f),
                color = Color.Gray,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewSavedPlacesBox() {
    MaterialTheme {
        Surface {
            SavedPlacesBox()
        }
    }
}
