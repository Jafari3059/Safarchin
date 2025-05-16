@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.MapTabContent

import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlacesViewModel
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import androidx.navigation.compose.rememberNavController

@Composable
fun OverviewScreen(navController: NavController) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    val screenHeight = config.screenHeightDp.dp
    val context = LocalContext.current

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("guide_prefs", Context.MODE_PRIVATE)
    val initialGuideCount = sharedPreferences.getInt("guide_shown_count", 0)
    var guideCount by remember { mutableStateOf(initialGuideCount) }

    val isPopupVisible = remember { mutableStateOf(false) }

    val topBoxHeight = screenHeight * 0.33f
    val dateBoxSize = screenWidth * 0.14f
    val dreamCardWeight = 3f
    val tabHeight = screenHeight * 0.08f
    val tabFontSize = screenWidth.value.times(0.03).sp
    val spacerHeight = screenHeight * 0.0001f
    var selectedTabIndex by remember { mutableStateOf(3) }

    val savedPlacesViewModel: SavedPlacesViewModel = viewModel()
    val selectedPlacesViewModel: SelectedPlacesViewModel = viewModel()

    val fabSize = screenWidth * 0.16f
    val fabPaddingEnd = screenWidth * 0.06f
    val fabPaddingBottom = screenHeight * 0.04f
    val iconSize = screenWidth * 0.18f


    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF6F4F4))) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(topBoxHeight)
                    .zIndex(2f)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color(0xFFFBAD0C))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "بازگشت",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 28.dp, top = 44.dp)
                        .size(20.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    tint = Color.Black
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = screenHeight * 0.086f, end = 28.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .offset(x = 8.dp, y = 2.dp),
                        verticalArrangement = Arrangement.spacedBy(90.dp)
                    ) {
                        DateBox("اردیبهشت", "۰۳", dateBoxSize, tabFontSize)
                        DateBox("اردیبهشت", "۰۷", dateBoxSize, tabFontSize)
                    }

                    DreamTripCard(modifier = Modifier.weight(dreamCardWeight))
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(tabHeight + 2.dp)
                    .offset(y = (-tabHeight * 0.30f))
                    .zIndex(1f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(Color.White)
            ) {
                val tabs = listOf("نقشه", "بودجه", "برنامه ریزی", "نمای کلی")
                OverviewTabBar(
                    tabs = tabs,
                    selectedTab = selectedTabIndex,
                    onTabSelected = { selectedTabIndex = it },
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp, top = 28.dp)
                        .align(Alignment.CenterEnd)
                )
            }

            Spacer(modifier = Modifier.height(spacerHeight))

            when (selectedTabIndex) {
                0 -> MapTabContent(navController = navController)
                1 -> {} // بودجه
                2 -> {} // برنامه‌ریزی
                3 -> OverviewTabContent(
                    savedPlacesViewModel = savedPlacesViewModel,
                    selectedPlacesViewModel = selectedPlacesViewModel
                )
            }

        }

        // 🔘 دکمه شناور
        if (!isPopupVisible.value && selectedTabIndex == 3) {
            FloatingActionButton(
                onClick = { isPopupVisible.value = true },
                shape = CircleShape,
                containerColor = Color(0xFFFFD56B),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = fabPaddingEnd, bottom = fabPaddingBottom)
                    .size(fabSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.newplace),
                    contentDescription = "افزودن مکان",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(iconSize)
                )
            }
        }

        // 💬 دیالوگ راهنمایی
        if (isPopupVisible.value) {
            Dialog(
                onDismissRequest = { isPopupVisible.value = false },
                properties = DialogProperties(
                    usePlatformDefaultWidth = false,
                    dismissOnClickOutside = true
                )
            ) {
                GuidePopup(
                    onDismiss = {
                        guideCount++
                        sharedPreferences.edit().putInt("guide_shown_count", guideCount).apply()
                        isPopupVisible.value = false
                    },
                    navController = navController // 👈 این خط اضافه شد تا ارور رفع بشه
                )
            }
        }
    }
}

@Composable
fun DateBox(label: String, value: String, size: Dp, fontSize: TextUnit) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF6F4F4)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = label, fontFamily = iranSans, fontSize = fontSize * 0.85f, color = Color.Gray)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = fontSize * 1.1f)
        }
    }
}
@Composable
fun DestinationCard(imageRes: Int, title: String) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    val cardWidth = (screenWidth * 0.38).dp
    val cardHeight = (screenHeight * 0.40).dp
    val imageHeight = (screenHeight * 0.11).dp
    val buttonWidth = (screenWidth * 0.25).dp
    val buttonHeight = (screenHeight * 0.045).dp
    val fontSize = (screenWidth * 0.03).sp
    val buttonFontSize = (screenWidth * 0.025).sp

    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                fontSize = fontSize,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF939B62)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(buttonWidth)
                    .height(buttonHeight)
            ) {
                Text("افزودن به برنامه", fontSize = buttonFontSize, color = Color.White)
            }
        }
    }
}

@Composable
fun ColumnBox(count: String, label: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val boxSize = (screenWidth * 0.11).dp
    val fontSize = (screenWidth * 0.028).sp

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = count, fontWeight = FontWeight.Bold, fontSize = fontSize)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = fontSize, color = Color.White)
    }
}

@Composable
fun InfoIconWithText(iconId: Int, text: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val iconSize = (screenWidth * 0.04).dp
    val fontSize = (screenWidth * 0.03).sp

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = fontSize, color = Color.Gray)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewInputField(
    placeholder: String,
    height: Dp = 50.dp,
    width: Dp = 348.dp
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val fontSize = (screenWidth * 0.03).sp
    val iconSize = (screenWidth * 0.04).dp

    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(12.dp)),
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                textAlign = TextAlign.Right,
                fontFamily = iranSans,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.down),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(iconSize)
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            cursorColor = Color.Black,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SectionWithIcon(title: String, icon: Int) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val fontSize = (screenWidth * 0.032).sp
    val iconSize = (screenWidth * 0.05).dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = fontSize)
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun OverviewTabBar(
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 16.dp, top = 14.dp, bottom = 0.dp)
                    .clickable { onTabSelected(index) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Black else Color.Gray,
                    textAlign = TextAlign.Center
                )
                if (isSelected) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(40.dp)
                            .background(Color(0xFFFBAD0C), RoundedCornerShape(1.dp))
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewOverviewScreen() {
    val navController = rememberNavController() // ✅ ساخت نمونه تستی
    OverviewScreen(navController = navController) // ✅ پاس دادن به تابع
}
