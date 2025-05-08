@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.safarchin.ui.theme.FourPageAsli.planing

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans


@Composable
fun OverviewScreen() {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp
    var selectedTabIndex by remember { mutableStateOf(3) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .background(Color(0xFFFBAD0C))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp, top = 70.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    @Composable
                    fun DateBox(label: String, value: String) {
                        Box(
                            modifier = Modifier
                                .size(width = 55.dp, height = 55.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = label, fontFamily = iranSans, fontSize = 10.sp, color = Color.Gray)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .offset(x = 6.dp),
                        verticalArrangement = Arrangement.spacedBy(60.dp)
                    ) {
                        DateBox(label = "اردیبهشت", value = "۰۳")
                        DateBox(label = "اردیبهشت", value = "۰۷")
                    }
                    Box(
                        modifier = Modifier
                            .weight(3.5f)
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.meydan_emam),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(90.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "سفر رویایی به اصفهان",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("اصفهان", fontSize = 11.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.location),
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("3 نفر", fontSize = 11.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.companions),
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text("1,200,000", fontSize = 11.sp, color = Color.Gray)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.money),
                                        contentDescription = null,
                                        tint = Color.Gray,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.edit2),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 14.dp, bottom = 16.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-20).dp)
                    .zIndex(-1f) // پایین‌تر از باکس نارنجی
                    .height(65.dp)
                    .background(Color.White)
            ) {
                val tabs = listOf("نقشه", "بودجه", "برنامه ریزی", "نمای کلی")
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    edgePadding = 0.dp,
                    containerColor = Color.Transparent,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .tabIndicatorOffset(tabPositions[selectedTabIndex])
                                .padding(horizontal = 12.dp)
                                .height(3.dp),
                            color = Color(0xFFFBAD0C)
                        )
                    },
                    divider = {},
                    modifier = Modifier
                        .align(Alignment.BottomEnd) // تغییر از BottomCenter به BottomEnd برای راست‌چین کردن کل تب بار
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            modifier = Modifier
                                .padding(horizontal = 2.dp),
                            text = {
                                Text(
                                    text = title,
                                    fontFamily = iranSans,
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selectedTabIndex == index) Color.Black else Color.Gray,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier
                                        .wrapContentWidth(Alignment.End)
                                )
                            }
                        )
                    }
                }
            }


        //    Spacer(modifier = Modifier.height(2.dp))

            if (selectedTabIndex == 3) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                             .offset(y=(-10).dp)
                            .verticalScroll(rememberScrollState())
                            .padding(top = 0.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                       // Spacer(modifier = Modifier.height(2.dp))

                        OverviewInputField(placeholder = "یادداشت‌ها")
                        Spacer(modifier = Modifier.height(8.dp))
                        OverviewInputField(placeholder = "چک‌لیست")
                        Spacer(modifier = Modifier.height(8.dp))
                        OverviewInputField(placeholder = "مکان‌های منتخب")

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier
                                .width(348.dp)
                                .height(89.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Top
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Text(
                                        text = "ذخیره شده‌ها",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        fontFamily = iranSans
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.save),
                                        contentDescription = null,
                                        tint = Color(0xFF939B62),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "هنوز مکان ذخیره شده ای برای این شهر نداری!!!",
                                    fontSize = 10.sp,
                                    color = Color.Gray,
                                    fontFamily = iranSans,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(348.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .padding(12.dp)
                        ) {
                            Column {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "مکان‌های پیشنهادی",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black,
                                        fontFamily = iranSans
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.location2),
                                        contentDescription = null,
                                        tint = Color(0xFF939B62),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                Row(
                                    modifier = Modifier
                                        .horizontalScroll(rememberScrollState())
                                        .fillMaxWidth()
                                ) {
                                    listOf(
                                        "مسجد نصیرالملک" to R.drawable.shiraz,
                                        "مسجد نصیرالملک" to R.drawable.shiraz,
                                        "باغ ارم" to R.drawable.shiraz
                                    ).forEach { (title, imageRes) ->
                                        Card(
                                            modifier = Modifier
                                                .width(114.dp)
                                                .height(140.dp)
                                                .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp)),
                                            shape = RoundedCornerShape(6.dp),
                                            colors = CardDefaults.cardColors(containerColor = Color.White)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(top = 6.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Image(
                                                    painter = painterResource(id = imageRes),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .width(95.dp)
                                                        .height(85.dp)
                                                        .clip(RoundedCornerShape(15.dp)),
                                                    contentScale = ContentScale.Crop
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = title,
                                                    fontSize = 10.sp,
                                                    fontFamily = iranSans,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.align(Alignment.End).padding(end = 12.dp)
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Button(
                                                    onClick = { },
                                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF939B62)) ,
                                                    modifier = Modifier
                                                        .width(84.dp)
                                                        .height(18.dp),
                                                    shape = RoundedCornerShape(6.dp),
//
                                                    contentPadding = PaddingValues(horizontal = 8.dp)
                                                ) {
                                                    Text(
                                                        text = "اضافه کردن به برنامه",
                                                        fontSize = 8.sp,
                                                        textAlign = TextAlign.Center,
                                                        color = Color.White,
                                                        maxLines = 1
                                                    )
                                                }
//
                                            }
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(100.dp))
                    }

                    FloatingActionButton(
                        onClick = { /* Navigate to new page */ },
                        containerColor = Color(0xFFFFD56B),
                        shape = CircleShape,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(24.dp)
                    ) {
                        Text("+ مکان جدید ", color = Color.White, fontSize = 8.sp)
                    }
                }
            }
            }
    }
}
@Composable
fun DestinationCard(imageRes: Int, title: String) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF939B62)),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .width(87.dp)
                    .height(28.dp)
            ) {
                Text("افزودن به برنامه", fontSize = 10.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ColumnBox(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text = count, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 10.sp, color = Color.White)
    }
}

@Composable
fun InfoIconWithText(iconId: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun OverviewInputField(placeholder: String, height: Dp = 50.dp) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .width(348.dp)
            .height(height)
            .clip(RoundedCornerShape(12.dp)),
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                textAlign = TextAlign.Right,
                fontFamily = iranSans,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.popdown),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
            )
        },
        colors = textFieldColors(
            containerColor = Color.White,
            cursorColor = Color.Black,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
fun SectionWithIcon(title: String, icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
fun PreviewOverviewScreen() {
    OverviewScreen()
}