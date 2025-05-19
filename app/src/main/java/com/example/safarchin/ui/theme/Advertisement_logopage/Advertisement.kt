package com.example.safarchin.ui.theme.Advertisement_logopage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

@Composable
fun AdvertisementScreen(
    imageResId: Int,
    title: String,
    description: String,
    indicatorState: List<Boolean>,
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        Text(
            text = "رد شدن",
            fontSize = (screenWidth * 0.04f).value.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = iranSans,
            color = Color(0xFF5E5E5E),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = screenHeight * 0.05f, end = screenWidth * 0.05f)
                .clickable { onSkip() }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "advertisement",
                modifier = Modifier
                    .width(screenWidth * 0.85f)
                    .height(screenHeight * 0.3f)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = title,
                fontSize = (screenWidth * 0.06f).value.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = iranSans,
                color = Color(0xFF000000),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            Text(
                text = description,
                fontSize = (screenWidth * 0.036f).value.sp,
                fontWeight = FontWeight.Light,
                fontFamily = iranSans,
                textAlign = TextAlign.Center,
                color = Color(0xFF000000),
                modifier = Modifier
                    .width(screenWidth * 0.9f)
                    .height(screenWidth * 0.2f)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            Row(
                modifier = Modifier.width(screenWidth * 0.3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                indicatorState.forEachIndexed { index, isActive ->
                    Image(
                        painter = painterResource(id = if (isActive) R.drawable.circlerangi else R.drawable.circleadver),
                        contentDescription = "indicator",
                        modifier = Modifier.size(screenWidth * 0.025f) // ✅ سایز همیشگی و ثابت
                    )

                    // Spacer بین دایره‌ها ولی آخرین دایره spacer نداشته باشه
                    if (index != indicatorState.lastIndex) {
                        Spacer(modifier = Modifier.width(screenWidth * 0.01f)) // ✅ فاصله‌ی ثابت
                    }
                }
            }


        }

        Button(
            onClick = { onNext() },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = screenHeight * 0.09f)
                .width(screenWidth * 0.5f)
                .height(screenHeight * 0.07f),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFB26B)),
            shape = RoundedCornerShape(screenWidth * 0.04f)
        ) {
            Text(
                text = "بعدی",
                color = Color.White,
                fontSize = (screenWidth * 0.05f).value.sp,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdvertisementScreenPreview() {
    AdvertisementScreen(
        imageResId = R.drawable.ad1,
        title ="سفر فقط رفتن نیست... یه تجربه ست!" ,
        description = "از انتخاب مقصد تا مدیریت بودجه و پیشنهاد جاذبه های دیدنی... \nهمه چیز با چند کلیک، کنارته.\n",
        indicatorState = listOf(true, false, false),
        onNext = {},
        onSkip = {}
    )
}
