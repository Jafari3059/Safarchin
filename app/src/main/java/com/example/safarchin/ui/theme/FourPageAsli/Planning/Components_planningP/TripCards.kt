package com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripEntity
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.zIndex
import com.example.safarchin.ui.theme.irgitiFont

@Composable
fun TripCard(
    trip: TripEntity,
    isDeleteBoxVisible: Boolean,
    onMoreIconClick: () -> Unit,
    onDeleteClick: (Int) -> Unit,
    onStatusChangeClick: (Int, String) -> Unit,
    onEditClick: (TripEntity) -> Unit,
    onCardCloseMenu: () -> Unit,
    screenWidth: Dp,
    navController: NavController
)

 {
    val cardWidth = 163.dp
    val cardHeight = 161.dp
    val cornerRadius = 16.dp

     Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight + 30.dp) // فضای اضافی برای منو
            .zIndex(if (isDeleteBoxVisible) 2f else 1f)
    ) {
        //  بدنه کارت
        Box(
            modifier = Modifier
                .width(cardWidth)
                .height(cardHeight)
                .shadow(4.dp, shape = RoundedCornerShape(cornerRadius))
                .clip(RoundedCornerShape(cornerRadius))
        ) {
            Image(
                painter = painterResource(id = trip.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    trip.city,
                    fontFamily = irgitiFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    "از ${trip.startDate} تا ${trip.endDate}",
                    fontFamily = iranSans,
                    fontSize = 10.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    "${trip.budget / 1_000_000} میلیون از ${
                        if (trip.budgetForAll) trip.budget / 1_000_000 else trip.budget / 500_000
                    } میلیون باقی‌مانده",
                    fontFamily = iranSans,
                    fontSize = 9.5.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    "۲ روز مانده به شروع سفر",
                    fontFamily = iranSans,
                    fontSize = 9.5.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onMoreIconClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.etc1),
                            contentDescription = "آیکون سه‌نقطه",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Button(
                        onClick = { navController.navigate("overview")},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .width(cardWidth * 0.7f)
                            .height(30.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "مشاهده برنامه",
                                fontFamily = iranSans,
                                fontSize = 10.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        //  منوی سه نقطه (TripCardMenuBox)
         if (isDeleteBoxVisible) {

             // ✅ این بخش باید قبل از TripCardMenuBox باشه
             val handleFinishTrip = {
                 onStatusChangeClick(trip.id, "به اتمام رسیده ها")
                 onCardCloseMenu()
             }

             TripCardMenuBox(
                 modifier = Modifier
                     .absoluteOffset(x = 30.dp, y = 130.dp)
                     .zIndex(2f),
                 onDeleteClick = { onDeleteClick(trip.id) },
                 onStatusChangeClick = handleFinishTrip,
                 onEditClick = { onEditClick(trip) }
             )
         }
     }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TripCardsSection(
    trips: List<TripEntity>,
    selectedCardId: Int?,
    onCardMoreClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onStatusChangeClick: (Int, String) -> Unit,
    onEditClick: (TripEntity) -> Unit,
    screenWidth: Dp,
    navController: NavController
) {

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column( //  تغییر از Box به Column
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 50.dp)
        ) {
            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val isOdd = trips.size % 2 != 0
                trips.forEachIndexed { index, trip ->
                    if (isOdd && index == trips.lastIndex) {
                        TripCard(
                            trip = trip,
                            isDeleteBoxVisible = selectedCardId == trip.id,
                            onMoreIconClick = { onCardMoreClick(trip.id) },
                            onDeleteClick = onDeleteClick,
                            onStatusChangeClick = onStatusChangeClick,
                            onEditClick = onEditClick,
                            onCardCloseMenu = { onCardMoreClick(-1) }, // 👈 این خط جدید
                            screenWidth = screenWidth,
                            navController = navController
                        )

                        Spacer(modifier = Modifier.width(50.dp))
                    } else {
                        TripCard(
                            trip = trip,
                            isDeleteBoxVisible = selectedCardId == trip.id,
                            onMoreIconClick = { onCardMoreClick(trip.id) },
                            onDeleteClick = onDeleteClick,
                            onStatusChangeClick = onStatusChangeClick,
                            onEditClick = onEditClick,
                            onCardCloseMenu = { onCardMoreClick(-1) }, //  این خط جدید
                            screenWidth = screenWidth,
                            navController = navController
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun TripCardMenuBox(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    onStatusChangeClick: () -> Unit,
  onEditClick: () -> Unit

    ) {
    val corner = 8.dp

    Box(
        modifier = modifier
            .width(60.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(corner)) // جلوگیری از بیرون زدن گوشه‌ها
            .background(Color(0xFFFFB26B), shape = RoundedCornerShape(corner))
            .border(1.dp, Color(0xFFFF7B54), shape = RoundedCornerShape(corner)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            listOf("حذف", "اتمام سفر", "ویرایش").forEachIndexed { index, text ->
                val bgColor = when (index) {
                    1 -> Color(0xFFFF7B54) // خانه وسطی
                    else -> Color(0xFFFFB26B)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // همه خونه‌ها با ارتفاع مساوی
                        .background(bgColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontFamily = iranSans,
                        modifier = Modifier.clickable {
                            when (index) {
                                0 -> onDeleteClick()
                                1 -> onStatusChangeClick()
                                2 -> onEditClick()
                            }
                        }
                    )
                }
            }
        }
    }
}