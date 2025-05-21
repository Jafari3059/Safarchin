
package com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.planingT

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans


@Composable
fun PlanningTabContent() {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    val screenWidthDp = screenWidth.dp
    val screenHeightDp = screenHeight.dp
    val fontSize = (screenWidth * 0.035).sp
    val inputHeight = screenHeightDp * 0.05f
    val verticalSpacing = screenHeightDp * 0.01f

    val scrollState = rememberScrollState()

    val days = remember {
        mutableStateListOf(
            "سه شنبه 1403/6/23",
            "چهار شنبه 1403/6/24",
            "پنج شنبه 1403/6/25",
            "جمعه 1403/6/26",
            "شنبه 1403/6/27"
        )
    }

    val expandedStates = remember { mutableStateListOf<Boolean>().apply { repeat(days.size) { add(false) } } }
    val isEditing = remember { mutableStateListOf<Boolean>().apply { repeat(days.size) { add(false) } } }

    val placeImages = mapOf(
        "سی و سه پل" to R.drawable.shiraz,
        "پل خواجو" to R.drawable.khajo,
        "کلیسای وانک" to R.drawable.shiraz,
        "منارجنبان" to R.drawable.shiraz,
        "باغ پرندگان" to R.drawable.shiraz,
        "میدان نقش جهان(امام)" to R.drawable.meydan_emam,
        "چهل ستون" to R.drawable.shiraz,
        "رستوران شهرزاد" to R.drawable.meydan_emam
    )

    data class SelectedPlace(val day: String, val name: String, val imageRes: Int, val time: String)

    val placesByDay = remember {
        mutableStateMapOf<String, SnapshotStateList<SelectedPlace>>().apply {
            days.forEach { put(it, mutableStateListOf()) }
        }
    }

    var selectedDayIndex by remember { mutableStateOf(0) }
    var currentDialogTitle by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }
    var hoveredIndex by remember { mutableStateOf(-1) }
    var draggedPlace by remember { mutableStateOf<SelectedPlace?>(null) }
    var dragTargetDay by remember { mutableStateOf<String?>(null) }

    fun formatTimeWithPeriod(time: String): String {
        return try {
            val parts = time.split("_")
            if (parts.size != 2) return ""
            val (start, end) = parts.map { it.trim() }
            val startHour = start.split(":")[0].toInt()
            val endHour = end.split(":")[0].toInt()
            fun period(h: Int) = when (h) {
                in 5..11 -> "صبح"
                in 12..16 -> "ظهر"
                else -> "شب"
            }
            "${period(startHour)} $start _ ${period(endHour)} $end"
        } catch (e: Exception) { "" }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF6F4F4))
            .padding(horizontal = screenWidthDp * 0.05f, vertical = verticalSpacing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        days.forEachIndexed { index, day ->
            val places = placesByDay[day] ?: mutableStateListOf()
            var editedText by remember { mutableStateOf(day) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = verticalSpacing)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(8.dp)
                        .pointerInput(day) {
                            detectDragGestures(
                                onDrag = { change, _ ->
                                    change.consume()
                                    dragTargetDay = day
                                },
                                onDragEnd = {
                                    draggedPlace?.let { place ->
                                        if (dragTargetDay != null && dragTargetDay != place.day) {
                                            placesByDay[place.day]?.remove(place)
                                            placesByDay[dragTargetDay!!]?.add(place.copy(day = dragTargetDay!!))
                                            val oldIndex = days.indexOf(place.day)
                                            if (placesByDay[place.day]?.isEmpty() == true && oldIndex != -1) {
                                                expandedStates[oldIndex] = false
                                            }
                                        }
                                        draggedPlace = null
                                        dragTargetDay = null
                                    }
                                }
                            )
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(inputHeight)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = if (expandedStates[index]) R.drawable.up else R.drawable.down),
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .clickable { expandedStates[index] = !expandedStates[index] }
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (isEditing[index]) {
                                TextField(
                                    value = editedText,
                                    onValueChange = { editedText = it },
                                    singleLine = true,
                                    textStyle = LocalTextStyle.current.copy(
                                        fontSize = fontSize,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Right,
                                        fontFamily = iranSans
                                    ),
                                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                    keyboardActions = KeyboardActions(onDone = {
                                        days[index] = editedText
                                        isEditing[index] = false
                                    }),
                                    modifier = Modifier.weight(1f)
                                )
                            } else {
                                Text(
                                    text = day,
                                    fontSize = fontSize,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = iranSans,
                                    color = Color.Black,
                                    modifier = Modifier.clickable {
                                        isEditing[index] = true
                                        editedText = day
                                    }
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                painter = painterResource(id = R.drawable.location3),
                                contentDescription = "مکان‌ها",
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        currentDialogTitle = "مکان‌های روز $day"
                                        selectedDayIndex = index
                                        isPopupVisible = true
                                    }
                            )
                        }
                    }

                    if (expandedStates[index]) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            if (places.isEmpty()) {
                                Text(
                                    text = "برنامه‌ای برای این روز نداری",
                                    fontSize = fontSize,
                                    fontFamily = iranSans,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .padding(vertical = 16.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                            else {
                                places.forEachIndexed { i, place ->
                                    val isBeingDragged = draggedPlace == place

                                    Box(
                                        modifier = Modifier
                                            .graphicsLayer {
                                                if (isBeingDragged) alpha = 0.5f
                                            }
                                            .pointerInput(place) {
                                                detectDragGesturesAfterLongPress(
                                                    onDragStart = {
                                                        draggedPlace = place
                                                        dragTargetDay = day
                                                        hoveredIndex = i
                                                    },
                                                    onDrag = { change, _ ->
                                                        change.consume()
                                                        hoveredIndex = i
                                                    },
                                                    onDragEnd = {
                                                        val currentList = placesByDay[draggedPlace!!.day]
                                                        if (day == draggedPlace!!.day && currentList != null) {
                                                            val fromIndex = currentList.indexOf(draggedPlace)
                                                            val toIndex = hoveredIndex
                                                            if (fromIndex != -1 && toIndex != -1 && fromIndex != toIndex) {
                                                                currentList.removeAt(fromIndex)
                                                                currentList.add(
                                                                    if (fromIndex < toIndex) toIndex - 1 else toIndex,
                                                                    draggedPlace!!
                                                                )
                                                            }
                                                        } else if (dragTargetDay != null && dragTargetDay != draggedPlace!!.day) {
                                                            placesByDay[draggedPlace!!.day]?.remove(draggedPlace)
                                                            placesByDay[dragTargetDay!!]?.add(draggedPlace!!.copy(day = dragTargetDay!!))
                                                        }

                                                        hoveredIndex = -1
                                                        draggedPlace = null
                                                        dragTargetDay = null
                                                    },
                                                    onDragCancel = {
                                                        hoveredIndex = -1
                                                        draggedPlace = null
                                                        dragTargetDay = null
                                                    }
                                                )
                                            }
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = formatTimeWithPeriod(place.time),
                                                fontSize = fontSize * 0.85f,
                                                color = Color.Gray,
                                                modifier = Modifier
                                                    .align(Alignment.End)
                                                    .padding(end = 4.dp, bottom = 2.dp)
                                            )

                                            Box(
                                                modifier = Modifier
                                                    .width(324.dp)
                                                    .height(54.dp)
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .background(Color(0xFFF6F4F4))
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(horizontal = 8.dp)
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = place.imageRes),
                                                        contentDescription = null,
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier
                                                            .size(width = 99.dp, height = 54.dp)
                                                            .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                                                    )

                                                    Spacer(modifier = Modifier.width(4.dp))

                                                    Icon(
                                                        painter = painterResource(id = R.drawable.trashcan),
                                                        contentDescription = "حذف",
                                                        modifier = Modifier
                                                            .size(20.dp)
                                                            .offset(y = 14.dp)
                                                            .clickable {
                                                                places.remove(place)
                                                                if (places.isEmpty()) expandedStates[index] = false
                                                            }
                                                    )

                                                    Spacer(modifier = Modifier.weight(1f))

                                                    Text(
                                                        text = place.name,
                                                        fontSize = 14.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        fontFamily = iranSans,
                                                        color = Color.Black,
                                                        modifier = Modifier.padding(end = 4.dp)
                                                    )

                                                    Spacer(modifier = Modifier.width(4.dp))

                                                    Box(
                                                        modifier = Modifier.size(32.dp),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(id = R.drawable.menu_button),
                                                            contentDescription = null,
                                                            modifier = Modifier.size(24.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        if (isPopupVisible) {
            Dialog(
                onDismissRequest = { isPopupVisible = false },
                properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnClickOutside = true)
            ) {
                PlacePickerDialog(
                    onDismiss = { isPopupVisible = false },
                    onConfirm = { selectedList ->
                        selectedList.forEach {
                            val imageRes = placeImages[it.name] ?: R.drawable.placeholder
                            placesByDay[days[selectedDayIndex]]?.add(
                                SelectedPlace(
                                    day = days[selectedDayIndex],
                                    name = it.name,
                                    imageRes = imageRes,
                                    time = it.time
                                )
                            )
                        }
                        expandedStates[selectedDayIndex] = true
                        isPopupVisible = false
                    },
                    dialogTitle = currentDialogTitle
                )
            }
        }
        }
    }



