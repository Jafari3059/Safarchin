package com.example.safarchin.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(16.dp)

    Box( // لایه خارجی برای سایه
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 10.dp,
                shape = shape,
                clip = false
            )
    ) {
        Box( // لایه اصلی سرچ‌بار
            modifier = Modifier
                .background(Color.White, shape)
                .width(343.dp)
                .height(53.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = iranSans,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp)
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    "اصفهان",
                                    fontFamily = iranSans,
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "جستجو",
                            tint = Color(0xFFFF8000),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    // مقدار تستی برای سرچ‌بار
    var searchText = remember { mutableStateOf("") }

    // پس‌زمینه‌ای با رنگ خنثی که سایه بهتر دیده بشه
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            value = searchText.value,
            onValueChange = { searchText.value = it }
        )
    }
}
