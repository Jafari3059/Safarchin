package com.example.safarchin.ui.theme.FourPageAsli.Plannig.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.Trip
//import com.example.safarchin.model.Trip
import com.example.safarchin.ui.theme.iranSans

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TripCardsSection(
    trips: List<Trip>,
    selectedCardId: Int?,
    onCardMoreClick: (Int) -> Unit,
    screenWidth: Dp,
    screenHeight: Dp
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidth * 0.077f),
            horizontalArrangement = Arrangement.spacedBy(screenWidth * 0.042f),
            verticalArrangement = Arrangement.spacedBy(screenHeight * 0.02f),
            maxItemsInEachRow = 2
        ) {
            trips.forEach { trip ->
                TripCard(
                    trip = trip,
                    isDeleteBoxVisible = selectedCardId == trip.id,
                    onMoreIconClick = { onCardMoreClick(trip.id) },
                    screenWidth = screenWidth
                )
            }
        }
    }
}
// فرم سفر
@Composable
fun TripCard(
    trip: Trip,
    isDeleteBoxVisible: Boolean,
    onMoreIconClick: () -> Unit,
    screenWidth: Dp
) {
    val cardWidth = screenWidth * 0.40f
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp.dp
    val screenHeight = config.screenHeightDp.dp

    val boxOffsetX = screenWidth * -0.015f  // حدود -6dp
    val boxOffsetY = screenHeight * -0.009f // حدود -16dp
    val boxPaddingH = screenWidth * 0.025f  // حدود 10dp
    val boxPaddingV = screenHeight * 0.008f // حدود 6dp
    val boxCornerRadius = screenWidth * 0.03f // حدود 12dp
    val fontSize = screenWidth.value.times(0.03).sp // حدود 12sp

    Card(
        modifier = Modifier
            .width(cardWidth)
            .aspectRatio(0.95f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = trip.imageRes),
                contentDescription = "عکس سفر",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.3f))
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = trip.title,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = trip.date,
                    fontFamily = iranSans,
                    fontSize = 11.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = trip.description,
                    fontFamily = iranSans,
                    fontSize = 11.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(22.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .width(cardWidth * 0.6f)
                            .height(30.dp),
                        contentPadding = PaddingValues(horizontal = 6.dp)
                    ) {
                        Text(
                            text = "شروع برنامه",
                            fontFamily = iranSans,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }

                    Box {
                        IconButton(onClick = { onMoreIconClick() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.etc1),
                                contentDescription = "آیکون حذف",
                                modifier = Modifier.size(25.dp),
                                tint = Color.Unspecified
                            )
                        }
                        if (isDeleteBoxVisible) {
                            Box(
                                modifier = Modifier
                                    .offset(y = boxOffsetY, x = boxOffsetX)
                                    .background(
                                        color = Color(0xFFFFB26B),
                                        shape = RoundedCornerShape(boxCornerRadius)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFFF7B54),
                                        shape = RoundedCornerShape(boxCornerRadius)
                                    )
                                    .clickable { }
                                    .padding(horizontal = boxPaddingH, vertical = boxPaddingV)
                            ) {
                                Text(
                                    text = "حذف",
                                    color = Color.White,
                                    fontFamily = iranSans,
                                    fontSize = fontSize
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}