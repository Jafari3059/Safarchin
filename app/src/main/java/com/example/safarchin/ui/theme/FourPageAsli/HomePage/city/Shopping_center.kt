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
        shopCenter(
            "مجتمع تجاری خلیج فارس",
            "بزرگ‌ترین مرکز خرید ایران با بیش از ۲۵۰۰ فروشگاه، شهربازی، سینما، فودکورت، سالن بیلیارد و بولینگ، هایپرمارکت و هتل ۵ ستاره. معماری آن الهام‌گرفته از تخت جمشید است و امکانات متنوعی برای خرید و تفریح ارائه می‌دهد.",
            R.drawable.persian_gulf_complex,
            "شیراز، اتوبان دکتر حسابی، ورودی شهر جدید صدرا",
            "071-12345678",
            "۹ صبح تا ۱۲ شب"
        ),
        shopCenter(
            "مرکز خرید زیتون فارس",
            "یکی از بزرگ‌ترین مراکز خرید شیراز با ۱۲ طبقه و نزدیک به ۴۰۰ واحد تجاری. دارای فروشگاه‌های متنوع، سینمای ۳ بعدی، شهربازی، رستوران و کافی‌شاپ. مناسب برای خرید پوشاک، لوازم دیجیتال و لوازم آرایشی.",
            R.drawable.zeytoon_fars,
            "شیراز، چهارراه ۱۵ خرداد (پارامونت)",
            "071-87654321",
            "۱۰ صبح تا ۱۰ شب"
        ),
        shopCenter(
            "مرکز خرید ستاره فارس",
            "مجتمعی ۹ طبقه با بیش از ۴۰۰ فروشگاه عرضه‌کننده پوشاک، لوازم آرایشی، دیجیتال و جواهرات. دارای رستوران ملل با غذاهای بین‌المللی، فودکورت، شهربازی و پارکینگ طبقاتی. مناسب برای خرید با قیمت‌های مناسب.",
            R.drawable.setare_fars,
            "شیراز، خیابان عفیف‌آباد",
            "071-23456789",
            "۱۰ صبح تا ۱۱ شب"
        ),
        shopCenter(
            "مرکز خرید هامون",
            "مرکزی لوکس با فروشگاه‌های برند، شهربازی، رستوران و فودکورت. مناسب برای خرید پوشاک، عطر، لوازم آرایشی و زیورآلات. گزینه‌ای مناسب برای خرید و تفریح در یک مکان.",
            R.drawable.hamoon,
            "شیراز، بلوار ستارخان، نرسیده به خیابان عفیف‌آباد",
            "071-34567890",
            "۹ صبح تا ۱۰ شب"
        ),
        shopCenter(
            "مرکز خرید سلطانیه",
            "یکی از لوکس‌ترین مراکز خرید شیراز با ۹ طبقه و بیش از ۸۰ فروشگاه عرضه‌کننده پوشاک، عطر، لوازم آرایشی، ساعت و جواهرات. دارای رستوران، کافی‌شاپ و دسترسی آسان از طریق مترو.",
            R.drawable.soltanieh,
            "شیراز، خیابان مطهری، میدان مطهری",
            "071-45678901",
            "۱۰ صبح تا ۱۰ شب"
        )
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
