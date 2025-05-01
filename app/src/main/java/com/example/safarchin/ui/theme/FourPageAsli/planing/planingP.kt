package com.example.safarchin.ui.theme.FourPageAsli.planing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.unit.Dp
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar

// داده‌ها

data class Trip(
    val id: Int,
    val title: String,
    val date: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun planingP(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var selectedNavIndex by remember { mutableStateOf(2) }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }
    var selectedTab by remember { mutableStateOf("همه") }
    var searchText by remember { mutableStateOf("") }

    val allTrips = listOf(
        Trip(1, "سفر به شیراز", "تا ۳ روز دیگه", "برنامه‌ریزی شده", R.drawable.khajo),
        Trip(2, "سفر به اصفهان", "تا ۵ روز دیگه", "در حال برنامه‌ریزی", R.drawable.meydan_emam),
        Trip(3, "سفر به تبریز", "تا ۷ روز دیگه", "برنامه‌ریزی شده", R.drawable.shiraz)
    )

    val filteredTrips = allTrips.filter {
        (selectedTab == "همه" || it.description == selectedTab) &&
                it.title.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO */ },
                shape = CircleShape,
                modifier = Modifier
                    .size(56.dp)
                    .offset(x = (-14).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "افزودن سفر",
                    tint = Color.Unspecified
                )
            }
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .offset(y = (-screenHeight * 0.001f)), // 👈 اضافه کن این خط رو
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // متن عنوان - جای مناسب فعلی
            Text(
                text = "سفرهای من",
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (screenHeight * 0.07f)) // ⬅️ حرکت به بالا
                    .padding(end = screenWidth * 0.06f),
                fontSize = (screenWidth.value * 0.045f).sp,
                fontWeight = FontWeight.Bold,
                fontFamily = iranSans,
                color = Color.Black,
                textAlign = TextAlign.Right
            )
//            Text(
//                text = "سفرهای من",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        top = screenHeight * 0.098f,
//                        end = screenWidth * 0.04f
//                    ),
//                fontSize = (screenWidth.value * 0.045f).sp,
//                fontWeight = FontWeight.Bold,
//                fontFamily = iranSans,
//                color = Color.Black,
//                textAlign = TextAlign.Right
//            )

            // لوگو
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidth * 0.15f),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vec1),
                    contentDescription = "لوگوی سفرچین",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .offset(y = (screenHeight * 0.012f))
                        .width(screenWidth * 0.20f)
                        .height(screenHeight * 0.12f) // 🔽 کمی کوچک‌تر برای بالا رفتن
                )
            }


            //  Spacer(modifier = Modifier.height(screenHeight * 0.005f)) // 🔽 خیلی کمتر شده
            // نوار جستجو
            SearchBar(
                value = searchText,
                onValueChange = { searchText = it },
                placeholderText = "جستجو در سفرهای شما...",
                cornerRadius = 30.dp,
                iconOnLeft = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(screenHeight * 0.015f)) // 🔽 کمتر از قبل

            // تب‌ها
            val tabs = listOf("در حال برنامه‌ریزی", "به اتمام رسیده‌ها", "همه")
            TabBar(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    selectedCardId = null
                },
                modifier = Modifier.padding(horizontal = screenWidth * 0.06f)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            // کارت‌های سفر
            TripCardsSection(
                trips = filteredTrips,
                selectedCardId = selectedCardId,
                onCardMoreClick = { clickedId ->
                    selectedCardId = if (selectedCardId == clickedId) null else clickedId
                },
                screenWidth = screenWidth,
                screenHeight = screenHeight
            )
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TripCardsSection(
    trips: List<Trip>,
    selectedCardId: Int?,
    onCardMoreClick: (Int) -> Unit,
    screenWidth: Dp,
    screenHeight: Dp
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidth * 0.077f),
            horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.042f),
            verticalArrangement = Arrangement.spacedBy(screenHeight * 0.02f),
            maxItemsInEachRow = 2
        ) {
            trips.forEach { trip ->
                TripCard(
                    trip = trip,
                    isDeleteBoxVisible = selectedCardId == trip.id,
                    onMoreIconClick = { onCardMoreClick(trip.id) },
                    screenWidth = screenWidth
                )
            }
        }
    }
}

@Composable
fun TripCard(
    trip: Trip,
    isDeleteBoxVisible: Boolean,
    onMoreIconClick: () -> Unit,
    screenWidth: Dp
) {
    val cardWidth = screenWidth * 0.40f

    Card(
        modifier = Modifier
            .width(cardWidth)
            .aspectRatio(0.95f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = trip.imageRes),
                contentDescription = "عکس سفر",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.3f))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = trip.title,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = trip.date,
                    fontFamily = iranSans,
                    fontSize = 11.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = trip.description,
                    fontFamily = iranSans,
                    fontSize = 11.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .width(cardWidth * 0.6f)
                            .height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "شروع برنامه",
                            fontFamily = iranSans,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }

                    Box {
                        IconButton(onClick = { onMoreIconClick() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.etc1),
                                contentDescription = "آیکون حذف",
                                modifier = Modifier.size(20.dp),
                                tint = Color.Unspecified
                            )
                        }

                        if (isDeleteBoxVisible) {
                            Box(
                                modifier = Modifier
                                    .offset(y = (-16).dp, x = (-6).dp)
                                    .background(
                                        color = Color(0xFFFFB26B),
                                        shape = RoundedCornerShape(12.dp) // گردی بیشتر برای مستطیل خوش‌فرم
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFFF7B54),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable { }
                                    .padding(horizontal = 10.dp, vertical = 6.dp) // عرض بیشتر نسبت به ارتفاع
                            ) {
                                Text(
                                    text = "حذف",
                                    color = Color.White,
                                    fontFamily = iranSans,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun planingPPreview() {
    val fakeNavController = rememberNavController()
    planingP(navController = fakeNavController)
}
