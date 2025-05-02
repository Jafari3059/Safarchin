package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont
import kotlinx.coroutines.delay

@Composable
fun CityP(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val images = listOf(
        R.drawable.khajo,
        R.drawable.shiraz,
        R.drawable.meydan_emam
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

    // ✅ تغییر خودکار عکس هر ۵ ثانیه
    LaunchedEffect(pagerState.currentPage) {
        delay(5000L)
        val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
        pagerState.animateScrollToPage(
            page = nextPage,
            animationSpec = tween(
                durationMillis = 2, // مثلا 600 میلی‌ثانیه
                easing = LinearOutSlowInEasing
            )
        )
    }
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF6F4F4))
            .verticalScroll(scrollState) // ✅ اسکرول‌پذیر کردن کل صفحه
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            // فقط یک HorizontalPager
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill, // 👈 تمام عرض صفحه رو بگیره
                modifier = Modifier.fillMaxSize()

            ) { page ->
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.9f // ✅ شفافیت تصویر
                )
            }

            // گرادینت پایین برای محو کردن
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFF6F4F4)),
                            startY = 450f
                        )
                    )
            )
            // ✅ دایره‌های پایین وسط
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(images.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (pagerState.currentPage == index) Color(0xFFFF9800)
                                else Color.LightGray
                            )
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.back), // آیکون برگشت خودت
                contentDescription = "بازگشت",
                modifier = Modifier
                    .align(Alignment.TopStart) // یا .TopEnd برای سمت راست
                    .padding(start = 24.dp, top = 42.dp)
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack() // رفتن به عقب
                    },
                tint = Color.Black
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "شیراز",
            fontFamily = irgitiFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Right,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .wrapContentHeight() // 👈 ارتفاع بر اساس محتوا
                .shadow(
                    elevation = 8.dp, // شدت سایه
                    shape = RoundedCornerShape(8.dp),
                    clip = false // خیلی مهم برای دیده شدن سایه بیرون از Box
                )
                .background(color = Color(0xFFFFFFFF) , RoundedCornerShape(8.dp))

        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = "شیراز مرکز استان فارس در جنوب غربی ایران است. این شهر در دامنه کوه\u200Cهای زاگرس قرار گرفته و از آب\u200Cوهوایی معتدل در فصل بهار برخوردار است.\n" +
                        "وسعت شهر: حدود ۲۴۰ کیلومتر مربع\n" +
                        "ارتفاع از سطح دریا: تقریبا ۱۵۰۰ متر\n" +
                        "بر اساس سرشماری سال ۱۴۰۰، جمعیت کلان\u200Cشهر شیراز حدود ۱٬۹۵۵٬۰۰۰ نفر بوده و پس از تهران، مشهد و اصفهان، یکی از پرجمعیت\u200Cترین شهرهای ایران محسوب می\u200Cشود.\n" +
                        "شیراز شهری با بیش از ۲۵۰۰ سال تاریخ مکتوب است. این شهر از دوران هخامنشیان (قرن ششم پیش از میلاد) به عنوان منطقه\u200Cای مهم شناخته می\u200Cشده و در دوره\u200Cهای مختلف، از جمله دوران آل\u200Cبویه و زندیه، پایتخت حکومت ایران بوده است. نزدیکی\u200Cاش به مراکز باستانی چون تخت جمشید و پاسارگاد نشان از جایگاه تاریخی\u200Cاش دارد.\n" +
                        "شیراز زادگاه بسیاری از بزرگان شعر و ادب فارسی است؛ از جمله:\n" +
                        "حافظ\n" +
                        "سعدی\n" +
                        "خواجوی کرمانی\n" +
                        "همچنین شیراز به عنوان پایتخت فرهنگی ایران شناخته می\u200Cشود و میزبان جشنواره\u200Cها، نمایشگاه\u200Cها و برنامه\u200Cهای فرهنگی متعدد است.",
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = 8.sp,
                color = Color.Black,
                textAlign = TextAlign.Right,
            )
        }


        val config = LocalConfiguration.current
        val screenWidth = config.screenWidthDp.dp
        val screenHeight = config.screenHeightDp.dp
        val screenWidthPx = LocalConfiguration.current.screenWidthDp
        val iconSize = (screenWidthPx * 0.045).dp
        // نسبت‌های تطبیقی
        val horizontalPadding = (screenWidth.value * 0.06).dp  // حدود 24dp روی گوشی 400dp
        val fontSizeTitle = (screenWidth.value * 0.035).sp     // حدود 14sp روی گوشی 400dp
        val fontSizeMore = (screenWidth.value * 0.03).sp       // حدود 12sp روی گوشی 400dp

        // ✅ بخش: مکان‌های دیدنی
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = horizontalPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // سمت چپ (بیشتر + آیکون)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "Next Icon",
                            modifier = Modifier.size((screenWidth.value * 0.045).dp) // حدود 18dp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "بیشتر",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = fontSizeMore,
                            color = Color.Black
                        )
                    }

                    // سمت راست (عنوان بخش)
                    Text(
                        text = "مکان‌های دیدنی",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                // ✅ لیست کارت‌ها
                TourCardList()

            }
        }



//        // رستوران‌ها و کافه‌ها
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = horizontalPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // سمت چپ (بیشتر + آیکون)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "Next Icon",
                            modifier = Modifier.size(screenWidth * 0.045f) // 👈 حدود 18dp روی گوشی معمولی
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "بیشتر",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = fontSizeMore,
                            color = Color.Black
                        )
                    }

                    // سمت راست (عنوان)
                    Text(
                        text = "رستوران‌ها و کافه‌ها",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                RestKafeSection()

            }
        }


// مراکز خرید (ریسپانسیو)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = horizontalPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // سمت چپ (بیشتر + آیکون)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "Next Icon",
                            modifier = Modifier.size(iconSize)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "بیشتر",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = fontSizeMore,
                            color = Color.Black
                        )
                    }

                    // سمت راست (عنوان بخش)
                    Text(
                        text = "مراکز خرید",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                ShopingCenterList()

            }
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = horizontalPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // سمت چپ (بیشتر + آیکون)
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.next_icon),
                            contentDescription = "Next Icon",
                            modifier = Modifier.size(iconSize)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "بیشتر",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Normal,
                            fontSize = fontSizeMore,
                            color = Color.Black
                        )
                    }

                    // سمت راست (عنوان بخش)
                    Text(
                        text = "سوغاتی‌ها",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                SoqatiList()

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun cityPreview() {
//    CityP(navController)
//}