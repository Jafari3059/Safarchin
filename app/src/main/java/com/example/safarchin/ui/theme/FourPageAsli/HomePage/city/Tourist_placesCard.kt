package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class TourPlace(
    val name: String,
    val description: String,
    val imageRes: Int,
    val Visit_duration: String,
    val Visit_price: String,
    val address: String,
    val telephone: Int,
    val WorkingHours: String,
)

@Composable
fun TourPlcCard(place: TourPlace) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp        // حدود 160dp روی موبایل
    val imageHeight = (screenWidth * 0.3).dp       // حدود 120dp
    val titleFontSize = (screenWidth * 0.03).sp    // حدود 12sp
    val descFontSize = (screenWidth * 0.025).sp    // حدود 10sp

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
                .padding(6.dp),
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
                    painter = painterResource(id = place.imageRes),
                    contentDescription = "TourPlace Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = place.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = descFontSize,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TourCardList() {
    val cityList = listOf(
        TourPlace(
            name = "باغ ارم",
            description = "شهر زیبای شیراز در ۲۰ کیلشهر تاریخی اصفهان با معماریومتری فارس واقع شده است.",
            imageRes = R.drawable.khajo,
            Visit_duration = "۱ ساعت",
            Visit_price = "۲۰٬۰۰۰ تومان",
            address = "شیراز، خیابان ارم",
            telephone = 12345678,
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "مسجد نصیرالملک",
            description = "معماری بی‌نظیر شهر تاریخی اصفهان با معماریبا پنجره‌های رنگی جذاب.",
            imageRes = R.drawable.shiraz,
            Visit_duration = "۴۵ دقیقه",
            Visit_price = "۱۵٬۰۰۰ تومان",
            address = "شیراز، خیابان لطفعلی‌خان زند",
            telephone = 87654321,
            WorkingHours = "۹ صبح تا ۵ عصر"
        ),
        TourPlace(
            name = "تبریز",
            description = "شهر اولین‌ها دشهر تاریخی اصفهان با معماریشهر تاریخی اصفهان با معماریر شمال‌غرب ایران.",
            imageRes = R.drawable.khajo,
            Visit_duration = "۲ ساعت",
            Visit_price = "رایگان",
            address = "تبریز، مرکز شهر",
            telephone = 11223344,
            WorkingHours = "۲۴ ساعته"
        )
    )

    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cityList) { place ->
            TourPlcCard(place)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCityCards() {
    TourCardList()
}
