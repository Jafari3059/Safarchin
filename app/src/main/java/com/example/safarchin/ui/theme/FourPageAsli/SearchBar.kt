package com.example.safarchin.ui.theme.FourPageAsli

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans


@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "جستجو کنید...",
    cornerRadius: Dp = 16.dp,
    iconOnLeft: Boolean = false
) {
    val shape = RoundedCornerShape(50.dp)

    Box(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 10.dp,
                shape = shape,
                clip = false
            )
    ) {
        Box(
            modifier = Modifier
                .background(Color.White, shape)
                .width(343.dp)
                .height(53.dp)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange, // ارسال مقدار جدید
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Right
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = if (iconOnLeft) Arrangement.Start else Arrangement.End
                    ) {
                        if (iconOnLeft) {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "جستجو",
                                tint = Color(0xFFFF8000),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.CenterEnd // مهم برای راست‌چین بودن کرسر

                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    placeholderText,
                                    fontFamily = iranSans,
                                    fontSize = 14.sp,
                                    color = Color(0xFFBEBAB3),
                                    textAlign = TextAlign.Right,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }

                        if (!iconOnLeft) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "جستجو",
                                tint = Color(0xFFFF8000),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var searchText = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            placeholderText = "...جستجو در سفرها",
            cornerRadius = 24.dp,
            iconOnLeft = true
        )
    }
}

