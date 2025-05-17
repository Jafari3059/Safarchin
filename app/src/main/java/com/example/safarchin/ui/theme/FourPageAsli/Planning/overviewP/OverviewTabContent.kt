package com.example.safarchin.ui.theme.FourPageAsli.Planning.overviewP


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.TourPlaceCard
import com.example.safarchin.ui.theme.FourPageAsli.Planning.overviewP.Saved.SavedPlace
import com.example.safarchin.ui.theme.FourPageAsli.Planning.overviewP.Saved.SavedPlacesBox
import com.example.safarchin.ui.theme.FourPageAsli.Planning.overviewP.Saved.SavedPlacesViewModel
import com.example.safarchin.ui.theme.iranSans

@Composable
fun OverviewTabContent(
    savedPlacesViewModel: SavedPlacesViewModel,
    selectedPlacesViewModel: SelectedPlacesViewModel
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    val screenWidthDp = screenWidth.dp
    val screenHeightDp = screenHeight.dp

    val inputWidth = screenWidthDp * 0.9f
    val inputHeight = screenHeightDp * 0.05f
    val spacerHeight = screenHeightDp * 0.01f

    var notesExpanded by remember { mutableStateOf(false) }
    var checklistExpanded by remember { mutableStateOf(false) }
    var locationsExpanded by remember { mutableStateOf(false) }

    val checklistInput = remember { mutableStateOf("") }
    val checklistItems = remember { mutableStateListOf<Pair<String, Boolean>>() }

    val noteInput = remember { mutableStateOf("") }
    val noteItems = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = screenWidthDp * 0.05f,
                end = screenWidthDp * 0.05f,
                top = screenHeightDp * 0.01f,
                bottom = screenHeightDp * 0.12f
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OverviewInputField(
            placeholder = "یادداشت‌ها",
            height = inputHeight,
            width = inputWidth,
            isExpanded = notesExpanded,
            onToggle = { notesExpanded = !notesExpanded }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF6F4F4), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                ) {
                    TextField(
                        value = noteInput.value,
                        onValueChange = { noteInput.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Right,
                            fontFamily = iranSans
                        ),
                        placeholder = {
                            Text(
                                "...چیزی بنویسید",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth().padding(end = 0.dp)
                            )
                        },
                        singleLine = false,
                        maxLines = 4
                    )

                    Button(
                        onClick = {
                            val input = noteInput.value.trim()
                            if (input.isNotEmpty()) {
                                noteItems.add(input)
                                noteInput.value = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                           // .padding(start = 0.dp, bottom = 0.dp)
                            .offset(x = 2.dp, y=4.dp)
                            .width(66.dp)
                    ) {
                        Text("ثبت", fontFamily = iranSans, color = Color.White)
                    }
                }

                noteItems.forEachIndexed { index, text ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(1.dp)
                    ) {
                        Canvas(modifier = Modifier.matchParentSize()) {
                            drawRoundRect(
                                color = Color.Black,
                                size = size,
                                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                                style = Stroke(
                                    width = 2.5f,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f))
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFFF6F4F4), RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.trashcan),
                                contentDescription = null,
                                tint = Color(0xFF2D2D2D),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        noteItems.removeAt(index)
                                    }
                            )

                            Text(
                                text = text,
                                fontFamily = iranSans,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                textAlign = TextAlign.Right,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(spacerHeight))

        OverviewInputField(
            placeholder = "چک‌لیست",
            height = inputHeight,
            width = inputWidth,
            isExpanded = checklistExpanded,
            onToggle = { checklistExpanded = !checklistExpanded }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF6F4F4), RoundedCornerShape(16.dp))
                        .padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            val input = checklistInput.value.trim()
                            if (input.isNotEmpty()) {
                                checklistItems.add(input to false)
                                checklistInput.value = ""
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.width(66.dp)
                    ) {
                        Text("ثبت", fontFamily = iranSans, color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    TextField(
                        value = checklistInput.value,
                        onValueChange = { checklistInput.value = it },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(12.dp),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Right,
                            fontFamily = iranSans
                        ),
                        placeholder = {
                            Text(
                                "...آیتم جدید",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp,
                                color = Color.Gray,
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth().padding(end = 0.dp)
                            )
                        },
                        singleLine = true,
                        maxLines = 1
                    )
                }

                checklistItems.forEachIndexed { index, (text, isChecked) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(1.dp)
                    ) {
                        Canvas(modifier = Modifier.matchParentSize()) {
                            drawRoundRect(
                                color = Color.Black,
                                size = size,
                                cornerRadius = CornerRadius(16.dp.toPx(), 16.dp.toPx()),
                                style = Stroke(
                                    width = 2.5f,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f))
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFFF6F4F4), RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.trashcan),
                                contentDescription = null,
                                tint = Color(0xFF2D2D2D),
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        checklistItems.removeAt(index)
                                    }
                            )

                            Text(
                                text = text,
                                fontFamily = iranSans,
                                fontSize = 12.sp,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                textAlign = TextAlign.Right,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )

                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isChecked) Color(0xFF939B62) else Color.LightGray)
                                    .clickable {
                                        checklistItems[index] = checklistItems[index].copy(second = !isChecked)
                                    }
                            ) {}
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(spacerHeight))

        OverviewInputField(
            placeholder = "مکان‌های منتخب",
            height = inputHeight,
            width = inputWidth,
            isExpanded = locationsExpanded,
            onToggle = { locationsExpanded = !locationsExpanded }
        ) {
            if (selectedPlacesViewModel.selectedPlaces.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(inputHeight * 3)
                ) {}
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    selectedPlacesViewModel.selectedPlaces.forEach { place ->
                        val tourPlace = TourPlace(
                            name = place.name,
                            description = "میدان نقش جهان یا میدان امام اصفهان، یکی از مهم ترین جاذبه های گردشگری و میدان مرکزی شهراصفهان است...",
                            imageRes = place.imageRes,
                            Visit_duration = "۱ ساعت",
                            Visit_price = "۲۰٬۰۰۰ تومان",
                            address = "آدرس تستی",
                            telephone = 12345678,
                            WorkingHours = "۹ تا ۱۷"
                        )
                        TourPlaceCard(
                            place = tourPlace,
                            onRemoveClick = {
                                selectedPlacesViewModel.removePlace(place.id)
                                if (selectedPlacesViewModel.selectedPlaces.isEmpty()) {
                                    locationsExpanded = false
                                }
                            },
                            showRemove = true
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(spacerHeight))

        SavedPlacesBox(
            viewModel = savedPlacesViewModel,
            onAddToPlanClicked = { place ->
                selectedPlacesViewModel.addPlace(place)
                locationsExpanded = true
            }
        )

        Spacer(modifier = Modifier.height(spacerHeight))

        Button(
            onClick = {
                savedPlacesViewModel.addPlace(
                    SavedPlace(
                        id = 1,
                        name = "مسجد نصیرالملک",
                        imageRes = R.drawable.shiraz
                    )
                )
            },
            modifier = Modifier.padding(12.dp)
        ) {
            Text("+ افزودن تستی")
        }

        SuggestedPlacesSection()
    }
}

