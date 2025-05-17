package com.example.safarchin.ui.theme.FourPageAsli.Planning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.window.DialogProperties
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components.TripCardsSection
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components.CreateTripDialog
import androidx.compose.ui.window.Dialog


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

    var selectedTab by rememberSaveable { mutableStateOf("همه") }
    var selectedCardId by rememberSaveable { mutableStateOf<Int?>(null) }
    var searchText by rememberSaveable { mutableStateOf("") }
    var isPopupVisible = remember { mutableStateOf(false) }
    val fabSize = screenWidth * 0.14f
    val fabPaddingEnd = screenWidth * 0.06f
    val fabPaddingBottom = screenHeight * 0.10f
    val iconSize = screenWidth * 0.12f


    val allTrips = remember {
        listOf(
            Trip(1, "سفر به شیراز", "تا ۳ روز دیگه", "برنامه‌ریزی شده", R.drawable.khajo),
            Trip(2, "سفر به اصفهان", "تا ۵ روز دیگه", "در حال برنامه‌ریزی", R.drawable.meydan_emam),
            Trip(3, "سفر به تبریز", "تا ۷ روز دیگه", "برنامه‌ریزی شده", R.drawable.shiraz)
        )
    }

    val filteredTrips = allTrips.filter {
        (selectedTab == "همه" || it.description == selectedTab) &&
                it.title.contains(searchText, ignoreCase = true)
    }

    Box(
        modifier = Modifier
        .fillMaxSize()
        .wrapContentHeight()
        .background(Color(0xFFF6F4F4)))
    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F4F4)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // عنوان
            Text(
                text = "سفرهای من",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = screenHeight * 0.07f, end = screenWidth * 0.06f),
                fontSize = (screenWidth.value * 0.045f).sp,
                fontWeight = FontWeight.Bold,
                fontFamily = iranSans,
                color = Color.Black,
                textAlign = TextAlign.Right
            )

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
                        .padding(top = screenHeight * 0.012f)
                        .width(screenWidth * 0.20f)
                        .height(screenHeight * 0.12f)
                )
            }

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

            Spacer(modifier = Modifier.height(screenHeight * 0.015f))

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

            TripCardsSection(
                trips = filteredTrips,
                selectedCardId = selectedCardId,
                onCardMoreClick = { clickedId ->
                    selectedCardId = if (selectedCardId == clickedId) null else clickedId
                },
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                navController = navController
            )
        }

        //  دکمه شناور
        FloatingActionButton(
            onClick = { isPopupVisible.value = true },
            shape = CircleShape,
            containerColor =(Color(0xFFFF7B00)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = fabPaddingEnd, bottom = fabPaddingBottom)
                .size(fabSize)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "افزودن سفر",
                tint = Color.Unspecified,
                modifier = Modifier.size(iconSize)
            )
        }

        //  دیالوگ بازشو
        if (isPopupVisible.value) {
            Dialog(
                onDismissRequest = { isPopupVisible.value = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    dismissOnClickOutside = true
                )
            ) {
                CreateTripDialog(
                    onDismiss = { isPopupVisible.value = false },
                    navController = navController
                )
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
