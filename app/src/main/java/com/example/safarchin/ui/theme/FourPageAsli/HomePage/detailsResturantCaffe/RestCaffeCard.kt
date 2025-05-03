package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsResturantCaffe


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
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.rest_kafe
//import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlcCard
import com.example.safarchin.ui.theme.iranSans



@Composable
fun RestCaffeCard(place: rest_kafe) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val cardHeight = (screenHeight * 0.29).dp // ✅ ارتفاع نسبت‌دار
    val imageWidth = (screenWidth * 0.35).dp
    val titleFontSize = (screenWidth * 0.035).sp
    val descFontSize = (screenWidth * 0.028).sp
    val iconSize = (screenWidth * 0.04).dp
    val iconSaveSize = (screenWidth * 0.055).dp

    val paddingSize = (screenWidth * 0.025).dp
    val minDescriptionHeight = (descFontSize.value * 1.3f * 6).dp

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
                painter = painterResource(id = place.imageResId),
                contentDescription = place.title,
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
                    text = place.title,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Right
                )

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Text(
                        text = place.description,
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Light,
                        fontSize = descFontSize,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        lineHeight = descFontSize * 1.4,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = minDescriptionHeight) // ✅ ارتفاع حداقل
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
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
                Spacer(modifier = Modifier.height(6.dp))

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
fun RestCaffeList() {
    val restList = listOf(
        rest_kafe(
            title = "کافه زاویه",
            description = "یکی از ماگه دنبال یه تجربه\u200Cی اصیل ایرانی وسط حال\u200Cو\u200Cهوای سنتی اصفهان هستی، رستوران شهرزاد یه انتخاب فوق\u200Cالعاده\u200Cست. فضای رستوران با آیینه\u200Cکاری\u200Cها و نقاشی\u200Cهای سنتی، حس و حال خونه\u200Cهای قدیمی ایرانی رو برات زنده می\u200Cکنه.\n" +
                    "منوی غذاها بیشتر روی غذاهای ایرانی تمرکز داره؛ از باقالی\u200Cپلو با ماهیچه گرفته تا فسنجون و زرشک\u200Cپلو با مرغ که همه با کیفیت بالا سرو می\u200Cشن. ",
            rating = 5,
            imageResId = R.drawable.khajo,
            address = "شیراز، خیابان ارم",
            telephone = 12345678,
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        rest_kafe(
            title = "رستوران هفت خوان",
            description = "مسجد معروف با شیشاگه دنبال یه تجربه\u200Cی اصیل ایرانی وسط حال\u200Cو\u200Cهوای سنتی اصفهان هستی، رستوران شهرزاد یه انتخاب فوق\u200Cالعاده\u200Cست. فضای رستوران با آیینه\u200Cکاری\u200Cها و نقاشی\u200Cهای سنتی، حس و حال خونه\u200Cهای قدیمی ایرانی رو برات زنده می\u200Cکنه.\n" +
                    "منوی غذاها بیشتر روی غذاهای ایرانی تمرکز داره؛ از باقالی\u200Cپلو با ماهیچه گرفته تا فسنجون و زرشک\u200Cپلو با مرغ که همه با کیفیت بالا سرو می\u200Cشن. ",
            rating = 4,
            imageResId = R.drawable.shiraz,
            address = "شیراز، خیابان لطفعلی‌خان زند",
            telephone = 87654321,
            WorkingHours = "۹ صبح تا ۵ عصر"
        ),
        rest_kafe(
            title = "کافه بازار",
            description = "فضایی سنتی برای نوشیدن چای و تجربه حال واگه دنبال یه تجربه\u200Cی اصیل ایرانی وسط حال\u200Cو\u200Cهوای سنتی اصفهان هستی، رستوران شهرزاد یه انتخاب فوق\u200Cالعاده\u200Cست. فضای رستوران با آیینه\u200Cکاری\u200Cها و نقاشی\u200Cهای سنتی، حس و حال خونه\u200Cهای قدیمی ایرانی رو برات زنده می\u200Cکنه.\n" +
                    "منوی غذاها بیشتر روی غذاهای ایرانی تمرکز داره؛ از باقالی\u200Cپلو با ماهیچه گرفته تا فسنجون و زرشک\u200Cپلو با مرغ که همه با کیفیت بالا سرو می\u200Cشن.  هوای قدیمی.",
            rating = 4,
            imageResId = R.drawable.khajo,
            address = "تبریز، مرکز شهر",
            telephone = 11223344,
            WorkingHours = "۱۰ صبح تا ۱۰ شب"
        )
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(restList) { place ->
            RestCaffeCard(place = place)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewsdfvSoqatiCards() {
    RestCaffeList()
}