@Composable
fun OverviewInputField(
    placeholder: String,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    height: Dp = 50.dp,
    width: Dp = 348.dp,
    content: @Composable (() -> Unit)? = null
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val fontSize = (screenWidth * 0.03).sp
    val iconSize = (screenWidth * 0.04).dp

    val actualHeight = if (isExpanded && content == null) height * 3 else height

    Box(
        modifier = Modifier
            .width(width)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .clickable { onToggle() }
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = if (isExpanded) R.drawable.up else R.drawable.down),
                    contentDescription = null,
                    modifier = Modifier.size(iconSize),
                    tint = Color.Unspecified
                )
                Text(
                    text = placeholder,
                    fontFamily = iranSans,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize,
                    textAlign = TextAlign.Right
                )
            }

            //  محتوا وقتی فیلد باز بشه
            if (isExpanded && content != null) {
                Spacer(modifier = Modifier.height(8.dp))
                content()
            }
        }
    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewOverviewTabContent() {
    val savedViewModel = remember {
        SavedPlacesViewModel().apply {
            addPlace(SavedPlace(1, "مسجد نصیرالملک", R.drawable.shiraz))
            addPlace(SavedPlace(2, "باغ ارم", R.drawable.khajo))
        }
    }

    val selectedViewModel = remember {
        SelectedPlacesViewModel().apply {
            addPlace(SavedPlace(1, "مسجد نصیرالملک", R.drawable.shiraz))
        }
    }

    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F4F4))
        ) {
            OverviewTabContent(
                savedPlacesViewModel = savedViewModel,
                selectedPlacesViewModel = selectedViewModel
            )
        }
    }
}

