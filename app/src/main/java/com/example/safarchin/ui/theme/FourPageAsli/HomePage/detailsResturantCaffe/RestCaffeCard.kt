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
            title = "رستوران هفت‌خوان",
            description = "مجموعه‌ای لوکس با چند رستوران و کافی‌شاپ در طبقات مختلف که هرکدام غذاهایی خاص ارائه می‌دهند؛ شامل غذاهای ایرانی، فرنگی، فست‌فود و صبحانه. فضای مدرن و خدمات حرفه‌ای، این رستوران را به یکی از محبوب‌ترین مقاصد غذایی در شیراز تبدیل کرده است.",
            rating = 5,
            imageResId = R.drawable.haft_khan,
            address = "شیراز، بلوار جدید قرآن، نبش کوچه هفدهم",
            telephone = "07132270000",
            WorkingHours = "۷ صبح تا ۱۱:۳۰ شب"
        ),
        rest_kafe(
            title = "کافه عمارت فتح‌الملوکی",
            description = "کافه‌ای با طراحی سنتی و فضای دل‌نشین در عمارت تاریخی، با منویی متنوع شامل نوشیدنی‌های سنتی، قهوه، دسر و غذاهای سبک. مکان مناسبی برای تجربه‌ای آرامش‌بخش در دل بافت تاریخی شیراز.",
            rating = 4,
            imageResId = R.drawable.fath_olmolk,
            address = "شیراز، پشت ارگ کریم‌خانی، خیابان ۲۲ بهمن، کوچه پارکینگ اتحاد",
            telephone = "09173131542",
            WorkingHours = "۱۰ صبح تا ۱۲ شب"
        ),
        rest_kafe(
            title = "کافه موزه زرنگار",
            description = "کافه‌ای هنری در فضای موزه‌ای با حال و هوای سنتی و محیطی آرامش‌بخش. مناسب برای صرف صبحانه، میان‌وعده و نوشیدنی‌های سنتی و مدرن در فضایی فرهنگی.",
            rating = 4,
            imageResId = R.drawable.zar_negar,
            address = "شیراز، خیابان لطفعلی‌خان زند، کوچه ۳۳، پلاک ۱۴",
            telephone = "09179355003",
            WorkingHours = "۸ صبح تا ۵:۳۰ عصر"
        ),
        rest_kafe(
            title = "رستوران سنتی شرزه",
            description = "یکی از قدیمی‌ترین و محبوب‌ترین رستوران‌های سنتی شیراز با فضای دل‌نشین ایرانی و موسیقی زنده. منوی متنوع شامل کباب، خورشت‌های سنتی و غذاهای محلی مثل کلم‌پلو.",
            rating = 4,
            imageResId = R.drawable.sherzeh,
            address = "شیراز، خیابان لطفعلی‌خان زند، کنار مجموعه بازار وکیل",
            telephone = "07132241963",
            WorkingHours = "۱۲ ظهر تا ۱۱ شب"
        ),
        rest_kafe(
            title = "رستوران کته ماس",
            description = "رستورانی با حال و هوای صمیمی و غذاهای سنتی ایرانی با تمرکز ویژه بر غذاهای محلی شیراز مانند کلم‌پلو، شیرازی‌پلو و خورشت قورمه‌سبزی. کیفیت بالا و قیمت مناسب.",
            rating = 4,
            imageResId = R.drawable.kateh_mass,
            address = "شیراز، سه‌راه نمازی، کوچه ۷",
            telephone = "07132231818",
            WorkingHours = "۸ صبح تا ۱۱ شب"
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