package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import android.net.Uri
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont
import kotlinx.coroutines.delay

@Composable
fun CityP(navController: NavController) {
    val sharedViewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
    val city = sharedViewModel.selectedCity

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 1 }
    )

    LaunchedEffect(pagerState.currentPage) {
        delay(5000L)
        pagerState.animateScrollToPage(
            page = 0,
            animationSpec = tween(
                durationMillis = 2,
                easing = LinearOutSlowInEasing
            )
        )
    }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF6F4F4))
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.fillMaxSize()
            ) {
                city?.imageRes?.let { imageRes ->
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Background Image",
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(0.dp)),
                        contentScale = ContentScale.Crop,
                        alpha = 0.9f
                    )
                }
            }

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF9800))
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "بازگشت",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 42.dp)
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack()
                    },
                tint = Color.Black
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = city?.name ?: "شهر نامشخص",
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
                .wrapContentHeight()
                .shadow(8.dp, RoundedCornerShape(8.dp), clip = false)
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                text = city?.description ?: "بدون توضیحات",
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = 8.sp,
                color = Color.Black,
                textAlign = TextAlign.Right
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        val config = LocalConfiguration.current
        val screenWidth = config.screenWidthDp.dp
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
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("tourDetails")
                            },
                    ) {
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
                            color = Color.Black,

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
//                if (city != null) {
//                    TourCardList(items = city.souvenirs, navController = navController)
//                }
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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("RestDetails")
                            },
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
                            color = Color.Black,

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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                navController.navigate("shopDetails")
                            },

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
                            color = Color.Black,

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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {
                            city?.souvenirs?.firstOrNull()?.let { soqati ->
                                sharedViewModel.selectedSouvenir = soqati
                                navController.navigate("soqatiDetails")
                            }
                        }
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

                if (city != null) {
                    SoqatiList(items = city.souvenirs, navController = navController)
                }

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