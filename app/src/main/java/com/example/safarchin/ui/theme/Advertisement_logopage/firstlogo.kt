package com.example.safarchin.ui.theme.Advertisement_logopage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.delay

@Composable
fun logopage(onNavigateToLogin: () -> Unit) {
    // ✅ گرفتن اندازه صفحه برای تنظیم مقیاس لوگو
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val logoHeight = screenHeight * 0.12f // ✅ اندازه لوگو (۱۲٪ از ارتفاع صفحه)
    val logoWidth = logoHeight * 1.5f // ✅ حفظ نسبت عرض به ارتفاع
//    ✅ بعد از ۱۰ ثانیه هدایت به صفحه ورود
    LaunchedEffect(Unit) {
        delay(3000L) // ⏳ ۱۰ ثانیه صبر کن
        onNavigateToLogin() // 🚀 انتقال به صفحه ورود
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBAD0C)), // ✅ رنگ پس‌زمینه
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center, // ✅ وسط‌چین کردن عمودی
            horizontalAlignment = Alignment.CenterHorizontally // ✅ وسط‌چین کردن افقی
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo prodeutsch",
                modifier = Modifier
                    .width(logoWidth)
                    .height(logoHeight)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f)) // ✅ فاصله بین لوگو و متن

            Text(
                text = "سفرچین",
                fontSize = (screenWidth * 0.07f).value.sp, // ✅ تنظیم اندازه فونت بر اساس عرض صفحه
                fontWeight = FontWeight.Bold,
                fontFamily = iranSans,
                color = Color.Black,
                textAlign = TextAlign.Center, // ✅ وسط‌چین کردن متن
                modifier = Modifier.fillMaxWidth() // ✅ اطمینان از وسط‌چین شدن متن
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogoPagePreview() {
    logopage(
        onNavigateToLogin = {} // 🔥 اینجا تو Preview لازم نیست کاری کنیم
    )
}
