package com.example.safarchin.ui.theme.FourPageAsli.Plannig.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.safarchin.R

//فرم سفر
@Composable
fun CreateTripDialog(onDismiss: () -> Unit, navController: NavController)
{
    var tripName by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("انتخاب شهر") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var travelers by remember { mutableStateOf("") }
    var selectedBudget by remember { mutableStateOf("انتخاب بودجه") }
    var budgetForAll by remember { mutableStateOf(true) }

    val cities = listOf("تهران", "مشهد", "شیراز", "فارس", "مازندران", "خوزستان", "اصفهان", "قم")
    val budgets = listOf(" 800000 "," 1000000 "," 1500000 ", " 2000000 "," 3500000 "," 5000000 ")

    var showCityDialog by remember { mutableStateOf(false) }
    var showBudgetDialog by remember { mutableStateOf(false) }
    var showCalendarDialog by remember { mutableStateOf(false) }
    var isSelectingStartDate by remember { mutableStateOf(true) }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFBCBCBC).copy(alpha = 0.63f))
//            .clickable(enabled = false) {}, // جلوگیری از دریافت کلیک
//        contentAlignment = Alignment.Center
//    )
//    {
        Box(modifier = Modifier.zIndex(1f)) {
            Card(
                modifier = Modifier.size(380.dp, 580.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F4F4)),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box {
                    Column(
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 16.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("یه سفر جدید بساز و برنامه‌ش رو بچین", fontWeight = FontWeight.Bold, fontSize = 16.sp)

                        Text("اسم سفر", fontSize = 12.sp)
                        CustomTextField(
                            value = tripName,
                            onValueChange = { tripName = it },
                            width = 296.dp,
                            height = 45.dp,
                            placeholder = ""
                        )

                        Text("کجا می‌خوای بری؟", fontSize = 12.sp)
                        Box(modifier = Modifier.width(296.dp)) {
                            CustomDropdownField(
                                value = selectedCity,
                                onClick = { showCityDialog = true },
                                width = 296.dp,
                                height = 45.dp
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Column(horizontalAlignment = Alignment.End) {
                                Text("تاریخ پایان", fontSize = 12.sp)
                                CustomTextField(
                                    value = endDate,
                                    onValueChange = {},
                                    width = 100.dp,
                                    height = 45.dp,
                                    placeholder = "تاریخ پایان",
                                    readOnly = true,
                                    onClick = {
                                        isSelectingStartDate = false
                                        showCalendarDialog = true
                                    }
                                )
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text("تاریخ شروع", fontSize = 12.sp)
                                CustomTextField(
                                    value = startDate,
                                    onValueChange = {},
                                    width = 100.dp,
                                    height = 45.dp,
                                    placeholder = "تاریخ شروع",
                                    readOnly = true,
                                    onClick = {
                                        isSelectingStartDate = true
                                        showCalendarDialog = true
                                    }
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("تعداد همسفران", fontSize = 12.sp)
                            CustomTextField(
                                value = travelers,
                                onValueChange = { travelers = it },
                                width = 100.dp,
                                height = 45.dp,
                                placeholder = ""
                            )
                        }

                        Text("میزان بودجه؟", fontSize = 12.sp)
                        Box(modifier = Modifier.width(296.dp)) {
                            CustomDropdownField(
                                value = selectedBudget,
                                onClick = { showBudgetDialog = true },
                                width = 296.dp,
                                height = 45.dp
                            )
                        }

                        Text(
                            "این بودجه برای کل همسفران در نظر گرفته شود یا فقط خود شما؟",
                            fontSize = 12.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            BudgetRadioButton(
                                selected = !budgetForAll,
                                text = "فقط خودم",
                                onClick = { budgetForAll = false }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            BudgetRadioButton(
                                selected = budgetForAll,
                                text = "برای همه",
                                onClick = { budgetForAll = true }
                            )
                        }

                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(
                                onClick = { onDismiss() // بستن دیالوگ
                                    navController.navigate("overview")  },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(70.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("شروع برنامه‌ریزی سفر", color = Color.White, fontSize = 11.sp)
                            }
                        }
                    }

                    IconButton(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.zabdar),
                            contentDescription = "بازگشت",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            if (showCalendarDialog) {
                PersianCalendarDialog(
                    onDismiss = { showCalendarDialog = false },
                    onSave = { selectedStart, selectedEnd ->
                        startDate = selectedStart ?: startDate
                        endDate = selectedEnd ?: endDate
                        showCalendarDialog = false
                    }
                )
            }

            if (showCityDialog) {
                Box(
                    modifier = Modifier
                        .offset(x = (-248).dp, y = 180.dp)
                        .zIndex(2f)
                        .align(Alignment.TopEnd)
                ) {
                    CityDropdownMenu(
                        modifier = Modifier.width(60.dp),
                        cities = cities,
                        onCitySelected = {
                            selectedCity = it
                            showCityDialog = false
                        },
                        onDismissRequest = { showCityDialog = false }
                    )
                }
            }

            if (showBudgetDialog) {
                Box(
                    modifier = Modifier
                        .offset(x = (-248).dp, y = 397.dp)
                        .zIndex(2f)
                        .align(Alignment.TopEnd)
                ) {
                    BudgetDropdownMenu(
                        modifier = Modifier.width(60.dp),
                        budgets = budgets,
                        onBudgetSelected = {
                            selectedBudget = it
                            showBudgetDialog = false
                        },
                        onDismissRequest = { showBudgetDialog = false }
                    )
                }
            }
        }
    }

