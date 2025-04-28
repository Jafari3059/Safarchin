package com.example.safarchin.ui.theme.FourPageAsli

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.safarchin.R

@Composable
fun BottomNavigationBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val iconSize = screenWidth * 0.06f
    val selectedIconSize = screenWidth * 0.12f
    val itemBoxSize = screenWidth * 0.15f
    val navHeight = screenHeight * 0.08f

    val items = listOf("پروفایل", "خرید اشتراک", "برنامه ریزی", "خانه")

    val icons = listOf(
        R.drawable.profileicon,
        R.drawable.almas,
        R.drawable.taqvim,
        R.drawable.hoome,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(navHeight)
            .shadow(
                elevation = 40.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                ambientColor = Color.Black,
                spotColor = Color.Black,
                clip = false // ✅ جلوگیری از حذف سایه
            )
            .background(Color(0xFFFFFFFF), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .clickable { onItemSelected(index) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(itemBoxSize),
                        contentAlignment = Alignment.Center
                    ) {
                        if (selectedIndex == index) {
                            Box(
                                modifier = Modifier
                                    .size(itemBoxSize),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = icons[index]),
                                    contentDescription = item,
                                    modifier = Modifier.size(selectedIconSize),
                                    tint = Color(0xFFFFB26B)
                                )
                            }
                        } else {
                            Icon(
                                painter = painterResource(id = icons[index]),
                                contentDescription = item,
                                modifier = Modifier.size(iconSize),
                                tint = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}