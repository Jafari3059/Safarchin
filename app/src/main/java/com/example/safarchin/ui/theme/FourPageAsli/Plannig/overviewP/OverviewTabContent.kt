package com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.TourPlaceCard
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlace
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlacesBox
import com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP.Saved.SavedPlacesViewModel
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(inputHeight * 3)
            ) {}
        }
        Spacer(modifier = Modifier.height(spacerHeight))

        OverviewInputField(
            placeholder = "چک‌لیست",
            height = inputHeight,
            width = inputWidth,
            isExpanded = checklistExpanded,
            onToggle = { checklistExpanded = !checklistExpanded }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(inputHeight * 3)
            ) {}
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
            }
            else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    selectedPlacesViewModel.selectedPlaces.forEach { place ->
                        val tourPlace = TourPlace(
                            name = place.name,
                            description = "میدان نقش جهان یا میدان امام اصفهان، یکی از مهم ترین جاذبه های گردشگری و میدان مرکزی شهراصفهان است. در این میدان بسیاری دیگر از بناهای تاریخی و باستانی نیز قرار گرفته است. \u2028میدان نقش جهان اصفهان، ثبت سازمان یونسکو شده است به همین دلیل شهرت و شکوه بین المللی دارد همچنین میدان نقش جهان گنجینه شهر اصفهان است که سالانه  ",
                            imageRes = place.imageRes,
                            Visit_duration = "۱ ساعت",
                            Visit_price = "۲۰٬۰۰۰ تومان",
                            address = "آدرس تستی",
                            telephone = 12345678,
                            WorkingHours = "۹ تا ۱۷"
                        )
                            Box(
                                modifier = Modifier
                                   // .fillMaxWidth()
                                   // .padding(2.dp) // فاصله از اطراف برای نمایش سایه
                                   // .shadow(6.dp, RoundedCornerShape(2.dp))
                                   // .clip(RoundedCornerShape(8.dp))
                                   // .background(Color(0x40000000),)
                            )
                        {
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
    content: @Composable (() -> Unit)? = null // ✅ اضافه شده
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

            // ✅ محتوا وقتی فیلد باز بشه
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

//@Preview(showBackground = true, locale = "fa")
//@Composable
//fun PreviewOverviewTabContent() {
//    val fakeViewModel = SavedPlacesViewModel().apply {
//        addPlace(SavedPlace(1, "مسجد نصیرالملک", R.drawable.shiraz))
//    }
//
//    MaterialTheme {
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFF6F4F4))
//        ) {
//            OverviewTabContent(savedPlacesViewModel = fakeViewModel)
//        }
//    }
//}
