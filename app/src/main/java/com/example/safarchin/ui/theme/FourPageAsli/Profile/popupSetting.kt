package com.example.safarchin.ui.theme.FourPageAsli.Profile

import android.widget.Switch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle


@Composable
fun popupSettting( ){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

        Box(
            modifier = Modifier
                .width(screenWidth * 0.9f)
                .wrapContentHeight() // 👈 ارتفاع به اندازه محتوا
                .background(color = Color(0xFFF6F4F4), RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color(0xFFF6F4F4))
                ) {
                    // عکس و آپلود
                    Box(
                        modifier = Modifier
                            .wrapContentHeight() // ✅ تغییر مهم
                            .weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                            Image(
                                painter = painterResource(id = R.drawable.profile_image),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(screenWidth * 0.22f)
                                    .clip(CircleShape)
                            )

                            Text(
                                text = "آپلود عکس",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.022).sp,
                                color = Color.Blue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = screenHeight * 0.005f),
                                style = androidx.compose.ui.text.TextStyle(
                                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                                )
                            )
                        }
                    }

                    // فیلدها
                    Box(
                        modifier = Modifier
                            .wrapContentHeight() // ✅ تغییر مهم
                            .weight(2f)
                    ) {
                        Column {
                            val labels = listOf(
                                ":نام کاربری",
                                ":نام",
                                ":نام خانوادگی",
                                ":سن",
                                ":شهر محل زندگی",
                                ":شماره"
                            )

                            labels.forEach { label ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(screenHeight * 0.042f)
                                        .padding(vertical = screenHeight * 0.003f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(5f)
                                            .fillMaxHeight()
                                            .background(Color.White, RoundedCornerShape(6.dp))
                                    )
                                    Text(
                                        text = label,
                                        fontFamily = iranSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = (screenWidth.value * 0.025).sp,
                                        color = Color.Black,
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier
                                            .weight(2f)
                                            .fillMaxHeight()
                                            .padding(top = screenHeight * 0.01f)
                                    )
                                }
                            }
                        }
                    }
                }
                var isCheckedS by remember { mutableStateOf(false) }
                var isCheckedN by remember { mutableStateOf(false) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.05f)
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    // Switch سفارشی‌شده
                    Switch(
                        checked = isCheckedS,
                        onCheckedChange = { isCheckedS = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFFFFC98D), // نارنجی کم‌رنگ
                            uncheckedThumbColor = Color.Black,
                            uncheckedTrackColor = Color.Transparent
                        ),
                        modifier = Modifier.scale(0.85f)
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("ایا مایل هستید سفرهای برنامه ریزی شده شما در صفحه پروفایلتان قرار بگیرد؟ ")

                            withStyle(
                                style = SpanStyle(
                                    fontSize = (screenWidth.value * 0.020).sp, // کوچکتر از متن اصلی
                                    color = Color.Gray // می‌تونی رنگ رو هم عوض کنی
                                )
                            ) {
                                append("(با اینکار کاربران دیگر میتوانند محتوای سفر شما را مشاهده کنند)")
                            }
                        },
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth.value * 0.025).sp, // سایز متن اصلی
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.05f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = isCheckedN,
                        onCheckedChange = { isCheckedN = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFFFFC98D),
                            uncheckedThumbColor = Color.Black,
                            uncheckedTrackColor = Color.Transparent // برای حذف ظاهر خاموش
                        ),
                        modifier = Modifier
                            .scale(0.85f)
                    )
                    Spacer(modifier = Modifier.weight(1f)) // 👈 فاصله‌دهنده بین متن و سوییچ

                    Text(
                        text = "اعلان ها",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth.value * 0.025).sp,
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                    )

                }
                Spacer(modifier = Modifier.height(16.dp)) // 👈 فاصله افقی بین دو دکمه


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
                                .clip(RoundedCornerShape(screenWidth * 0.04f)) // گردی نسبی
                                .background(Color.White)
                                .border(1.5.dp, Color(0xFFFF3A3A), RoundedCornerShape(screenWidth * 0.04f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "خروج",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.04).sp, // ⬅ اندازه فونت ریسپانسیو
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
                                .background(Color(0xFFFF7F54)),
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
fun PopupS() {
    popupSettting()
}