package com.example.safarchin.ui.theme.FourPageAsli.planing.overviewP

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
//import com.example.safarchin.ui.theme.iranSans

@Composable
fun OverviewTabContent() {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val screenHeight = config.screenHeightDp

    val screenWidthDp = screenWidth.dp
    val screenHeightDp = screenHeight.dp

    val inputWidth = screenWidthDp * 0.9f
    val inputHeight = screenHeightDp * 0.07f
    val spacerHeight = screenHeightDp * 0.01f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = screenWidthDp * 0.05f,
                end = screenWidthDp * 0.05f,
                top = screenHeightDp * 0.01f,
                bottom = screenHeightDp * 0.12f
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OverviewInputField(
            placeholder = "یادداشت‌ها",
            height = inputHeight,
            width = inputWidth
        )
        Spacer(modifier = Modifier.height(spacerHeight))

        OverviewInputField(
            placeholder = "چک‌لیست",
            height = inputHeight,
            width = inputWidth
        )
        Spacer(modifier = Modifier.height(spacerHeight))

        OverviewInputField(
            placeholder = "مکان‌های منتخب",
            height = inputHeight,
            width = inputWidth
        )
        Spacer(modifier = Modifier.height(spacerHeight))

        SavedPlacesBox()

        Spacer(modifier = Modifier.height(spacerHeight))

        SuggestedPlacesSection()
    }
}


@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewOverviewTabContent() {
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F4F4))
        ) {
            OverviewTabContent()
        }
    }
}
