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
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.iranSans

@Composable
fun PlacedetailsScreen(
    navController: NavController,
    name: String,
    description: String,
    imageResList: List<Int>
)

{    val soqatiItem = Soqati(
    name = "کلوچه مسقطی",
    description = "شیرینی کاک یکی از سوغات خوشمزه شیراز و کرمانشاه است که از لایه‌های نازک تهیه شده و با پودر قند تزئین می‌شود. ماندگاری بالا دارد و برای دورهمی و ایام عید عالی است.شیرینی کاک یکی از سوغات خوشمزه شیراز و کرمانشاه و خرم آباد محسوب می شود به این شیرینی در شیراز نان یوخه و در کرمانشاه نان کاک یا شیرینی کاک گفته می شود. این شیرینی خوشمزه از لایه های بسیار نازک تهیه می شود که روی هم قرار گرفته و با پودر قند و پسته تزئین می شود. این شیرینی طرز تهیه راحتی دارد و ماندگاری بالایی نیز دارد و شما می توانید برای دورهمی یا ایام عید این شیرینی یوخه را تهیه کنید.\n" +
            "\n" +
            "مواد لازم\n" +
            "تخم مرغ۱ عدد\n" +
            "کره۲۰۰ گرم\n" +
            "آب ولرم کمتر ۱/۲ پیمانه + ۱/۴ پیمانه \n" +
            "برای خمیر مایه خمیر مایه۱ ق چای خوریوانیلنوک ق چای خوریآرد گندمحدود ۶۰۰ گرمنمک۱ ق چای خوریگلاب۱ ق غذا خوریپودر هل و پودر قندبه مقدار لازم\n" +
            "طرز تهیه شیرینی یوخه\n" +
            "1مرحله اول: عمل آوری خمیر مایه\n" +
            "داخل یک لیوان ۱/۴ پیمانه آب ولرم را ریخته و خمیر مایه و ۱ ق چای خوری شکر اضافه کنید و هم بزنید سپس روی لیوان را به مدت ۲۰ دقیقه بپوشانید تا کف کند و عمل بیاد (مهم ترین نکته دمای آب است که باید در حدی باشد که انگشت را نسوزاند).\n" +
            "2مرحله دوم: مخلوط کردن مواد\n" +
            "داخل یک کاسه تخم مرغ ها را ریخته و با وانیل هم بزنید تا تخم مرغ ها از لختگی خارج شود سپس کره نرم شده را ریخته و هم بزنید تا مواد مخلوط شوند سپس به ترتیب مابقی آب و خمیر مایه عمل آمده را به همراه گلاب اضافه کنید و هم بزنید.\n" +
            "3مرحله سوم: استراحت خمیر نان یوخه\n" +
            "آرد و نمک را نمک را با هم مخلوط کنید و سه بار الک کنید، باید به تدریج شروع به اضافه کردن آرد کنید تا خمیر وسط کاسه جمع شود و بسته به آردها و جنس های مختلف آن شاید کمتر یا بیشتر آرد مصرف شود سپس خمیر را روی میز کار بیاورید و به مدت ۱۰ دقیقه خوب ورز دهید تا نرم و لطیف شود سپس داخل کاسه تمیز گذاشته و بمدت ۲ ساعت در دمای گرم قرار داده تا خمیر حجم بگیرد و پف کند.\n" +
            "4مرحله چهارم: چونه گرفتن شیرینی کاک\n" +
            "بعد از استراحت خمیر پف آن را با مشت بگیرید و دوباره برای چند دقیقه خمیر را ورز دهید سپس به اندازه یک نارنگی از خمیر برداشته و گرد کنید و روی دستمال تمیز قرار دهید و روی چونه ها را بپوشانید و مجدد برای ۱۰ دقیقه استراحت بدهید تا خمیر دوباره حجم بگیرد.\n" +
            "5مرحله پنجم: تهیه شیرینی یوخه شیرازی\n" +
            "هر کدام از چونه ها را روی سطح آرد پاشی شده وردنه بکشید به طوری که آنقدر نازک شود که دست از پشت آن دیده و یا ازش عبور کند.\n" +
            "6مرحله ششم: پخت شیرینی یوخه یا کاک شیرازی\n" +
            "برای تهیه شیرینی نان یوخه شما می توانید به دو روش عم",
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
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreCards() {
//    PlacedetailsScreen()
//}
