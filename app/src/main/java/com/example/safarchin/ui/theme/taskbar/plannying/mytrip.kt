package com.example.safarchin.ui.theme.taskbar.plannying

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.components.SearchBar
import com.example.safarchin.ui.theme.components.TabBar
import com.example.safarchin.ui.theme.iranSans

@Composable
fun MyTripScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Add Trip */ },
                containerColor = Color(0xFFFF9800),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 30.sp,
                    color = Color.White,
                    fontFamily = iranSans
                )
            }
        },
        bottomBar = {
            BottomNavigationBar()
        },
        containerColor = Color(0xFFF7F7F7)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
        ) {
            // Header Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // ارتفاع هدر
            ) {
                // آیکون
                Image(
                    painter = painterResource(id = R.drawable.vector1),
                    contentDescription = "تصویر سفر",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.TopStart) // جای اولیه
                        .offset(x = 30.dp, y = 40.dp) // راحت جابه‌جا کن
                )

                // متن سفرهای من
                Text(
                    text = "سفرهای من",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = iranSans,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.TopEnd) // جای اولیه
                        .offset(x = (-40).dp, y = 62.dp) // راحت جابه‌جا کن
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                // Search Bar
                var searchText by remember { mutableStateOf("") }

                SearchBar(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholderText = "کجا میخوای بری؟ ",
                    cornerRadius = 30.dp, // گوشه گرد تر
                    iconOnLeft = true     // آیکون بیاد چپ
                )

                Spacer(modifier = Modifier.height(10.dp))
                var selectedTab by remember { mutableStateOf("همه") }

                val tabs = listOf("در حال برنامه‌ریزی", "به اتمام رسیده‌ها", "همه") // این ترتیب مهمه!

                TabBar(
                    tabs = tabs,
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Trip Cards
                TripCardsSection()
            }
        }
    }
}


@Composable
fun TripCardsSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            TripCard(
                title = "راین دهس",
                date = "تا ۳ روز دیگه",
                description = "این برنامه نزدیکه!"
            )
            TripCard(
                title = "سفر",
                date = "تا ۱۰ روز دیگه",
                description = "منتظر بمون!"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            TripCard(
                title = "سفر",
                date = "تا ۱۵ روز دیگه",
                description = "برنامه‌ریزی شده"
            )
        }
    }
}

@Composable
fun TripCard(
    title: String,
    date: String,
    description: String
) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(180.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.khajobridge),
                contentDescription = "عکس سفر",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = title,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = date,
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontFamily = iranSans,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "مشاهده برنامه",
                        fontFamily = iranSans,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "خانه"
                )
            }
        )
        NavigationBarItem(
            selected = true, // این صفحه فعاله پس این انتخاب شده
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.planpage),
                    contentDescription = "تقویم"
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.subscription),
                    contentDescription = "ویژه"
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "پروفایل"
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyTripScreenPreview() {
    MyTripScreen()
}
