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


@Composable
fun Nearest_cities(city: City) {
    Box(
        modifier = Modifier
            .height(165.dp)
            .width(130.dp)
            .padding(4.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp)) // سایه از همه طرف
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
                    .height(80.dp)
                    .clip(RoundedCornerShape(11))
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
                    fontSize = 24.sp,
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
                        .padding(top = 15.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = city.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = city.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = 6.sp,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun CityCarddList() {
    val cityList = listOf(
        City("شیراز", "۱۲۰ کیلومتر دورتر", R.drawable.shiraz),
        City("اصفهان", "۸۰ کیلومتر دورتر", R.drawable.shiraz),
        City("تبریز", "۱۰۰ کیلومتر دورتر", R.drawable.shiraz)
    )

    LazyRow(
        reverseLayout = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cityList) { city ->
            CityCard(city)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCitsdvyCards() {
    CityCarddList()
}
