package com.example.safarchin.ui.theme.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.delay

@Composable
fun codeLogin() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var code by remember { mutableStateOf(TextFieldValue("")) }
    var timer by remember { mutableStateOf(60) }
    var isTimerRunning by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = when {
        isFocused -> Color(0xFFFFA500)
        else -> Color(0xFF5F5F5F)
    }

    // ✅ تایمر شمارش معکوس
    LaunchedEffect(key1 = isTimerRunning) {
        if (isTimerRunning) {
            while (timer > 0) {
                delay(1000L)
                timer--
            }
            isTimerRunning = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x802A2A2A)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 60.dp)
                    .width(screenWidth * 0.88f)
                    .height(screenHeight * 0.28f)
                    .background(Color(0x60FFFFFF), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "کد تأیید",
                            fontSize = (screenWidth * 0.04f).value.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = iranSans,
                            color = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    OutlinedTextField(
                        value = code,
                        onValueChange = { code = it },
                        placeholder = {
                            Text(
                                text = "کد تایید را وارد کنید",
                                textAlign = TextAlign.Right,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 3.dp, 0.dp, 0.dp),
                                fontSize = (screenWidth * 0.04f).value.sp,
                                color = Color(0xFFCFD1D4),
                                fontFamily = iranSans,
                                fontWeight = FontWeight.ExtraLight,
                            )
                        },
                        shape = RoundedCornerShape(screenWidth * 0.03f),
                        modifier = Modifier
                            .fillMaxWidth(0.83f)
                            .height(screenHeight * 0.07f)
                            .background(Color(0xFF5F5F5F), RoundedCornerShape(screenWidth * 0.03f))
                            .border(2.dp, borderColor, RoundedCornerShape(screenWidth * 0.03f))
                            .onFocusChanged { isFocused = it.isFocused }
                    )

                    // ✅ بخش تایمر یا ارسال مجدد کد
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.83f)
                            .padding(0.dp, 0.dp, 10.dp, 0.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        if (isTimerRunning) {
                            Text(
                                text = "ارسال مجدد کد: ${timer} ثانیه دیگر",
                                fontSize = (screenWidth * 0.03f).value.sp,
                                fontWeight = FontWeight.ExtraLight,
                                fontFamily = iranSans,
                                color = Color.White,
                            )
                        } else {
                            Text(
                                text = "ارسال مجدد کد",
                                fontSize = (screenWidth * 0.03f).value.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = iranSans,
                                color = Color(0xFFFFA500),
                                modifier = Modifier.clickable {
                                    // 🔥 وقتی کلیک شد تایمر دوباره شروع بشه
                                    timer = 60
                                    isTimerRunning = true
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                    Button(
                        onClick = {
                            // تایید کد کاربر
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(screenHeight * 0.065f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "تأیید کد",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginbPreview() {
//    val navController = rememberNavController()
    codeLogin()
}
