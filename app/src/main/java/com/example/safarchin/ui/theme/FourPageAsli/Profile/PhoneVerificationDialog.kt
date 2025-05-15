package com.example.safarchin.ui.theme.FourPageAsli.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay

@Composable
fun PhoneVerificationDialog(
    phone: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var phoneNumber by remember { mutableStateOf(phone) }
    var code by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(0) } // ⬅ در ابتدا صفره

    // تایمر شمارنده
    LaunchedEffect(timer) {
        if (timer > 0) {
            delay(1000)
            timer--
        }
    }

        Box(
            modifier = Modifier
                .width(screenWidth * 0.8f)
                .wrapContentHeight()
                .background(Color(0xFFF6F4F4), RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "برای تغییر شماره باید شماره جدید احراز هویت شود",
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    textAlign = TextAlign.Right
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(screenWidth * 0.6f) // 👈 طول دقیق فیلد
                            .height(screenHeight * 0.045f)
                            .background(Color.White, RoundedCornerShape(screenWidth * 0.03f))
//                            .clickable { expanded = true }
                            .border(1.dp, Color.LightGray, RoundedCornerShape(screenWidth * 0.03f)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it }, // ⬅ حالا قابل تایپ
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            textStyle = TextStyle(
                                fontFamily = iranSans,
                                fontSize = (screenWidth.value * 0.026).sp,
                                color = Color(0xFFFF7F54),
                                textAlign = TextAlign.Start
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(screenWidth * 0.01f))

                    Text(
                        text = ":شماره",
                        fontFamily = iranSans,
                        fontSize = 13.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(screenWidth * 0.6f) // 👈 طول دقیق فیلد
                            .height(screenHeight * 0.045f)
                            .background(Color.White, RoundedCornerShape(screenWidth * 0.03f))
//                            .clickable { expanded = true }
                            .border(1.dp, Color.LightGray, RoundedCornerShape(screenWidth * 0.03f)),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        BasicTextField(
                            value = code,
                            onValueChange = { code = it }, // ⬅ قابل تایپ
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp),
                            textStyle = TextStyle(
                                fontFamily = iranSans,
                                fontSize = (screenWidth.value * 0.026).sp,
                                color = Color(0xFFFF7F54),
                                textAlign = TextAlign.Right
                            )
                        )

                    }
                    Spacer(modifier = Modifier.width(screenWidth * 0.01f))

                    Text(
                        text = ":کد",
                        fontFamily = iranSans,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(screenWidth * 0.04f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (timer > 0) "۰:${if (timer < 10) "0$timer" else "$timer"}" else "",
                        fontFamily = iranSans,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                if (timer > 0) Color(0xFFFFD6C7) else Color(0xFFFF7F54),
                                RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable(enabled = timer == 0) {
                                timer = 42 // ⬅ شروع تایمر
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (timer == 0) "ارسال کد" else "ارسال مجدد کد",
                            fontFamily = iranSans,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.07f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.07f), // ⬅ جایگزین 60.dp
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // دکمه "خروج"
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(screenWidth * 0.04f))
                                .background(Color.White)
                                .border(1.5.dp, Color(0xFFFF3A3A), RoundedCornerShape(screenWidth * 0.04f))
                                .clickable {
                                    onDismiss() // ⬅ فقط بستن دیالوگ
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "لغو",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.04).sp,
                                color = Color.Black
                            )
                        }


                        Spacer(modifier = Modifier.width(screenWidth * 0.06f)) // فاصله ریسپانسیو

                        // دکمه "ثبت"
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(screenWidth * 0.04f))
                                .background(Color(0xFFFF7F54))

                                .clickable {
                                    onConfirm(phoneNumber) // ✅ شماره رو پاس بده
                                },


                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ثبت",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.04).sp,
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PopupSd() {
    PhoneVerificationDialog(
        phone = "09123456789",
        onDismiss = {},
        onConfirm = {}
    )
}
