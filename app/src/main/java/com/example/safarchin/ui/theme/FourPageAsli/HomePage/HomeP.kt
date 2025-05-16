package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HeaderSection
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.delay

@Composable
fun HomeP(navController: NavController) {
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

            // Header
            HeaderSection(
                onNotificationClick = { /* می‌تونی بعداً آلارم بزاری */ },
                onHelpClick = {
                    navController.navigate("support_page")
                }
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
        }

//        Spacer(modifier = Modifier.height(16.dp))

        var searchText by remember { mutableStateOf("") }

        SearchBar(
            value = searchText,
            onValueChange = { searchText = it },
            placeholderText = "کجا میخوای بری؟",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        ActiveTripSection()

        val config = LocalConfiguration.current
        val screenWidth = config.screenWidthDp.dp
        val screenHeight = config.screenHeightDp.dp
        val screenWidthPx = LocalConfiguration.current.screenWidthDp
        val iconSize = (screenWidthPx * 0.045).dp
        // نسبت‌های تطبیقی
        val horizontalPadding = (screenWidth.value * 0.06).dp  // حدود 24dp روی گوشی 400dp
        val fontSizeTitle = (screenWidth.value * 0.035).sp     // حدود 14sp روی گوشی 400dp
        val fontSizeMore = (screenWidth.value * 0.03).sp


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
                        text = "سفرهای محبوب",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                // ✅ لیست کارت‌ها
                CityCardList(navToCityScreen = { navController.navigate("cityDetail") })
            }
        }

        Spacer(modifier = Modifier.height(10.dp))



        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(16.dp))

        ) {
            Box(
                modifier = Modifier
                    .width(130.dp)
                    .height(115.dp)
                    .clip(RoundedCornerShape(16))
                    .align(alignment = Alignment.CenterEnd)

            ) {
                Image(
                    painter = painterResource(R.drawable.khajo),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "۸",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White,
                        maxLines = 1,
                        style = TextStyle(
                            lineHeight = 24.sp, // کنترل ارتفاع خط
                            shadow = Shadow(Color.Black, Offset(0f, 0f), 8f)
                        ),
                        modifier = Modifier
                    )

                    val subtitle = "روز مانده به سفر اصفهان"
                    val calculatedFontSize = when {
                        subtitle.length < 20 -> 18.sp
                        subtitle.length < 30 -> 16.sp
                        else -> 14.sp
                    }

                    Text(
                        text = subtitle,
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = calculatedFontSize,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            shadow = Shadow(Color.Black, Offset(0f, 0f), 10f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth())
                }



            }


            WeatherCard()
        }
        sugestiontrip()

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
                        text = "نزدیکترین مقاصد",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        fontSize = fontSizeTitle,
                        color = Color.Black
                    )
                }

                // ✅ لیست کارت‌ها
                Nearest_citiesCard()

            }
        }


        Spacer(modifier = Modifier.height(90.dp)) // 👈 این کمک می‌کنه بنر کامل دیده بشه

    }
}


//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeP()
//}
