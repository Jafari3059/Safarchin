package com.example.safarchin.ui.theme.FourPageAsli.Plannig.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.safarchin.R

// فیلدهای بدون پاپ دراور
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
            .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(8.dp)) // ✅ کادر اضافه شد
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        contentAlignment = Alignment.CenterEnd
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Right,
                textDirection = TextDirection.ContentOrRtl
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.fillMaxSize()) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp),
                            style = TextStyle(
                                textDirection = TextDirection.ContentOrRtl
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}
//شهر و بودجه
@Composable
fun CustomDropdownField(
    value: String,
    onClick: () -> Unit,
    width: Dp,
    height: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clickable { onClick() }
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFFCFD1D4), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // آیکن سمت چپ
            Icon(
                painter = painterResource(id = R.drawable.down),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Color.Black
            )

            // متن سمت راست
            Text(
                text = value,
                fontSize = 12.sp,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                color = Color.Black
            )
        }
    }
}

//شهر
@Composable
fun CityDropdownMenu(
    modifier: Modifier = Modifier,
    cities: List<String>,
    onCitySelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .width(45.dp)
            .heightIn(max = 153.dp) // قابل اسکرول
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            cities.forEachIndexed { index, city ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)//سایز باکس شهرها
                        .background(
                            if (index % 2 == 0) Color(0xFFFFA871) else Color(0xFFFF8859),
                            shape = RoundedCornerShape(0.dp)
                        )
                        .clickable {
                            onCitySelected(city)
                            onDismissRequest()
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = city,
                        modifier = Modifier.padding(end = 12.dp),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// بودجه
@Composable
fun BudgetDropdownMenu(
    modifier: Modifier = Modifier,
    budgets: List<String>,
    onBudgetSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .width(45.dp)
            .heightIn(max = 153.dp)
            .zIndex(1f)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(2.dp))
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            budgets.forEachIndexed { index, budget ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp)
                        .background(
                            if (index % 2 == 0) Color(0xFFF5F5F5) else Color.White,
                            shape = RoundedCornerShape(0.dp)
                        )
                        .clickable {
                            onBudgetSelected(budget)
                            onDismissRequest()
                        },
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = budget,
                        modifier = Modifier.padding(end = 12.dp),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

// بودجه
@Composable
fun BudgetRadioButton(
    selected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(if (selected) Color(0xFFFF7B54) else Color(0xFFCFD1D4))
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = text, fontSize = 12.sp)
    }
}