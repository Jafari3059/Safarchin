package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop

import android.net.Uri
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
import kotlinx.coroutines.delay

@Composable
fun SoqatiDetaP(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
    val city = viewModel.selectedCity
    val soqatiList = city?.souvenirs ?: emptyList()
    val bannerItem = soqatiList.firstOrNull()

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerItem?.imageResList?.size ?: 1 }
    )

    LaunchedEffect(pagerState.currentPage) {
        delay(5000L)
        val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
        pagerState.animateScrollToPage(
            page = nextPage,
            animationSpec = tween(durationMillis = 2, easing = LinearOutSlowInEasing)
        )
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F4F4))
            .verticalScroll(scrollState)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Image(
                    painter = painterResource(id = bannerItem?.imageResList?.getOrNull(page) ?: R.drawable.placeholder),
                    contentDescription = "Banner Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.9f
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFF6F4F4)),
                            startY = 450f
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(bannerItem?.imageResList?.size ?: 1) { index ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(if (pagerState.currentPage == index) 8.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (pagerState.currentPage == index) Color(0xFFFF9800)
                                else Color.LightGray
                            )
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 24.dp, top = 42.dp)
                    .size(20.dp)
                    .clickable { navController.popBackStack() },
                tint = Color.Black
            )

            Text(
                modifier = Modifier
                    .width(220.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .padding(horizontal = 14.dp, vertical = 34.dp),
                text = "سوغاتی‌ها",
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(screenHeight * 0.015f))

        val tabs = listOf("هنرهای سنتی", "پوشاک", "خوردنی ها", "همه")
        var selectedTab by remember { mutableStateOf("همه") }

        TabBar(
            tabs = tabs,
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it },
            modifier = Modifier.padding(horizontal = screenWidth * 0.06f)
        )

        Spacer(modifier = Modifier.height(screenHeight * 0.015f))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            soqatiList.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)

                    rowItems.forEach { item ->
                        SoqatiCard(
                            soqati = item,
                            navController = navController,
                            modifier = Modifier
                                .width(160.dp)
                                .height(210.dp)

                        )
                    }


                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.width(160.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
