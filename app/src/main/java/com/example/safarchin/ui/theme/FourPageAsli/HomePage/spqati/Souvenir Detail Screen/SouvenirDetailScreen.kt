package com.example.safarchin.ui.theme.FourPageAsli.HomePage.spqati.Souvenir

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.iranSans

@Composable
fun SouvenirDetailScreen(
    navController: NavController,
){
    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
    val souvenir = viewModel.selectedSouvenir

    if (souvenir == null) {
        // نمایش یک پیام خطا یا بارگذاری مجدد
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text("خطا: اطلاعات سوغاتی یافت نشد", color = Color.Red, fontSize = 16.sp)
        }
        return
    }

    val imageResList = souvenir.imageResList
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
                navController.popBackStack() // رفتن به عقب

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
                    text = souvenir.name,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = souvenir.description,
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
//    SouvenirDetailScreen()
//}
