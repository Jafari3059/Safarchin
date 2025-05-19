package com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.planingT

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

data class PlaceWithTime(
    val name: String,
    var time: String,
    var isSelected: Boolean = false
)



@Composable
fun PlacePickerDialog(
    onDismiss: () -> Unit,
    onConfirm: (List<PlaceWithTime>) -> Unit,
    dialogTitle: String
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val fontSizeTitle = (screenWidthDp * 0.026f).sp
    val fontSizeText = (screenWidthDp * 0.024f).sp
    val fontSizeRow = (screenWidthDp * 0.022f).sp

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val dialogWidth = screenWidth * 0.75f // مثلاً 85 درصد عرض صفحه

    val places = remember {
        mutableStateListOf(
            PlaceWithTime("سی و سه پل", ""),
            PlaceWithTime("پل خواجو", ""),
            PlaceWithTime("کلیسای وانک", ""),
            PlaceWithTime("منارجنبان", ""),
            PlaceWithTime("باغ پرندگان", ""),
            PlaceWithTime("چهل ستون", ""),
            PlaceWithTime("آتشگاه", "")
        )
    }

    Column(
        modifier = Modifier
            .width(dialogWidth)
            .wrapContentHeight()
            .heightIn(min = 330.dp) // حداقل ارتفاع
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //  عنوان دیالوگ
        Text(
            text = dialogTitle,
            fontWeight = FontWeight.Bold,
            fontSize = fontSizeTitle,
            fontFamily = iranSans,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        //  توضیح بالا
        Text(
            text = "از لیست زیر می‌تونی مکان‌هایی که قراره تو این تاریخ اولین بازدید داشته باشی رو انتخاب به برنامه‌ات اضافه کنی.",
            fontSize = fontSizeText,
            fontFamily = iranSans,
            color = Color.Black,
            textAlign = TextAlign.Right,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(14.dp))

        //  جدول لیست با ارتفاع ثابت
        Box(
            modifier = Modifier
                .width(246.dp)
                .height(180.dp) // ارتفاع همیشه ثابت
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF6F4F4))
                .border(1.dp, Color(0xFFCFD1D4), RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                places.forEachIndexed { index, place ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .background(Color(0xFFF6F4F4))
                            .border(0.5.dp, Color(0xFFCFD1D4))
                            .padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = "ساعت",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(10.dp)
                            )
                            CustomTextField(
                                value = place.time,
                                onValueChange = { input ->
                                    val digits = input.filter { it.isDigit() }.take(8)
                                    places[index] = place.copy(
                                        time = if (digits.length >= 8)
                                            "${digits.substring(0, 2)}:${digits.substring(2, 4)} _ ${digits.substring(4, 6)}:${digits.substring(6, 8)}"
                                        else digits
                                    )
                                },
                                width = 90.dp,
                                height = 26.dp,
                                placeholder = "00:00 _ 00:00"
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = place.name,
                                fontSize = fontSizeRow,
                                fontFamily = iranSans,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(
                                        if (place.isSelected) Color(0xFFFFB26B) else Color.White,
                                        RoundedCornerShape(4.dp)
                                    )
                                    .border(1.dp, Color(0xFFCFD1D4), RoundedCornerShape(4.dp))
                                    .clickable {
                                        places[index] = place.copy(isSelected = !place.isSelected)
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                if (place.isSelected) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.check),
                                        contentDescription = "انتخاب شده",
                                        tint = Color.White,
                                        modifier = Modifier.size(10.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // ❹ دکمه‌ها
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(width = 90.dp, height = 34.dp)
            ) {
                Text("لغو", fontFamily = iranSans, fontWeight = FontWeight.Bold, fontSize = fontSizeRow)
            }
            Spacer(modifier = Modifier.width(6.dp)) // این فاصله بین دو دکمه رو زیاد می‌کنه

            Button(
                onClick = {
                    val selected = places.filter { it.isSelected }
                    if (selected.isNotEmpty()) {
                        onConfirm(selected)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.size(width = 90.dp, height = 34.dp)
            ) {
                Text("افزودن", fontFamily = iranSans, fontWeight = FontWeight.Bold, fontSize = fontSizeRow)
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    width: Dp,
    height: Dp,
    placeholder: String,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
    ) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                // فقط اجازه ورود اعداد بده
                if (newValue.all { it.isDigit() }) {
                    onValueChange(newValue)
                }
            },
            readOnly = readOnly,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 12.sp, // فونت کوچکتر
                textAlign = TextAlign.Center,
                // textDirection = TextDirection.Rtl
                textDirection = TextDirection.Ltr,
                fontFamily = iranSans // اگر فونت خاص داری

            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 6.dp), // padding مناسب
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Right,
                            style = TextStyle(textDirection = TextDirection.Rtl),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}



//@Preview(showBackground = true)
//@Composable
//fun PreviewPlacePickerDialog() {
//    PlacePickerDialog(
//        onDismiss = {},
//        onConfirm = {},
//        dialogTitle = "مکان‌های روز سه‌شنبه 1403/6/23"
//    )
//}