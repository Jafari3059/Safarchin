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

data class shopCenter(
    val name: String,
    val description: String,
    val imageRes: Int,
    val address: String,
    val telephone: String,
    val WorkingHours: String
)

@Composable
fun ShopCentCard(shop: shopCenter) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp
    val imageHeight = (screenWidth * 0.3).dp
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
                    painter = painterResource(id = shop.imageRes),
                    contentDescription = "Shop Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = shop.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = shop.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = descFontSize,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ShopingCenterList() {
    val cityList = listOf(
        shopCenter("مرکز خرید ستاره فارس", "یکی از بزرگ‌ترین مراکز خرید شیراز یکی از برزرگدینریم سیبتن س نت  نتیسذبنس منش ب شبرهنیدب ", R.drawable.khajo, "شیراز، بلوار امیرکبیر", "12345678", "۱۰ صبح تا ۱۱ شب"),
        shopCenter("مجتمع خلیج فارس", "دارای فودکشهر تاریخی اصفهان با معماریشهر تاریخی اصفهان با معماریورت، شهربازی و برندهای مختلف", R.drawable.shiraz, "شیراز، ورودی صدرا", "87654321", "۹ صبح تا ۱۲ شب"),
        shopCenter("مرکز خرید ستاره فارس", "یکی از شهر تاریخی اصفهان با معماریشهر تاریخی اصفهان با معماریبزرگ‌تری ندصیذ  دبت ذ ابصشه  هبادبثیص ن مراکز خرید شیراز...", R.drawable.khajo, "شیراز، بلوار امیرکبیر", "12345678", "۱۰ صبح تا ۱۱ شب"),
        shopCenter("مرکز خرید ستاره فارس", "یکی از بزرگ‌ترشهر تاریخی اصفهان با معماریvین مراکز خرید شیراز...", R.drawable.khajo, "شیراز، بلوار امیرکبیر", "12345678", "۱۰ صبح تا ۱۱ شب"),


        )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        reverseLayout = true,
    ) {
        items(cityList) { shop ->
            ShopCentCard(shop)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewShopCenters() {
    ShopingCenterList()
}
