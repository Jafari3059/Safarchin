package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import android.content.Context
import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HeaderSection
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.loadCitiesFromAssets
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.rest_kafe
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.shopCenter
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP.calculateDaysLeft
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripEntity
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripsViewModel
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.DatabaseProvider
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.UserEntity
import com.example.safarchin.ui.theme.FourPageAsli.Profile.popupfirstlogin
import com.example.safarchin.ui.theme.FourPageAsli.SearchBar
import com.example.safarchin.ui.theme.iranSans
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP.calculateDaysLeft

@Composable
fun HomeP(navController: NavController, phone: String) {
    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val isNewUser = remember { mutableStateOf(false) }
    val cityList = remember { mutableStateOf<List<City>>(emptyList()) }
    val sharedViewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)

    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
//    val phone = sharedPreferences.getString("current_phone", "") ?: ""

    val tripsViewModel: TripsViewModel = viewModel()
    val lastTripState = produceState<TripEntity?>(initialValue = null) {
        value = withContext(Dispatchers.IO) {
            tripsViewModel.getLastPlannedTrip(phone)  // ✅ حالا phone مقدار داره
        }
    }

    val lastTrip = lastTripState.value
    val daysLeft = lastTrip?.startDate?.let { calculateDaysLeft(it) } ?: -1
    LaunchedEffect(lastTrip) {
        Log.d("TRIP_DEBUG", "LastTrip: $lastTrip")
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            val cityDao = db.cityDao()
            val existing = cityDao.getAllCities()
            if (existing.isEmpty()) {
                Log.d("DB_INIT", "Inserting initial cities from assets...")
                try {
                    val initialCities = loadCitiesFromAssets(context)
                    cityDao.insertAll(initialCities)
                } catch (e: Exception) {
                    Log.e("DB_INIT_ERROR", "Failed to load cities: ${e.message}", e)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        val citiesFromDb = withContext(Dispatchers.IO) {
            db.cityDao().getAllCities()
        }

        cityList.value = citiesFromDb.map {
            City(
                name = it.name,
                description = it.description,
                imageRes = it.imageRes,
                latitude = it.latitude,
                longitude = it.longitude,
                touristPlaces = Gson().fromJson(it.touristPlacesJson, object : TypeToken<List<TourPlace>>() {}.type),
                shoppingCenters = Gson().fromJson(it.shoppingCentersJson, object : TypeToken<List<shopCenter>>() {}.type),
                souvenirs = Gson().fromJson(it.souvenirsJson, object : TypeToken<List<Soqati>>() {}.type),
                restaurants = Gson().fromJson(it.restaurantsJson, object : TypeToken<List<rest_kafe>>() {}.type)
            )
        }

    }

    LaunchedEffect(true) {
        val currentUser = withContext(Dispatchers.IO) {
            db.userDao().getUserByPhone(phone)
        }
        Log.d("USER_IMAGE", "imageUri = ${currentUser?.imageUri}")
    }

    // مربوط به گرفتن اطلاعات از پاپ اپ و بروز کردن هدرسکشن
    var userInfo by remember { mutableStateOf<UserEntity?>(null) }
    LaunchedEffect(Unit) {
        val currentUser = withContext(Dispatchers.IO) {
            db.userDao().getUserByPhone(phone)
        }
        isNewUser.value = currentUser == null
        if (currentUser != null) {
            userInfo = currentUser
        }
    }
// مربوط به گرفتن اطلاعات از پاپ اپ و بروز کردن هدرسکشن


    Log.d("PHONE_CHECK", "Phone received: $phone")



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
    LaunchedEffect(Unit) {
        val user = withContext(Dispatchers.IO) {
            db.userDao().getUserByPhone(phone)
        }
        isNewUser.value = user == null
        if (user != null) {
            userInfo = user
        }
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
                onNotificationClick = {},
                onHelpClick = {},
                user = userInfo
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
                CityCardList(
                    cityList = cityList.value,
                    navToCityScreen = { city ->
                        sharedViewModel.selectedCity = city
                        navController.navigate("cityDetail")

                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .height(115.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            // ✅ باکس سمت راست: سفر برنامه‌ریزی‌شده یا نبود آن
            if (lastTrip != null && daysLeft >= 0) {
                Box(
                    modifier = Modifier
                        .width(123.dp)
                        .height(115.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterEnd)
                ) {
                    Image(
                        painter = painterResource(id = lastTrip.imageRes),
                        contentDescription = "Trip Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )

                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$daysLeft",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.White,
                            style = TextStyle(shadow = Shadow(Color.Black, Offset(0f, 0f), 8f))
                        )
                        Text(
                            text = "روز مانده به سفر ${lastTrip.city}",
                            fontFamily = iranSans,
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(shadow = Shadow(Color.Black, Offset(0f, 0f), 8f)),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(123.dp)
                        .height(115.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFD8D8D8).copy(alpha = 0.3f))
                        .align(Alignment.CenterEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "برنامه‌ریزی نشده",
                        fontFamily = iranSans,
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

            // ✅ باکس سمت چپ: WeatherCard همیشه در چپ
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                WeatherCard()
            }
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
                val userLat = 35.6892
                val userLon = 51.3890

                // ✅ لیست کارت‌ها
                Nearest_citiesCard(
                    userLat = userLat,
                    userLon = userLon,
                    cityList = cityList.value
                )

            }
        }


        Spacer(modifier = Modifier.height(90.dp)) // 👈 این کمک می‌کنه بنر کامل دیده بشه

    }
    val scope = rememberCoroutineScope()

    if (isNewUser.value) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = false
            )
        ) {
            popupfirstlogin(
                phone = phone,
                onSave = { user ->
                    scope.launch {
                        db.userDao().insertUser(user)
                        userInfo = user     // ✅ اطلاعات جدید کاربر در هوم اسکرین
                        isNewUser.value = false
                    }
                }
            )
        }
    }
}
//@Preview(showSystemUi = true, showBackground = true, locale = "fa")
//@Composable
//fun PreviewHomeP() {
//    val navController = rememberNavController()
//    HomeP(navController = navController, phone = "09123456789") // مقدار تستی
//}


//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeP()
//}
