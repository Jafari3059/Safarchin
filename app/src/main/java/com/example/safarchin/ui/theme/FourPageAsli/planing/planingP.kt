package com.example.safarchin.ui.theme.FourPageAsli.planing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.text.style.TextDirection


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

    var selectedTab by remember { mutableStateOf("همه") }
    var selectedCardId by remember { mutableStateOf<Int?>(null) }
    var searchText by remember { mutableStateOf("") }
    var showCreateDialog by remember { mutableStateOf(false) }

    val allTrips = listOf(
        Trip(1, "سفر به شیراز", "تا ۳ روز دیگه", "برنامه‌ریزی شده", R.drawable.khajo),
        Trip(2, "سفر به اصفهان", "تا ۵ روز دیگه", "در حال برنامه‌ریزی", R.drawable.meydan_emam),
        Trip(3, "سفر به تبریز", "تا ۷ روز دیگه", "برنامه‌ریزی شده", R.drawable.shiraz)
    )

    val filteredTrips = allTrips.filter {
        (selectedTab == "همه" || it.description == selectedTab) &&
                it.title.contains(searchText, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // لایه پایین: بدنه اصلی با Scaffold
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f),
            floatingActionButton = {
                if (!showCreateDialog) {
                    FloatingActionButton(
                        onClick = { showCreateDialog = true },
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
                }
            },
            containerColor = Color(0xFFF7F7F7),
            contentWindowInsets = WindowInsets(0.dp) // حذف padding پیش‌فرض (حل مشکل فضای سفید پایین)
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color(0xFFF7F7F7))
                    .offset(y = (-screenHeight * 0.001f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "سفرهای من",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (screenHeight * 0.07f))
                        .padding(end = screenWidth * 0.06f),
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
                            .offset(y = (screenHeight * 0.012f))
                            .width(screenWidth * 0.20f)
                            .height(screenHeight * 0.12f)
                    )
                }

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
                    screenHeight = screenHeight
                )
            }
        }

        // لایه بالایی: نیمه‌شفاف و دیالوگ کامل روی صفحه
        if (showCreateDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .zIndex(1f)
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { showCreateDialog = false })
                    },
                contentAlignment = Alignment.Center
            ) {
                CreateTripDialog(onDismiss = { showCreateDialog = false })
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

@Composable
fun CreateTripDialog(onDismiss: () -> Unit) {
    var tripName by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("انتخاب شهر") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var travelers by remember { mutableStateOf("") }
    var selectedBudget by remember { mutableStateOf("انتخاب بودجه") }
    var budgetForAll by remember { mutableStateOf(true) }

    val cities = listOf("تهران", "مشهد", "شیراز", "فارس", "مازندران", "خوزستان", "اصفهان", "قم")
    val budgets = listOf(" 800000 "," 1000000 "," 1500000 ", " 2000000 "," 3500000 "," 5000000 ")

    var showCityDialog by remember { mutableStateOf(false) }
    var showBudgetDialog by remember { mutableStateOf(false) }

    var showDatePicker by remember { mutableStateOf(false) }
    var isStartDate by remember { mutableStateOf(true) }

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBCBCBC).copy(alpha = 0.63f))
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = { onDismiss() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier.zIndex(1f)) {
            Card(
                modifier = Modifier.size(350.dp, 577.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F4F4)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box {
                    Column(
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("یه سفر جدید بساز و برنامه‌ش رو بچین", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                        Text("اسم سفر", fontSize = 12.sp)
                        CustomTextField(
                            value = tripName,
                            onValueChange = { tripName = it },
                            width = 296.dp,
                            height = 45.dp,
                            placeholder = ""
                        )

                        Text("کجا می‌خوای بری؟", fontSize = 12.sp)
                        Box(
                            modifier = Modifier
                                .width(296.dp)
                                .wrapContentHeight()
                        ) {
                            CustomDropdownField(
                                value = selectedCity,
                                onClick = { showCityDialog = true },
                                width = 296.dp,
                                height = 45.dp
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Column(horizontalAlignment = Alignment.End) {
                                Text("تاریخ پایان", fontSize = 12.sp)
                                CustomTextField(
                                    value = endDate,
                                    onValueChange = {},
                                    width = 91.dp,
                                    height = 33.dp,
                                    placeholder = "مثلاً ۱۴۰۳/۰۲/۱۵",
                                    readOnly = true,
                                    onClick = {
                                        isStartDate = false
                                        showDatePicker = true
                                    }
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("تاریخ شروع", fontSize = 12.sp)
                                CustomTextField(
                                    value = startDate,
                                    onValueChange = {},
                                    width = 91.dp,
                                    height = 33.dp,
                                    placeholder = "مثلاً ۱۴۰۳/۰۲/۱۰",
                                    readOnly = true,
                                    onClick = {
                                        isStartDate = true
                                        showDatePicker = true
                                    }
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("تعداد همسفران", fontSize = 12.sp)
                            CustomTextField(
                                value = travelers,
                                onValueChange = { travelers = it },
                                width = 91.dp,
                                height = 33.dp,
                                placeholder = ""
                            )
                        }

                        Text("میزان بودجه؟", fontSize = 12.sp)
                        Box(
                            modifier = Modifier
                                .width(296.dp)
                                .wrapContentHeight()
                        ) {
                            CustomDropdownField(
                                value = selectedBudget,
                                onClick = { showBudgetDialog = true },
                                width = 296.dp,
                                height = 45.dp
                            )
                        }

                        Text(
                            "این بودجه برای کل همسفران در نظر گرفته شود یا فقط خود شما؟",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            BudgetRadioButton(
                                selected = !budgetForAll,
                                text = "فقط خودم",
                                onClick = { budgetForAll = false }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            BudgetRadioButton(
                                selected = budgetForAll,
                                text = "برای همه",
                                onClick = { budgetForAll = true }
                            )
                        }

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(
                                onClick = { /* ذخیره‌سازی */ },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                                modifier = Modifier
                                    .width(131.dp)
                                    .height(53.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("شروع برنامه‌ریزی سفر", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }

                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.zabdar),
                            contentDescription = "بازگشت",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            if (showCityDialog) {
                Box(
                    modifier = Modifier
                        .offset(x = (-248).dp, y = 180.dp)
                        .zIndex(2f)
                        .align(Alignment.TopEnd)
                ) {
                    CityDropdownMenu(
                        modifier = Modifier.width(60.dp),
                        cities = cities,
                        onCitySelected = {
                            selectedCity = it
                            showCityDialog = false
                        },
                        onDismissRequest = { showCityDialog = false }
                    )
                }
            }

            if (showBudgetDialog) {
                Box(
                    modifier = Modifier
                        .offset(x = (-248).dp, y = 397.dp) // جدا از منوی شهر
                        .zIndex(2f)
                        .align(Alignment.TopEnd)
                ) {
                    BudgetDropdownMenu(
                        modifier = Modifier.width(60.dp),
                        budgets = budgets,
                        onBudgetSelected = {
                            selectedBudget = it
                            showBudgetDialog = false
                        },
                        onDismissRequest = { showBudgetDialog = false }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    width: Dp,
    height: Dp,
    placeholder: String,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(8.dp)) // ✅ کادر اضافه شد
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        contentAlignment = Alignment.CenterEnd
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Right,
                textDirection = TextDirection.ContentOrRtl
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.fillMaxSize()) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp),
                            style = TextStyle(
                                textDirection = TextDirection.ContentOrRtl
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
fun CustomDropdownField(
    value: String,
    onClick: () -> Unit,
    width: Dp,
    height: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clickable { onClick() }
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFCFD1D4), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // آیکن سمت چپ
            Icon(
                painter = painterResource(id = R.drawable.popdown),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Black
            )

            // متن سمت راست
            Text(
                text = value,
                fontSize = 12.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                color = Color.Black
            )
        }
    }
}

@Composable
fun CityDropdownMenu(
    modifier: Modifier = Modifier,
    cities: List<String>,
    onCitySelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .width(57.dp)
            .heightIn(max = 153.dp) // قابل اسکرول
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            cities.forEachIndexed { index, city ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)//سایز باکس شهرها
                        .background(
                            if (index % 2 == 0) Color(0xFFFFA871) else Color(0xFFFF8859),
                            shape = RoundedCornerShape(0.dp)
                        )
                        .clickable {
                            onCitySelected(city)
                            onDismissRequest()
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = city,
                        modifier = Modifier.padding(end = 12.dp),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BudgetDropdownMenu(
    modifier: Modifier = Modifier,
    budgets: List<String>,
    onBudgetSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .width(57.dp)
            .heightIn(max = 153.dp)
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            budgets.forEachIndexed { index, budget ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)
                        .background(
                            if (index % 2 == 0) Color(0xFFF5F5F5) else Color.White,
                            shape = RoundedCornerShape(0.dp)
                        )
                        .clickable {
                            onBudgetSelected(budget)
                            onDismissRequest()
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = budget,
                        modifier = Modifier.padding(end = 12.dp),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun BudgetRadioButton(
    selected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(if (selected) Color(0xFFFF7B54) else Color(0xFFCFD1D4))
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun planingPPreview() {
    val fakeNavController = rememberNavController()
    planingP(navController = fakeNavController)
}
