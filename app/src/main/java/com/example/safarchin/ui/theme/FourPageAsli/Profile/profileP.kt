package com.example.safarchin.ui.theme.FourPageAsli.Profile

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourCardList
import com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP.TripCard
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripEntity
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.DatabaseProvider
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.UserEntity
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Composable
fun profileP( phone: String) {
    fun createSampleTrip(
        id: Int,
        title: String,
        city: String,
        startDate: String,
        endDate: String,
        budget: Int,
        imageRes: Int,
        userId: String
    ): TripEntity {
        return TripEntity(
            id = id,
            userId = userId,
            title = title,
            city = city,
            startDate = startDate,
            endDate = endDate,
            travelers = 2,
            budget = budget,
            budgetForAll = true,
            status = "در حال برنامه‌ریزی",
            date = "$startDate تا $endDate",
            imageRes = imageRes
        )
    }

    val sampleTrips = listOf(
        createSampleTrip(1, "سفر به یزد", "یزد", "۱۴۰۳/۰۱/۱۵", "۱۴۰۳/۰۱/۲۰", 4000000, R.drawable.khajo, phone),
        createSampleTrip(2, "سفر به شیراز", "شیراز", "۱۴۰۳/۰۲/۲۰", "۱۴۰۳/۰۲/۲۵", 5200000, R.drawable.shiraz, phone),
        createSampleTrip(3, "سفر به تبریز", "تبریز", "۱۴۰۳/۰۳/۰۵", "۱۴۰۳/۰۳/۱۰", 3700000, R.drawable.profile_image, phone)
    )

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)
    val userState = remember { mutableStateOf<UserEntity?>(null) }
    val initialUser by produceState<UserEntity?>(initialValue = null) {
        value = withContext(Dispatchers.IO) {
            db.userDao().getUserByPhone(phone)
        }
    }
    val user = userState.value ?: initialUser
    var isPopupVisible = remember { mutableStateOf(false) }
    val fontSizeMore = (screenWidth.value * 0.03).sp       // حدود 12sp روی گوشی 400dp
    val horizontalPadding = (screenWidth.value * 0.06).dp  // حدود 24dp روی گوشی 400dp
    val navController = rememberNavController()
    val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val phone = sharedPreferences.getString("current_phone", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // ✅ اسکرول‌پذیر کردن کل صفحه
            .background(Color(0xFFF6F4F4))
    ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(370.dp)
                        .background(color = Color(0xFFF6F4F4))
                ){
                    // کاور تصویر
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.27f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.backprof),
                            contentDescription = "Cover Photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .alpha(0.75f)
                        )
                    }

                    // کارت پروفایل سفید
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.45f)
                            .padding(top = screenHeight * 0.16f, start = screenWidth * 0.06f, end = screenWidth * 0.06f)
                            .shadow(
                                elevation = 14.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = false,
                                spotColor = Color(0xFF939B62)
                            )
                            .background(color = Color(0xD4FFFFFF), shape = RoundedCornerShape(10.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = screenHeight * 0.08f)
                        ) {
                            Text(
                                text = "${user?.name} ${user?.lastName}",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.03).sp
                            )

                            Text(
                                text = "${user?.city}، ${user?.age}",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.025).sp,
                                color = Color(0xFF939B62),
                                modifier = Modifier.padding(top = 6.dp)
                            )
                            Text(
                                text = "شهرهای دیده شده: مازندران، شیراز، قم، رشت",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.025).sp,
                                color = Color(0xFF939B62),
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // آمارها
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(screenHeight * 0.12f),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                ProfileStatResponsive("دنبال شونده ها", "1", screenWidth, screenHeight)
                                ProfileStatResponsive("دنبال کننده ها", "20", screenWidth, screenHeight)
                                ProfileStatResponsive("تعداد سفر ها", "3", screenWidth, screenHeight)
                            }
                        }
                    }

                    // عکس پروفایل و آیکون ویرایش
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = screenHeight * 0.12f)
                            .size(screenWidth * 0.25f)
                    ) {
                        val currentUser = user
                        val imageFile = currentUser?.imageUri?.let { File(it) }

                        val painter = if (imageFile != null && imageFile.exists()) {
                            rememberAsyncImagePainter(imageFile)
                        } else {
                            painterResource(id = R.drawable.profile_pic)
                        }

                        Image(
                            painter = painter,
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )



                        Icon(
                            painter = painterResource(id = R.drawable.editproficon),
                            contentDescription = "Edit",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(2.dp)
                                .size(screenWidth * 0.05f)
                                .clickable {
                                    isPopupVisible.value = true // 👈 اینجا
                                },
                            tint = Color(0xFF939B62)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp)
                ) {
                    Column {
                        Text(
                            text = "خلاصه سفرهای من",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Bold,
                            fontSize = (screenWidth.value * 0.026).sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // باکس اول
                            StatBox(
                                title = "گران‌ترین سفر من",
                                value = "۵.۲ میلیون",
                                subtitle = "شیراز",
                                background = Color(0xFFFFB26B)
                            )

                            // باکس دوم
                            StatBox(
                                title = "ارزان‌ترین سفر من",
                                value = "۱.۹ میلیون",
                                subtitle = "مازندران",
                                background = Color(0xB3939B62)
                            )

                            // باکس سوم
                            StatBox(
                                title = "میانگین بودجه",
                                value = "۲.۷ میلیون",
                                subtitle = "",
                                background = Color(0xFFFFB26B)
                            )

                            // باکس چهارم
                            StatBox(
                                title = "تعداد سفرها",
                                value = "۴",
                                subtitle = "",
                                background = Color(0xB3939B62)
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

//                 ✅ بخش: ذخیره شده ها
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = horizontalPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(215.dp),
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
//                                .clickable {
//                                    navController.navigate("tourDetails")
//                                },
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
                                text = "ذخیره شده ها",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.026).sp,
                                color = Color.Black
                            )
                        }

                        // ✅ لیست کارت‌ها
                        TourCardList()

                    }
                }


