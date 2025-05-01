package com.example.safarchin.ui.theme.FourPageAsli.planing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import com.example.safarchin.ui.theme.components .TabBar
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.safarchin.ui.theme.FourPageAsli.BottomNavigationBar
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.ui.unit.Dp

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
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "افزودن سفر",
                    tint = Color.Unspecified
                )
            }
        },
        bottomBar = {
            BottomNavigationBar(
                selectedIndex = selectedNavIndex,
                onItemSelected = { selectedNavIndex = it }
            )
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight * 0.15f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "تصویر سفر",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(screenWidth * 0.25f)
                        .align(Alignment.TopStart)
                        .offset(x = 30.dp, y = 40.dp)
                )

                Text(
                    text = "سفرهای من",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = iranSans,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = (-40).dp, y = 62.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
//                SearchBar(
//                    value = searchText,
//                    onValueChange = { searchText = it },
//                    placeholderText = "کجا میخوای بری؟",
//                    cornerRadius = 30.dp,
//                    iconOnLeft = true,
//                    modifier = Modifier.width(screenWidth - 32.dp)
//                )

                Spacer(modifier = Modifier.height(10.dp))

                val tabs = listOf("در حال برنامه‌ریزی", "به اتمام رسیده‌ها", "همه")

                TabBar(
                    tabs = tabs,
                    selectedTab = selectedTab,
                    onTabSelected = {
                        selectedTab = it
                        selectedCardId = null
                    },
//                    modifier = Modifier.width(screenWidth - 32.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                TripCardsSection(
                    trips = filteredTrips,
                    selectedCardId = selectedCardId,
                    onCardMoreClick = { clickedId ->
                        selectedCardId = if (selectedCardId == clickedId) null else clickedId
                    },
                    screenWidth = screenWidth
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TripCardsSection(
    trips: List<Trip>,
    selectedCardId: Int?,
    onCardMoreClick: (Int) -> Unit,
    screenWidth: Dp
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
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
    val cardWidth = screenWidth * 0.44f

    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(170.dp),
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
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = trip.title,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = trip.date,
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = trip.description,
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))
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
                            .width(cardWidth * 0.65f)
                            .height(24.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Text(
                            text = "شروع برنامه",
                            fontFamily = iranSans,
                            fontSize = 12.sp,
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
                                    .offset(y = (-40).dp, x = (-10).dp)
                                    .background(Color.Black, shape = RoundedCornerShape(6.dp))
                                    .clickable { /* اینجا کد حذف واقعی اضافه میشه */ }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
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
