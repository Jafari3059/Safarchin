package com.example.safarchin.ui.theme.newtrip

import com.example.safarchin.ui.theme.components.SearchBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans


@Composable
fun CreateTripScreen() {
    var travelersCount by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var totalBudget by remember { mutableStateOf("") }
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔙 دکمه برگشت و آیکون نقشه
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 4.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* رفتن به عقب */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.backbtn),
                    contentDescription = "بازگشت"
                )
            }

            IconButton(onClick = { /* فعلاً کاری نکنه */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.map),
                    contentDescription = "نقشه"
                )
            }
        }

        //Spacer(modifier = Modifier.height(5.dp))
        // 🔍 نوار جستجو سفارشی
        SearchBar(
            value = searchText,
            onValueChange = { searchText = it }
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "دسته بندی‌ها",
            fontFamily = iranSans,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            listOf(
                R.drawable.gift,
                R.drawable.market,
                R.drawable.coffeeshop,
                R.drawable.place,
                R.drawable.resturant
            ).forEach { icon ->
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(horizontal = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "مکان منتخب",
            fontFamily = iranSans,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Spacer(modifier = Modifier.height(16.dp))

        EsfahanCard(iconRes = R.drawable.location3)

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.width(105.dp)
            ) {
                LabelWithIcon("تعداد همسفران", R.drawable.companions)
                OutlinedTextField(
                    value = travelersCount,
                    onValueChange = { travelersCount = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontFamily = iranSans,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Right
                    )
                )
            }

            Spacer(modifier = Modifier.width(22.dp))

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.width(155.dp)
            ) {
                LabelWithIcon("تاریخ شروع و پایان سفر", R.drawable.calendar)
                OutlinedTextField(
                    value = "1403/01/20  /  1403/01/25",
                    onValueChange = { /* مدیریت تاریخ */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(53.dp),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = LocalTextStyle.current.copy(
                        fontFamily = iranSans,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(1.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-12).dp), // 👈 این کار رو انجام بده
            horizontalAlignment = Alignment.CenterHorizontally
        )  {
            Row(
                modifier = Modifier
                    .width(280.dp)
                    .padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.End
            ) {
                LabelWithIcon("مقدار کل بودجه سفر", R.drawable.money)
            }

            OutlinedTextField(
                value = totalBudget,
                onValueChange = { totalBudget = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(280.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(12.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = iranSans,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Right
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /* شروع برنامه‌ریزی */ },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF909090)),
            modifier = Modifier
                .offset(y = 14.dp)
                .width(136.dp)
                .height(53.dp)
        ) {
            Text(
                text = "شروع برنامه‌ریزی سفر",
                color = Color.White,
                fontSize = 10.sp,
                fontFamily = iranSans
            )
        }
    }
}

@Composable
fun LabelWithIcon(text: String, iconRes: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.End), // فاصله بین آیکون و متن
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp) // فاصله از فیلد پایین
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontFamily = iranSans,
            color = Color.Black
        )
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp) // می‌تونی بزاری 22 یا 24 اگه خیلی کوچیک بود
        )
    }
}
@Composable
fun EsfahanCard(
    iconRes: Int // آیکون مخصوص مکان که خودت داری
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA726)),
            modifier = Modifier
                .width(321.dp)
                .heightIn(min = 242.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Row(modifier = Modifier.padding(12.dp)) {
                Box(
                    modifier = Modifier
                        .size(width = 110.dp, height = 130.dp)
                        .clip(RoundedCornerShape(15.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imagesquareemam),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds, // به جای Crop یا Fit
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(4.dp)) // فاصله از بالا
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 4.dp, bottom = 4.dp) // چپ‌تر (در راست‌چین یعنی end) و پایین‌تر
                    ) {
                        Text(
                            text = "اصفهان",
                            fontSize = 16.sp,
                            fontFamily = iranSans,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp)) // فاصله با متن توضیحات

                    val fullText = "اصفهان یکی از مهم‌ترین شهرهای تاریخی ایران است که با جاذبه‌های فراوان، معماری باشکوه و فرهنگی غنی، هر ساله میزبان گردشگران بسیاری از داخل و خارج کشور می‌باشد. اصفهان در مرکز ایران واقع شده و با میدان نقش جهان، پل‌های تاریخی، و بناهایی مثل مسجد امام شناخته می‌شود."

                    Text(
                        text = if (expanded) fullText else fullText.take(140) + "…",
                        fontSize = 11.sp,
                        fontFamily = iranSans,
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (expanded) "...کمتر" else "...بیشتر",
                        fontSize = 11.sp,
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Medium,
                        color =Color(0xFF000000),
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                            .align(Alignment.Start)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCreateTripScreen() {
    CreateTripScreen()
}