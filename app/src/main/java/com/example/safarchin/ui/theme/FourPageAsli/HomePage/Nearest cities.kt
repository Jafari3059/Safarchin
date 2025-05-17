package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun Nearest_cities(city: City, distanceInKm: Double) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp
    val imageHeight = (screenWidth * 0.3).dp
    val titleFontSize = (screenWidth * 0.03).sp
    val distanceFontSize = (screenWidth * 0.022).sp
    val overlayTitleFontSize = (screenWidth * 0.06).sp
    val paddingInside = (screenWidth * 0.015).dp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingInside, vertical = paddingInside),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(11.dp))
            ) {
                Image(
                    painter = painterResource(id = city.imageRes),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Text(
                    text = city.name,
                    fontFamily = irgitiFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = overlayTitleFontSize,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0f, 0f),
                            blurRadius = 10f
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = paddingInside)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = city.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "%.1f کیلومتر دورتر".format(distanceInKm),
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = distanceFontSize,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun Nearest_citiesCard(userLat: Double, userLon: Double, cityList: List<City>) {
    // محاسبه فاصله و مرتب‌سازی
    val sortedCities = cityList.sortedBy {
        calculateDistance(userLat, userLon, it.latitude, it.longitude)
    }

    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sortedCities) { city ->
            val distance = calculateDistance(userLat, userLon, city.latitude, city.longitude)
            Nearest_cities(city, distanceInKm = distance)
        }

    }
}

fun calculateDistance(
    lat1: Double, lon1: Double,
    lat2: Double, lon2: Double
): Double {
    val R = 6371.0 // شعاع زمین بر حسب کیلومتر
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)

    val a = sin(dLat / 2).pow(2.0) +
            cos(Math.toRadians(lat1)) *
            cos(Math.toRadians(lat2)) *
            sin(dLon / 2).pow(2.0)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return R * c
}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewCitsdvyCards() {
//    Nearest_citiesCard()
//}
