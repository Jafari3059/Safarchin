package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HeaderSection

@Composable
fun Support1() {
    val helpItems = listOf(
        "سوالات متداول" to "",
        "راهنمای استفاده (با تصویر و ویدیو)" to "",
        "جستجوی مشکلات رایج" to "",
        "تماس با پشتیبانی" to "",
        "راهنمای مکان‌یابی و مجوزها" to "",
        "بروزرسانی‌ها و تغییرات نسخه جدید" to "",
        "بخش ارسال بازخورد یا پیشنهادات" to "پیشنهاد یا انتقاد خود را از طریق فرم پایین ثبت کنید."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF6F4F4))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.khajo),
                contentDescription = "پل خاجو",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFF5F5F5))
                        )
                    )
            )

            HeaderSection(
                onNotificationClick = { },
                onHelpClick = { }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        helpItems.forEach { (title, content) ->
            ExpandableHelpItem(title = title, content = content)
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun ExpandableHelpItem(title: String, content: String) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrowRotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.down),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(rotation)
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
                    modifier = Modifier.weight(1f)
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                when (title) {
                    "سوالات متداول" -> {
                        Column {
                            FAQItem("چطور می‌تونم یک سفر جدید بسازم؟", "از بخش «سفرهای من»، روی دکمه + کلیک کنید. عنوان و تاریخ سفر را وارد کرده و دکمه ذخیره را بزنید.")
                            FAQItem("چطور مکان دیدنی اضافه کنم؟", "بعد از ایجاد سفر، از طریق نقشه یا جستجو، مکان مورد نظر را پیدا کرده و با زدن دکمه افزودن آن را اضافه کنید.")
                            FAQItem("آیا می‌تونم برنامه سفر را با دیگران به اشتراک بگذارم؟", "بله. گزینه «اشتراک‌گذاری» را در صفحه سفر انتخاب کنید و لینک را برای دوستانتان بفرستید.")
                        }
                    }
                    "راهنمای استفاده (با تصویر و ویدیو)" -> {
                        Column {
                            FAQItem("ساخت سفر جدید", "وارد بخش سفرهای من شوید و روی دکمه + کلیک کنید. عنوان، مقصد و تاریخ را وارد کرده و ذخیره کنید.")
                            FAQItem("افزودن مکان‌های دیدنی", "در صفحه سفر، از نقشه یا جستجو برای پیدا کردن مکان استفاده کرده و روی افزودن کلیک کنید.")
                            FAQItem("برنامه‌ریزی زمانی", "برای هر مکان ساعت رسیدن را مشخص کنید تا برنامه منظمی داشته باشید.")
                            Image(
                                painter = painterResource(id = R.drawable.video),
                                contentDescription = "آموزش تصویری",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .padding(top = 16.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }
                    }
                    "جستجوی مشکلات رایج" -> {
                        Column {
                            FAQItem("برنامه باز نمی‌شود یا هنگ می‌کند", "اتصال اینترنت، به‌روزرسانی برنامه و پاک‌سازی کش را بررسی کنید.")
                            FAQItem("نقشه‌ها لود نمی‌شوند", "دسترسی مکان و اتصال اینترنت را بررسی کنید. اگر مشکل ادامه داشت، تنظیمات دستگاه را چک کنید.")
                        }
                    }
                    "تماس با پشتیبانی" -> {
                        Column {
                            FAQItem("تماس تلفنی", "با شماره ۰۹۱۲۳۴۵۶۷۸۹ در ساعات اداری تماس بگیرید.")
                            FAQItem("ارسال ایمیل", "پیام خود را به آدرس support@safarapp.com بفرستید.")
                            FAQItem("فرم تماس داخل برنامه", "از فرم پایین همین صفحه استفاده کنید تا با ما در ارتباط باشید.")
                        }
                    }
                    "راهنمای مکان‌یابی و مجوزها" -> {
                        Column {
                            FAQItem("برای اینکه تجربه‌ی بهتری در سفر داشته باشید، اپلیکیشن نیاز به دسترسی به موقعیت مکانی شما دارد:", "\n• مکان فعلی شما را روی نقشه نمایش دهد\n• مسیرهای پیشنهادی را نمایش دهد")
                            FAQItem("در دستگاه‌های اندرویدی:", "\n• وارد تنظیمات شوید\n• به بخش مجوزها (Permissions) بروید\n• گزینه Location را فعال کنید")
                            FAQItem("در دستگاه‌های iOS:", "\n• وارد Settings شوید\n• به بخش Privacy & Security > Location بروید\n• While Using the App را فعال کنید\n• Precise Location را هم روشن کنید (برای دقت بیشتر)")
                        }
                    }
                    "بروزرسانی‌ها و تغییرات نسخه جدید" -> {
                        Column {
                            FAQItem("ویژگی‌های جدید:", "\n• قابلیت اشتراک‌گذاری برنامه سفر\n• افزودن مسیرهای پیشنهادی\n• دسته‌بندی جدید برای مکان‌ها")
                            FAQItem("بهبودهای عملکرد:", "\n• افزایش سرعت بارگذاری نقشه‌ها و لیست مکان‌ها\n• بهینه‌سازی نمایش مکان‌ها برای دقت بیشتر\n• بهبود بخش پشتیبانی و ارسال سریع‌تر پیام")
                            FAQItem("باک‌فیکس‌ها:", "\n• رفع مشکل نمایش ندادن ایمیل‌ها\n• اصلاح نمایش ناهماهنگ در برخی صفحه‌ها")

                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 53.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F54))
                            ) {
                                Text("بروزرسانی")
                            }
                        }
                    }
                    else -> {
                        Text(
                            text = content,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Right,
                            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = question,
            color = Color(0xFFFF7B00),
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, start = 24.dp)
        )

        Text(
            text = answer,
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 16.dp, top = 4.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun Support1Preview() {
    Support1()
}
