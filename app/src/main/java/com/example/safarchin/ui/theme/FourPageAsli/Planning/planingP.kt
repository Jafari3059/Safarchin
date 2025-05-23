package com.example.safarchin.ui.theme.FourPageAsli.Planning

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP.CreateTripDialog
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP.TripCardsSection
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripEntity
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripsViewModel
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.launch


@Composable
fun planingP(navController: NavController) {
    val viewModel: TripsViewModel = viewModel()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val currentPhone = sharedPreferences.getString("current_phone", "") ?: ""
    val allTrips by viewModel.getTrips(userId = currentPhone).collectAsState(initial = emptyList())

    var selectedTab by rememberSaveable { mutableStateOf("همه") }
    var selectedCardId by rememberSaveable { mutableStateOf<Int?>(null) }
    var isPopupVisible by remember { mutableStateOf(false) }
    var editingTrip by remember { mutableStateOf<TripEntity?>(null) }
    val scope = rememberCoroutineScope()

    val fabSize = screenWidth * 0.14f
    val fabPaddingEnd = screenWidth * 0.06f
    val fabPaddingBottom = screenHeight * 0.10f
    val iconSize = screenWidth * 0.12f

    val filteredTrips = allTrips.filter {
        (selectedTab == "همه" || it.status == selectedTab) &&
                it.title.contains(viewModel.searchQuery.value, ignoreCase = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                SearchBar(
                    value = viewModel.searchQuery.collectAsState().value,
                    onValueChange = { viewModel.searchQuery.value = it },
                    placeholderText = "جستجو در سفرهای شما...",
                    cornerRadius = 30.dp,
                    iconOnLeft = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.015f))

                val tabs = listOf("در حال برنامه‌ریزی", "تمام‌شده", "همه")
                TabBar(
                    tabs = tabs,
                    selectedTab = selectedTab,
                    onTabSelected = {
                        selectedTab = it
                        viewModel.selectedTab.value = it
                        selectedCardId = null
                    },
                    modifier = Modifier.padding(horizontal = screenWidth * 0.06f)
                )

                Spacer(modifier = Modifier.height(screenHeight * 0.02f))
            }

            // ✅ بخش کارت‌ها با ترتیب درست
            TripCardsSection(
                trips = filteredTrips,
                selectedCardId = selectedCardId,
                onCardMoreClick = { selectedCardId = it },
                onDeleteClick = {
                    scope.launch { viewModel.deleteTripById(it) }
                },
                onStatusChangeClick = { id, newStatus ->
                    scope.launch { viewModel.updateTripStatus(id, newStatus) }
                },
                onEditClick = {
                    editingTrip = it
                    isPopupVisible = true
                },
                screenWidth = screenWidth,
                navController = navController
            )
        }

        FloatingActionButton(
            onClick = {
                editingTrip = null
                isPopupVisible = true
            },
            shape = CircleShape,
            containerColor = Color(0xFFFF7B00),
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

        if (isPopupVisible) {
            Dialog(
                onDismissRequest = { isPopupVisible = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    dismissOnClickOutside = true
                )
            ) {
                CreateTripDialog(
                    onDismiss = { isPopupVisible = false },
                    navController = navController,
                    viewModel = viewModel,
                    editingTrip = editingTrip
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
