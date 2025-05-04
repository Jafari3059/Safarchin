package com.example.safarchin.ui.theme.FourPageAsli.HomePage.spqati.Souvenir

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun SouvenirDetailScreen(
    navController: NavController,
    name: String,
    description: String,
    imageResList: List<Int>
)

{    val soqatiItem = Soqati(
        name = "کلوچه مسقطی",
        description = "شیرینی کاک یکی از سوغات خوشمزه شیراز و کرمانشاه است که از لایه‌های نازک تهیه شده و با پودر قند تزئین می‌شود. ماندگاری بالا دارد و برای دورهمی و ایام عید عالی است...",
        imageResList = listOf(
            R.drawable.khajo,
            R.drawable.shiraz,
            R.drawable.meydan_emam
        )
    )

    val imageList = remember { mutableStateListOf<Int>().apply { addAll(soqatiItem.imageResList) } }

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

            IconButton(onClick = {
                navController.popBackStack()

            }) {
                Image(
                    painter = painterResource(id = R.drawable.save),
                    contentDescription = "Save",
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFBEBAB3))
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageList[2 % imageList.size]),
                contentDescription = "Next Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(60.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .alpha(0.5f)
                    .clickable {
                        val last = imageList.removeAt(imageList.size - 1)
                        imageList.add(0, last)
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .width(240.dp)
                    .height(170.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .animateContentSize()
            ) {
                Image(
                    painter = painterResource(id = imageList[1 % imageList.size]),
                    contentDescription = "Main Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = painterResource(id = imageList[0]),
                contentDescription = "Previous Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(60.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .alpha(0.5f)
                    .clickable {
                        val first = imageList.removeAt(0)
                        imageList.add(first)
                    }
            )
        }

        Text(
            text = "${imageList.indexOf(imageList[1]) + 1}/${imageList.size}",
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
//    SouvenirDetailScreen()
//}
