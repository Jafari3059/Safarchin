package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.shopCenter
//import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlcCard
import com.example.safarchin.ui.theme.iranSans



@Composable
fun shopcenterCard(place: shopCenter) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val cardHeight = (screenHeight * 0.29).dp // ✅ ارتفاع نسبت‌دار
    val imageWidth = (screenWidth * 0.35).dp
    val titleFontSize = (screenWidth * 0.035).sp
    val descFontSize = (screenWidth * 0.028).sp
    val iconSize = (screenWidth * 0.04).dp
    val iconSaveSize = (screenWidth * 0.055).dp

    val paddingSize = (screenWidth * 0.025).dp
    val minDescriptionHeight = (descFontSize.value * 1.4f * 7).dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight) // ✅ ریسپانسیو بر اساس ارتفاع صفحه
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(Color.White)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(imageWidth)
                    .fillMaxHeight()
            )


            // متن و آیکون‌ها
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = place.name,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    )

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Text(
                        text = place.description,
                        fontFamily = iranSans,
                        fontSize = descFontSize,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Justify,
                        lineHeight = descFontSize * 1.4,
                        maxLines = 7,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = minDescriptionHeight) // ✅ ارتفاع حداقل
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

               InfoRow(icon = R.drawable.location, text = "آدرس: ${place.address}", iconSize)
                InfoRow(icon = R.drawable.clock, text = "ساعت کاری: ${place.WorkingHours}", iconSize)
                InfoRow(icon = R.drawable.phone, text = "تماس: ${place.telephone}", iconSize)

                Spacer(modifier = Modifier.height(6.dp))

                // دکمه و آیکون
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "افزودن سفر",
                        tint = Color(0xFFBEBAB3),
                        modifier = Modifier.size(iconSaveSize)
                    )

                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFB26B), RoundedCornerShape(8.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "توضیحات بیشتر",
                            fontSize = (screenWidth * 0.025).sp,
                            color = Color.White,
                            fontFamily = iranSans
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun InfoRow(icon: Int, text: String, iconSize: Dp) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontFamily = iranSans,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFFFFB26B),
            modifier = Modifier.size(iconSize)
        )
    }
}



@Composable
fun shopcenterList() {
    val restList = listOf(
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

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(restList) { place ->
            shopcenterCard(place = place)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevfvSoqatiCards() {
    shopcenterList()
}