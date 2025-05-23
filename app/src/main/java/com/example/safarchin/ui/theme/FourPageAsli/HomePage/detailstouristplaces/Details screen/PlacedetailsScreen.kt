package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.Details

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitHorizontalDragOrCancellation
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.iranSans
//import com.google.android.gms.maps.model.CameraPosition
//import com.google.maps.android.compose.*
//import com.google.android.gms.maps.model.LatLng

@Composable
fun PlacedetailsScreen(
    navController: NavController,
    name: String,
    description: String,
    imageResList: List<Int>
){
    val soqatiItem = Soqati(
        name = "میدان نقش جهان(امام)",
        description = "میدان نقش جهان یا میدان امام اصفهان، یکی از مهم ترین جاذبه های گردشگری و میدان مرکزی شهراصفهان است. در این میدان بسیاری دیگر از بناهای تاریخی و باستانی نیز قرار گرفته است. \u2028میدان نقش جهان اصفهان، ثبت سازمان یونسکو شده است به همین دلیل شهرت و شکوه بین المللی دارد.\n" +
                "این میدان در شمال شهر اصفهان واقع شده است. سالانه گردشگران بسیار زیادی از این میدان دیدن می کنند. پیش از سال ۱۳۵۷، به این منطقه میدان شاه نیز گفته می شد. پس از پیروزی انقلاب اسلامی، نقش جهان به طور رسمی میدان امام اصفهان نیز نامیده شد. نقش جهان در طول تاریخ با نام های میدان اصلی، میدان بزرگ، میدان قصر، میدان سلطنتی و میدان نو نیز شناخته می شد. \u2028این میدان در ۸ بهمن ۱۳۱۳ در فهرست آثار ملی ثبت شد و در سال ۱۳۵۸ نیز در لیست میراث جهانی یونسکو به ثبت رسید. \u2028پیش از دوره صفویه، در این میدان یک باغ زیبا به نام نقش جهان وجود داشت. نقش جهان نام شهری در کشور آذربایجان بود که امروزه به نخجوان تغییر پیدا کرده است.\u2028این میدان طولی بیشتر از ۵۶۰ متر و عرضی برابر با ۱۶۰ متر دارد. محیط این میدان با ۲۰۰ حجره دو طبقه پوشانده شده است. بناهای تاریخی زیبایی هم در دل نقش جهان قرار گرفته است. در گذشته برای حفاظت از میدان و دیگر آثار تاریخی طاق هایی در اطراف آن ساخته شد که این طاق ها به حجره های امروزی تبدیل شده اند.",
        imageResList = listOf(
            R.drawable.khajo,
            R.drawable.shiraz,
            R.drawable.meydan_emam,
            R.drawable.profile_image,
            R.drawable.khajo,
            R.drawable.khajo
        )
    )
    val imageList = remember { mutableStateListOf<Int>().apply { addAll(imageResList) } }

    var middleIndex by remember { mutableStateOf(1) }
    val currentIndex = remember { mutableStateOf(0) }
    val imageCount = imageResList.size

    val gestureModifier = Modifier.pointerInput(Unit) {
        awaitPointerEventScope {
            while (true) {
                val down = awaitPointerEvent().changes.firstOrNull() ?: continue
                if (!down.pressed) continue
                val drag = awaitHorizontalDragOrCancellation(down.id)
                drag?.let {
                    if (it.positionChange().x > 0) {
                        // ➡️ به راست
                        currentIndex.value =
                            if (currentIndex.value - 1 < 0) imageCount - 1 else currentIndex.value - 1
                    } else if (it.positionChange().x < 0) {
                        // ⬅️ به چپ
                        currentIndex.value = (currentIndex.value + 1) % imageCount
                    }
                }
            }
        }
    }




    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val titleFontSize = (screenWidth * 0.03).sp
    val descFontSize = (screenWidth * 0.038).sp

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 44.dp)
                .fillMaxWidth()
                .height(45.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
//                navController.popBackStack() // رفتن به عقب

            }) {
                Image(
                    painter = painterResource(id = R.drawable.next_icon),
                    contentDescription = "Back",
                    modifier = Modifier.size(36.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "جزئیات",
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = descFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            var isSaved by remember { mutableStateOf(false) }

            IconButton(onClick = {
                isSaved = !isSaved  // تغییر وضعیت انتخاب
            }) {
                Image(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = "Save",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(
                        if (isSaved) Color(0xFFFF9800) else Color(0xFFBEBAB3)  // نارنجی یا خاکستری
                    )
                )
            }

        }

        var middleIndex by remember { mutableStateOf(1) }
        val imageList = remember { mutableStateListOf<Int>().apply { addAll(imageResList) } }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            when (imageResList.size) {
                0 -> {
                    // بدون عکس → تصویر پیش‌فرض وسط، با ارتفاع بیشتر
                    Box(
                        modifier = Modifier
                            .width(290.dp)
                            .height(170.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.placeholder), // عکس پیش‌فرض
                            contentDescription = "No Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                1 -> {
                    Box(
                        modifier = Modifier
                            .width(290.dp)
                            .height(170.dp)
                            .clip(RoundedCornerShape(30.dp))
                    ) {
                        Image(
                            painter = painterResource(id = imageResList[0]),
                            contentDescription = "Single Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                2 -> {
                    // فقط دو عکس → قابلیت چرخش اضافه شده
                    val index = remember { mutableStateOf(0) }

                    Image(
                        painter = painterResource(id = imageResList[(index.value + 1) % 2]),
                        contentDescription = "Right Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .height(130.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .alpha(0.5f)
                            .clickable {
                                index.value = (index.value + 1) % 2
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .width(240.dp)
                            .height(170.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .clickable {
                                index.value = (index.value + 1) % 2
                            }
                    ) {
                        Image(
                            painter = painterResource(id = imageResList[index.value]),
                            contentDescription = "Main Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }

                else -> {
                    // حالت معمول با ۳ تصویر یا بیشتر
                    Image(
                        painter = painterResource(id = imageResList[(currentIndex.value - 1 + imageCount) % imageCount]),
                        contentDescription = "Previous Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .height(130.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .alpha(0.5f)
                            .clickable {
                                currentIndex.value =
                                    if (currentIndex.value - 1 < 0) imageCount - 1 else currentIndex.value - 1
                            }
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .width(240.dp)
                            .height(170.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .animateContentSize()
                            .then(gestureModifier)
                    ) {
                        Image(
                            painter = painterResource(id = imageResList[currentIndex.value]),
                            contentDescription = "Main Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        painter = painterResource(id = imageResList[(currentIndex.value + 1) % imageCount]),
                        contentDescription = "Next Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(60.dp)
                            .height(130.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .alpha(0.5f)
                            .clickable {
                                currentIndex.value = (currentIndex.value + 1) % imageCount
                            }
                    )
                }
            }
        }





        Text(
            text = "${currentIndex.value + 1}/$imageCount",
            fontFamily = iranSans,
            fontWeight = FontWeight.Medium,
            fontSize = titleFontSize,
            color = Color.Black,
            textAlign = TextAlign.Right,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .verticalScroll(scrollState)
            ) {
                Text(
                    text = soqatiItem.name,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = soqatiItem.description,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "۵ـ۶ ساعت", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = "مدت زمان بازدید:",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "رایگان", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = "هزینه بازدید:",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.ticket),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "۵ـ۶ ساعت", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = ":آدرس ",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "۸ تا ۲۰", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = ": ساعت کاری",

                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.clock2),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "۰۹۱۳۰۸۰۲۸۸۴", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = ": شماره تماس ",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "دارد", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = ": پارکینگ",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.park),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        // متن متغیر
                        Text(
                            text = "شهدا", // ← این بخش متغیره (مثلاً می‌تونی از یه متغیر بخونیش)
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        // متن ثابت
                        Text(
                            text = ": نزدیکترین ایستگاه مترو",
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.tran),
                            contentDescription = "آیکون توضیح",
                            tint = Color(0xFFFFB26B),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }


                Text("نقشه موقعیت", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
//                MapWithMarker()
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreCards() {
//    PlacedetailsScreen()
//}
@Preview(showBackground = true)
@Composable
fun PreviewPlacedetailsScreen() {
    val fakeNavController = rememberNavController()

    val fakeName = "کلوچه مسقطی"
    val fakeDescription = "پیش‌نمایش از توضیحات تستی برای نمایش در Preview."
    val fakeImages = listOf(
        R.drawable.shiraz,
        R.drawable.khajo,
        R.drawable.meydan_emam
    )

    PlacedetailsScreen(
        navController = fakeNavController,
        name = fakeName,
        description = fakeDescription,
        imageResList = fakeImages
    )
}


//@Composable
//fun MapWithMarker() {
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(LatLng(32.6546, 51.6680), 15f)
//    }
//
//    GoogleMap(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp),
//        cameraPositionState = cameraPositionState
//    ) {
//        Marker(
//            state = MarkerState(position = LatLng(32.6546, 51.6680)),
//            title = "میدان نقش جهان",
//            snippet = "اصفهان"
//        )
//    }
//}

