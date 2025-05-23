package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlcCard
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop.SoqatiCard
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
//import com.example.safarchin.ui.theme.irgitiFont
import kotlinx.coroutines.delay

@Composable
fun TourPlaceDetaP(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
    val city = viewModel.selectedCity
    val tourplaceList = city?.touristPlaces ?: emptyList()

    val bannerItem = tourplaceList.firstOrNull()

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

//    val images = listOf(
//        R.drawable.amarat_shapouri,
//        R.drawable.bagh_jahannama,
//        R.drawable.bagh_afifabad,
//        R.drawable.arg_karimkhan,
//        R.drawable.bazaar_vakil
//    )
//
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        pageCount = { images.size }
//    )

    // ✅ تغییر خودکار عکس هر ۵ ثانیه
    LaunchedEffect(pagerState.currentPage) {
        delay(5000L)
        val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
        pagerState.animateScrollToPage(
            page = nextPage,
            animationSpec = tween(
                durationMillis = 2, // مثلا 600 میلی‌ثانیه
                easing = LinearOutSlowInEasing
            )
        )
    }
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFF6F4F4))
            .verticalScroll(scrollState) // ✅ اسکرول‌پذیر کردن کل صفحه
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            // فقط یک HorizontalPager
            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fill, // 👈 تمام عرض صفحه رو بگیره
                modifier = Modifier.fillMaxSize()

            ) { page ->
                Image(
                    painter = painterResource(id = bannerItem?.imageResList?.getOrNull(page) ?: R.drawable.placeholder),
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    contentScale = ContentScale.Crop,
                    alpha = 0.9f // ✅ شفافیت تصویر
                )
            }

            // گرادینت پایین برای محو کردن
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
            // ✅ دایره‌های پایین وسط
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
                painter = painterResource(id = R.drawable.back), // آیکون برگشت خودت
                contentDescription = "بازگشت",
                modifier = Modifier
                    .align(Alignment.TopStart) // یا .TopEnd برای سمت راست
                    .padding(start = 24.dp, top = 42.dp)
                    .size(20.dp)
                    .clickable {
                        navController.popBackStack() // رفتن به عقب
                    },
                tint = Color.Black
            )
            Text(
                modifier = Modifier
                    .width(220.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .padding(horizontal = 24.dp, vertical = 34.dp),
                text = "مکان\u200Cهای دیدنی و جاذبه \u200Cهای گردشگری  در اصفهان",
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .wrapContentHeight() // 👈 ارتفاع بر اساس محتوا
                .shadow(
                    elevation = 8.dp, // شدت سایه
                    shape = RoundedCornerShape(8.dp),
                    clip = false // خیلی مهم برای دیده شدن سایه بیرون از Box
                )
                .background(color = Color(0xFFFFFFFF), RoundedCornerShape(8.dp))
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                text = " اصفهان؛ تلفیق تاریخ، زندگی و قصه\u200C در کوچه\u200Cپس\u200Cکوچه\u200Cهای خیال\u200Cانگیز\n" +
                        "سفر به اصفهان یعنی غرق شدن در زیبایی\u200Cهای بی\u200Cانتها؛ جایی که هر گوشه\u200Cاش داستانی برای گفتن داره. از شکوه میدان نقش جهان و عظمت کاخ چهلستون گرفته تا سکوت روح\u200Cنواز کلیسای وانک و زیبایی بی\u200Cتکرار سی\u200Cوسه\u200Cپل، این شهر فقط مقصد نیست؛ یه تجربه\u200Cی تمام\u200Cعیاره.\n" +
                        "اما اصفهان فقط به بناهای تاریخی محدود نمی\u200Cشه. کوچه\u200Cهای سنگ\u200Cفرش\u200Cشده\u200Cی محله\u200Cی جلفا، کافه\u200Cهای دنج و فضای دل\u200Cنشین محله\u200Cهای قدیمی مثل دردشت و عباسیان، همه و همه بخشی از اون چیزی هستن که به این شهر روح می\u200Cدن. اینجا جاییه که گذشته و حال کنار هم زندگی می\u200Cکنن.\n" +
                        "ما برای معرفی این فضاها، به منابع معتبری مثل The Culture Trip، Lonely Planet، و تجربه\u200Cنویسی\u200Cهای مسافران واقعی در سایت\u200Cهایی مثل Tripadvisor و وبلاگ\u200Cهای فارسی سر زدیم. هدفمون این بود که مکان\u200Cهایی رو معرفی کنیم که هم مورد توجه گردشگرا بودن، هم حس واقعی اصفهان رو منتقل کنن.",
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = 8.sp,
                color = Color.Black,
                textAlign = TextAlign.Right,
            )
        }

        Spacer(modifier = Modifier.height(screenHeight * 0.015f)) // 🔽 کمتر از قبل


        var selectedNavIndex by remember { mutableStateOf(2) }
        var selectedCardId by remember { mutableStateOf<Int?>(null) }
        var selectedTab by remember { mutableStateOf("همه") }

        // تب‌ها
        val tabs = listOf("محبوب ترین","فضای بسته","فضای باز", "رایگان", "همه")
        TabBar(
            tabs = tabs,
            selectedTab = selectedTab,
            onTabSelected = {
                selectedTab = it
                selectedCardId = null
            },
            modifier = Modifier.padding(horizontal = screenWidth * 0.06f)
        )
        Spacer(modifier = Modifier.height(screenHeight * 0.015f)) // 🔽 کمتر از قبل


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7)),
//                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            tourplaceList.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    rowItems.forEach { item ->
                        TourPlcCard(
                            place = item,
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
//            TourPlaceCard(
//                TourPlace(
//                    name = "حافظیه",
//                    description = "آرامگاه حافظ، شاعر بزرگ ایرانی، با معماری زیبا و فضای دل‌نشین، مکانی مناسب برای علاقه‌مندان به شعر و ادب فارسی است.",
//                    imageRes = R.drawable.hafezieh,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "۲۰٬۰۰۰ تومان",
//                    address = "شیراز، بلوار حافظ",
//                    telephone = "07132270007",
//                    WorkingHours = "۸ صبح تا ۱۰ شب"
//                ),
//            )
//            TourPlaceCard(
//                TourPlace(
//                    name = "سعدیه",
//                    description = "آرامگاه سعدی، شاعر و حکیم بزرگ ایرانی، در باغی زیبا با فضای آرامش‌بخش واقع شده و محل مناسبی برای دوستداران ادبیات است.",
//                    imageRes = R.drawable.saadiyeh,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "۱۵٬۰۰۰ تومان",
//                    address = "شیراز، انتهای خیابان بوستان",
//                    telephone = "07132270007",
//                    WorkingHours = "۸ صبح تا ۸ شب"
//                ),
//            )
//            TourPlaceCard(
//                TourPlace(
//                    name = "باغ دلگشا",
//                    description = "یکی از قدیمی‌ترین باغ‌های شیراز با عمارت تاریخی و فضای سرسبز، مناسب برای گردش و استراحت در طبیعت.",
//                    imageRes = R.drawable.bagh_delgosha,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "۱۰٬۰۰۰ تومان",
//                    address = "شیراز، خیابان بوستان، نزدیکی سعدیه",
//                    telephone = "07132270007",
//                    WorkingHours = "۸ صبح تا ۸ شب"
//                ),
//            )
//            TourPlaceCard(
//                TourPlace(
//                    name = "باغ نارنجستان قوام",
//                    description = "باغی زیبا با عمارت تاریخی متعلق به دوره قاجار، با کاشی‌کاری‌ها و آینه‌کاری‌های هنرمندانه.",
//                    imageRes = R.drawable.narenjestan_qavam,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "۱۵٬۰۰۰ تومان",
//                    address = "شیراز، خیابان لطفعلی‌خان زند",
//                    telephone = "07132270007",
//                    WorkingHours = "۸ صبح تا ۸ شب"
//                ),
//            )
//            TourPlaceCard(
//                TourPlace(
//                    name = "ارگ کریم‌خان",
//                    description = "قلعه‌ای تاریخی از دوره زندیه با معماری خاص و برج‌های بلند، نماد قدرت و حکومت کریم‌خان زند.",
//                    imageRes = R.drawable.arg_karimkhan,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "۱۵٬۰۰۰ تومان",
//                    address = "شیراز، میدان شهرداری",
//                    telephone = "07132270007",
//                    WorkingHours = "۸ صبح تا ۸ شب"
//                ),
//            )
//            TourPlaceCard(
//                TourPlace(
//                    name = "بازار وکیل",
//                    description = "بازاری سنتی با معماری زیبا و فروشگاه‌های متنوع، مناسب برای خرید سوغات و صنایع دستی.",
//                    imageRes = R.drawable.bazaar_vakil,
//                    Visit_duration = "۱ ساعت",
//                    Visit_price = "رایگان",
//                    address = "شیراز، خیابان لطفعلی‌خان زند",
//                    telephone = "07132270007",
//                    WorkingHours = "۹ صبح تا ۹ شب"
//                ),
//            )

            Spacer(modifier = Modifier.height(16.dp))

        }



    }
}

//@Preview(showBackground = true)
//@Composable
//fun TourPlacePreview() {
//    TourPlaceDetaP()
//}