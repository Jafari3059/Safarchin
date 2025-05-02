package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

data class rest_kafe(
    val title: String,
    val description: String,
    val rating: Int = 5,
    val imageResId: Int,
    val address: String,
    val telephone: Int,
    val WorkingHours: String,
)

@Composable
fun RestKafeCard(place: rest_kafe) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp      // حدود 160dp روی موبایل
    val imageHeight = (screenWidth * 0.3).dp    // حدود 120dp
    val iconSize = (screenWidth * 0.03).dp      // حدود 10dp
    val titleFontSize = (screenWidth * 0.025).sp
    val descFontSize = (screenWidth * 0.02).sp

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
                .padding(horizontal = 6.dp, vertical = 6.dp),
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
                    painter = painterResource(id = place.imageResId),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.title,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                repeat(place.rating) {
                    Icon(
                        painter = painterResource(id = R.drawable.fullstar),
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(iconSize)
                    )
                }
            }

            Text(
                text = place.description,
                fontFamily = iranSans,
                fontSize = descFontSize,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                textAlign = TextAlign.Right,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RestKafeSection() {
    val restList = listOf(
        rest_kafe("رستوران هفت‌خوان", "این مجموعه که در سال ۱۳۸۹ افتتاح شد.این رستوران جزو بهترین", 5, R.drawable.shiraz, "شیراز، بلوار چمران", 12345678, "۱۲ ظهر تا ۱۲ شب"),
        rest_kafe("کافه طهرون", "فضایی سنتی با طراحی نوستالژیک که دارای حس فوق العاده هیجان انگیز", 4, R.drawable.shiraz, "شیراز، خیابان کریم‌خان", 87654321, "۹ صبح تا ۱۱ شب"),
        rest_kafe("کافه فرهنگ", "با منوی متنوع و محیطی دوستانه در مرکز شهر", 3, R.drawable.shiraz, "شیراز، خیابان لطفعلی‌خان", 88888888, "۸ صبح تا ۱۰ شب")
    )

    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(restList) { item ->
            RestKafeCard(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestKafe() {
    RestKafeSection()
}
