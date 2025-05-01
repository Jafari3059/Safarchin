package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont

@Composable
fun sugestiontrip() {
    Box(
        modifier = Modifier
            .height(180.dp)
//            .width(340.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp), // بیشتر کردم که هم هدر هم Row دومی جا بش
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // سمت چپ (بیشتر + آیکون)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.next_icon),
                        contentDescription = "Next Icon",
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "بیشتر",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }

                // سمت راست (سفر فعال)
                Text(
                    text = "سفر پیشنهادی سفرچین",
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawRoundRect(
                        color = Color.Red,
                        size = size,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                        style = Stroke(
                            width = 4f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    Canvas(modifier = Modifier.matchParentSize()) {
                        drawRoundRect(
                            color = Color(0xFFFF7B54),
                            size = size,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                            style = Stroke(
                                width = 10f,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f))
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp) // ارتفاع کلی کارت
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        // باکس عکس
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .fillMaxHeight()
//                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.shiraz),
                                contentDescription = "Background Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
//                                    .clip(RoundedCornerShape(16.dp))
                            )

                            // افکت گرادیانت روی عکس
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(Color.Transparent, Color.White),
                                            startX = 300f
                                        )
                                    )
                            )
                        }

                        // باکس متن که روی عکس قرار می‌گیرد (و فراتر می‌رود)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(start = 120.dp) // اینجا شروع متن از نزدیک عکس
//                                .background(Color.White, shape = RoundedCornerShape(16.dp))
                                .align(Alignment.CenterEnd) // باکس رو در سمت راست و وسط چین می‌کنه
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(end = 16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "آرامش در زراس(سوییس ایران)",
                                    fontFamily = irgitiFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()

                                )


                                Text(
                                    text = "\uD83D\uDCCD مقصد: روستای زِرّاس، اندیمشک، خوزستان",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 8.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()

                                )

                                Text(
                                    text = "\uD83D\uDCC6 مدت سفر: ۳ روز – ۲ شب\u2028",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 8.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()

                                )

                                Text(
                                    text = "\uD83D\uDCB0 بودجه : ۲.۵ میلیون تومان",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 8.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()

                                )

                                Text(
                                    text = "\uD83C\uDFAF سبک سفر: طبیعت\u200Cگردی، آرامش، کمپینگ سبک",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 8.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()

                                )
                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(25.dp)
                                        .background(color = Color(0xFF939B62) , RoundedCornerShape(20.dp)),
                                    contentAlignment = Alignment.Center // ✅ این خط متن رو دقیقاً وسط باکس می‌ذاره

                                ) {
                                    Text(
                                        text = "مشاهده برنامه",
                                        fontFamily = iranSans,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 10.sp,
                                        color = Color.White,
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun sugestiontripPreview() {
    sugestiontrip()
}
