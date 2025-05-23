package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun MapTabContent(navController: NavController) {
    val daysWithPlaces = remember {
        mutableStateListOf(
            "سه‌شنبه 1403/6/23" to mutableStateListOf(
                PlaceItem("میدان نقش جهان (امام)", "صبح ۹ - ظهر ۱۲"),
                PlaceItem("رستوران شهرزاد", "ظهر ۱ - ظهر ۲:۳۰")
            ),
            "چهارشنبه 1403/6/24" to mutableStateListOf(),
            "پنج‌شنبه 1403/6/25" to mutableStateListOf(
                PlaceItem("میدان نقش جهان (امام)", "ظهر ۱ - ظهر ۲:۳۰")
            ),
            "جمعه 1403/6/26" to mutableStateListOf(),
            "شنبه 1403/6/27" to mutableStateListOf()
        )
    }

    var draggedItem by remember { mutableStateOf<PlaceItem?>(null) }
    var draggedFrom by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val expandedStates = remember { mutableStateListOf(true, false, false, false, false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        daysWithPlaces.forEachIndexed { dayIndex, (date, places) ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            expandedStates[dayIndex] = !expandedStates[dayIndex]
                        }) {
                            Icon(
                                imageVector = if (expandedStates[dayIndex]) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = "toggle"
                            )
                        }
                        Text(
                            text = date,
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Right
                        )
                    }

                    if (expandedStates[dayIndex]) {
                        if (places.isEmpty()) {
                            Text(
                                text = "برنامه‌ای ثبت نشده است",
                                fontSize = 13.sp,
                                color = Color.Gray,
                                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 6.dp)
                            )
                        } else {
                            places.forEachIndexed { placeIndex, place ->
                                PlaceRow(
                                    title = place.name,
                                    timeRange = place.time,
                                    onDrag = {
                                        draggedItem = place
                                        draggedFrom = dayIndex to placeIndex
                                    },
                                    onDrop = {
                                        if (draggedItem != null && draggedFrom != null && draggedItem != place) {
                                            val from = draggedFrom!!.first
                                            val fromIndex = draggedFrom!!.second
                                            val toList = daysWithPlaces[dayIndex].second
                                            val fromList = daysWithPlaces[from].second
                                            fromList.removeAt(fromIndex)
                                            toList.add(placeIndex, draggedItem!!)
                                            draggedItem = null
                                            draggedFrom = null
                                        }
                                    },
                                            navController = navController // ✅ حتماً اضافه کن
                                )
                            }
                        }

                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "دیدن مکان‌های برنامه‌ریزی‌شده بر روی نقشه",
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            textAlign = TextAlign.Right,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.naghshe),
            contentDescription = "نقشه برنامه‌ریزی‌شده",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(horizontal = 27.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    navController.navigate("full_map")
                },
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 27.dp, bottom = 24.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = { },
                modifier = Modifier.size(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF939B62)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "مکان جدید +",
                    color = Color.White,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun PlaceRow(
    title: String,
    timeRange: String,
    onDrag: () -> Unit,
    onDrop: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        Text(
            text = timeRange,
            fontSize = 12.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 12.dp, bottom = 4.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(Color(0xFFF8F8F8), RoundedCornerShape(16.dp))
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { onDrag() },
                        onDragEnd = { onDrop() },
                        onDrag = { _, _ -> }
                    )
                }
                .padding(horizontal = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(54.dp)
                    .width(99.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.map_bg),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = (-8).dp) // ← جابجایی به چپ
                        .clickable {
                            navController.navigate("fullMap")
                        },
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-23).dp)
                )
            }



            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right
                )
                Text(
                    text = "۷.۸ کیلومتر فاصله از شما",
                    fontSize = 7.sp,
                    color = Color.Red,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }


            Icon(
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

data class PlaceItem(val name: String, val time: String)


