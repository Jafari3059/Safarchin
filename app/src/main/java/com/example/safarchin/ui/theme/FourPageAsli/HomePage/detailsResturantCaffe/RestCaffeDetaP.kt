package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsResturantCaffe

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.rest_kafe
import com.example.safarchin.ui.theme.FourPageAsli.TabBar
import com.example.safarchin.ui.theme.iranSans
//import com.example.safarchin.ui.theme.irgitiFont
import kotlinx.coroutines.delay

@Composable
fun RestCaffeDetaP(navController: NavController) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val images = listOf(
        R.drawable.fath_olmolk,
        R.drawable.haft_khan,
        R.drawable.sherzeh
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { images.size }
    )

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
                    painter = painterResource(id = images[page]),
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
                repeat(images.size) { index ->
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
                text = "رستوران ها و کافه های شیراز",
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
                text = " اگه مثل ما عاشق امتحان کردن طعم\u200Cهای جدید باشی، سفر به اصفهان بدون سر زدن به رستوران\u200Cها و غذاخوری\u200Cهای معروفش کامل نیست. از رستوران\u200Cهای سنتی مثل شهرزاد و جارچی باشی گرفته تا انتخاب\u200Cهای متنوعی مثل مجتمع غذایی ترنج، این شهر برای هر سلیقه\u200Cای یه پیشنهاد خوشمزه داره.\n" +
                        "ما برای انتخاب این لیست، از تجربه کاربران و پیشنهادهای معتبر در سایت\u200Cهایی مثل The Culture Trip و Travital.com کمک گرفتیم تا مطمئن شیم جاهایی رو معرفی می\u200Cکنیم که حسابی محبوب و امتحان\u200Cپس\u200Cداده\u200Cان.",
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
        val tabs = listOf("اقتصادی","ارزانترین", "محبوب ترین", "همه")
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
            RestCaffeCard(
                rest_kafe(
                    title = "رستوران هفت‌خوان",
                    description = "مجموعه‌ای لوکس با چند رستوران و کافی‌شاپ در طبقات مختلف که هرکدام غذاهایی خاص ارائه می‌دهند؛ شامل غذاهای ایرانی، فرنگی، فست‌فود و صبحانه. فضای مدرن و خدمات حرفه‌ای، این رستوران را به یکی از محبوب‌ترین مقاصد غذایی در شیراز تبدیل کرده است.",
                    rating = 5,
                    imageResId = R.drawable.haft_khan,
                    address = "شیراز، بلوار جدید قرآن، نبش کوچه هفدهم",
                    telephone = "07132270000",
                    WorkingHours = "۷ صبح تا ۱۱:۳۰ شب"
                ),
            )
            RestCaffeCard(
                rest_kafe(
                    title = "کافه عمارت فتح‌الملوکی",
                    description = "کافه‌ای با طراحی سنتی و فضای دل‌نشین در عمارت تاریخی، با منویی متنوع شامل نوشیدنی‌های سنتی، قهوه، دسر و غذاهای سبک. مکان مناسبی برای تجربه‌ای آرامش‌بخش در دل بافت تاریخی شیراز.",
                    rating = 4,
                    imageResId = R.drawable.fath_olmolk,
                    address = "شیراز، پشت ارگ کریم‌خانی، خیابان ۲۲ بهمن، کوچه پارکینگ اتحاد",
                    telephone = "09173131542",
                    WorkingHours = "۱۰ صبح تا ۱۲ شب"
                ),
            )
            RestCaffeCard(
                rest_kafe(
                    title = "کافه موزه زرنگار",
                    description = "کافه‌ای هنری در فضای موزه‌ای با حال و هوای سنتی و محیطی آرامش‌بخش. مناسب برای صرف صبحانه، میان‌وعده و نوشیدنی‌های سنتی و مدرن در فضایی فرهنگی.",
                    rating = 4,
                    imageResId = R.drawable.zar_negar,
                    address = "شیراز، خیابان لطفعلی‌خان زند، کوچه ۳۳، پلاک ۱۴",
                    telephone = "09179355003",
                    WorkingHours = "۸ صبح تا ۵:۳۰ عصر"
                )
            )
            RestCaffeCard(
                rest_kafe(
                    title = "رستوران سنتی شرزه",
                    description = "یکی از قدیمی‌ترین و محبوب‌ترین رستوران‌های سنتی شیراز با فضای دل‌نشین ایرانی و موسیقی زنده. منوی متنوع شامل کباب، خورشت‌های سنتی و غذاهای محلی مثل کلم‌پلو.",
                    rating = 4,
                    imageResId = R.drawable.sherzeh,
                    address = "شیراز، خیابان لطفعلی‌خان زند، کنار مجموعه بازار وکیل",
                    telephone = "07132241963",
                    WorkingHours = "۱۲ ظهر تا ۱۱ شب"
                ),
            )
            RestCaffeCard(
                rest_kafe(
                    title = "رستوران کته ماس",
                    description = "رستورانی با حال و هوای صمیمی و غذاهای سنتی ایرانی با تمرکز ویژه بر غذاهای محلی شیراز مانند کلم‌پلو، شیرازی‌پلو و خورشت قورمه‌سبزی. کیفیت بالا و قیمت مناسب.",
                    rating = 4,
                    imageResId = R.drawable.kateh_mass,
                    address = "شیراز، سه‌راه نمازی، کوچه ۷",
                    telephone = "07132231818",
                    WorkingHours = "۸ صبح تا ۱۱ شب"
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

        }



    }
}

//@Preview(showBackground = true)
//@Composable
//fun TourPlacePreview() {
//    TourPlaceDetaP()
//}