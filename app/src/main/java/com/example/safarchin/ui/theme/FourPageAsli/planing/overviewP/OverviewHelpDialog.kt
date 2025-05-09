package com.example.safarchin.ui.theme.FourPageAsli.planing.overviewP

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

@Composable
fun GuidePopup(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBCBCBC).copy(alpha = 0.63f))
            .clickable(enabled = false) {},
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "راهنما",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = iranSans,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "برای اینکه مکان های مورد نظر خود را به برنامه ریزی خود اضافه کنید ابتدا وارد صفحه توضیحات بیشتر هر مکان شوید سپس مانند تصویر در قسمت بالا سمت راست روی ایکون ذخیره کلیک کنید.به این ترتیب آن مکان در لیست ذخیره شده های شما قرار میگیرد و میتوانید در آنجا نسبت به افزودن آن به مکان های منتخب اقدام کنید",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = iranSans,
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )
                   // Spacer(modifier = Modifier.height(2.dp))
                    Image(
                        painter = painterResource(id = R.drawable.guidesave),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 186.dp, height = 73.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally) // فاصله بین دکمه‌ها
                ) {
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .width(110.dp)
                            .height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "دیگه نمایش نده",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = iranSans,
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .width(110.dp)
                            .height(36.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F54)),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "متوجه شدم",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = iranSans,
                            color = Color.White
                        )
                    }



                }
            }
        }
    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewGuidePopup() {
    GuidePopup(onDismiss = {})
}
