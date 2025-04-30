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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont

@Composable
fun ActiveTripSection() {
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                    text = "سفر فعال",
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

                    Row(
                        modifier = Modifier

                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(16.dp))
                            ,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(1.8.dp , 1.8.dp , 0.dp, 1.8.dp)
                                .background(Color.White)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.shiraz),
                                contentDescription = "Background Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                                    .clip(RoundedCornerShape(11,0,0,11)),

                                )

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

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(0.dp , 1.8.dp , 1.8.dp, 1.8.dp)
                                .background(Color.White)



                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp, 0.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "شیراز",
                                    fontFamily = irgitiFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp,
                                    color = Color.Black
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "۲۸ مرداد تا ۳ شهریور",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "۲.۵ میلیون از ۵ میلیون باقیمانده",
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 10.sp,
                                    color = Color.Gray
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
fun ActiveTripSectionPreview() {
    ActiveTripSection()
}
