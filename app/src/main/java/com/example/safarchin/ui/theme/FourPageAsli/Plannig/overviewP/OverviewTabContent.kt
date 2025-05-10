package com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlace
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlacesBox
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlacesViewModel

@Composable
fun OverviewTabContent(savedPlacesViewModel: SavedPlacesViewModel) {
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
            ),
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

        SavedPlacesBox(viewModel = savedPlacesViewModel)

        Spacer(modifier = Modifier.height(spacerHeight))

        SuggestedPlacesSection()

        // ✅ دکمه تستی برای افزودن کارت ذخیره شده موقت
//        Button(
//            onClick = {
//                savedPlacesViewModel.addPlace(
//                    SavedPlace(
//                        id = 1,
//                        name = "مسجد نصیرالملک",
//                        imageRes = R.drawable.shiraz
//                    )
//                )
//            },
//            modifier = Modifier.padding(12.dp)
//        )
//        {
////            Text("+ افزودن تستی")
//        }


    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewOverviewTabContent() {
    val fakeViewModel = SavedPlacesViewModel().apply {
        addPlace(SavedPlace(1, "مسجد نصیرالملک", R.drawable.shiraz))
    }

    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F4F4))
        ) {
            OverviewTabContent(savedPlacesViewModel = fakeViewModel)
        }
    }
}