//                 ✅ بخش: سفرهای فعال
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = horizontalPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // سمت چپ (بیشتر + آیکون)
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
//                                .clickable {
//                                    navController.navigate("tourDetails")
//                                },
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
                                text = "سفر فعال",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.026).sp,
                                color = Color.Black
                            )
                        }

                        // ✅ لیست کارت‌ها
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 0.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            reverseLayout = true // اسکرول به سمت چپ (راست به چپ)
                        ) {
                            items(sampleTrips) { trip ->
                                TripCard(
                                    trip = trip,
                                    navController = navController,
                                    screenWidth = screenWidth,
                                    isDeleteBoxVisible = false,
                                    onMoreIconClick = {},
                                    onDeleteClick = {},
                                    onStatusChangeClick = { id, newStatus -> /* بعداً پیاده‌سازی می‌کنی */ },
                                    onEditClick = {},
                                    onCardCloseMenu = {},
                                )
                            }
                        }


                    }
                }


        Spacer(modifier = Modifier.height(100.dp))
    }

    val scope = rememberCoroutineScope()

    val currentPhone = remember { mutableStateOf(phone) }

    if (isPopupVisible.value) {
        Dialog(
            onDismissRequest = { isPopupVisible.value = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            )
        ) {
            user?.let {
                popupSettting(
                    user = it,
                    onPhoneChange = { newPhone ->
                        currentPhone.value = newPhone
                    },
                    onDismiss = {
                        isPopupVisible.value = false
                    },
                    onSubmit = { updatedUser ->
                        scope.launch {
                            db.userDao().updateUser(updatedUser)

                            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                            sharedPreferences.edit().putString("current_phone", currentPhone.value).apply()

                            userState.value = updatedUser
                            isPopupVisible.value = false
                        }
                    }
                )
            }
        }
    }

}

@Composable
fun ProfileStatResponsive(title: String, value: String, screenWidth: Dp, screenHeight: Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(screenWidth * 0.25f)
            .fillMaxHeight()
    ) {
        Text(
            text = title,
            fontFamily = iranSans,
            fontWeight = FontWeight.Medium,
            fontSize = (screenWidth.value * 0.025).sp,
            color = Color(0xFF5F5F5F),
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val fontSize = minOf(maxWidth.value, maxHeight.value) * 0.4f
            Text(
                text = value,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize.sp,
                color = Color(0xFF000000),
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

@Composable
fun StatBox(title: String, value: String, subtitle: String, background: Color) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(background)
            .padding(6.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                fontFamily = iranSans,
                fontWeight = FontWeight.Medium,
                fontSize = 9.sp,
                color = Color(0xFF5F5F5F)
            )
            Text(
                text = value,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 9.sp,
                    color = Color.Black
                )
            } else {
                val density = LocalDensity.current
                Spacer(
                    modifier = Modifier.height(
                        with(density) { 9.sp.toDp() }
                    )
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreCards() {
//    profileP()
//}