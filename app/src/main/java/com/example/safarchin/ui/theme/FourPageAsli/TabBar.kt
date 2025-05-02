package com.example.safarchin.ui.theme.FourPageAsli

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.ui.theme.iranSans

@Composable
fun TabBar(
    tabs: List<String>,
    selectedTab: String,
    onTabSelected: (String) -> Unit,
    modifier: Modifier = Modifier // ✅ اضافه شده برای کنترل موقعیت از بیرون
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            val isSelected = tab == selectedTab
            Box(
                modifier = Modifier
                    .background(
                        color = if (isSelected) Color(0xFF939B62) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            ) {
                Text(
                    text = tab,
                    fontSize = 10.sp,
                    color = if (isSelected) Color.White else Color(0xFFABB7C2),
                    fontFamily = iranSans
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabBarPreview() {
    var selectedTab by remember { mutableStateOf("همه") }

    val tabs = listOf("در حال برنامه‌ریزی", "به اتمام رسیده‌ها", "همه") // لیست اصلی

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
    ) {
        TabBar(
            tabs = tabs,
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
    }
}
