package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

data class TourPlace(
    val name: String,
    val description: String,
    val imageResList: List<Int>,
    val Visit_duration: String,
    val Visit_price: String,
    val address: String,
    val telephone: String,
    val WorkingHours: String,
)

@Composable
fun TourPlcCard(place: TourPlace, navController: NavController, modifier: Modifier = Modifier) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp        // حدود 160dp روی موبایل
    val imageHeight = (screenWidth * 0.3).dp       // حدود 120dp
    val titleFontSize = (screenWidth * 0.03).sp    // حدود 12sp
    val descFontSize = (screenWidth * 0.025).sp    // حدود 10sp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(11.dp))
            ) {
                val imageResId = place.imageResList?.firstOrNull() ?: R.drawable.meydan_emam

                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "TourPlace Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = place.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = descFontSize,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TourCardList(items: List<TourPlace>, navController: NavController) {
    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) { item ->
            TourPlcCard(place = item, navController = navController)
        }
    }
}
//@Composable
//fun TourCardList() {
//    val cityList = listOf(
//        TourPlace(
//            name = "حافظیه",
//            description = "آرامگاه حافظ، شاعر بزرگ ایرانی، با معماری زیبا و فضای دل‌نشین، مکانی مناسب برای علاقه‌مندان به شعر و ادب فارسی است.",
//            imageRes = R.drawable.hafezieh,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۲۰٬۰۰۰ تومان",
//            address = "شیراز، بلوار حافظ",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۱۰ شب"
//        ),
//        TourPlace(
//            name = "سعدیه",
//            description = "آرامگاه سعدی، شاعر و حکیم بزرگ ایرانی، در باغی زیبا با فضای آرامش‌بخش واقع شده و محل مناسبی برای دوستداران ادبیات است.",
//            imageRes = R.drawable.saadiyeh,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۵٬۰۰۰ تومان",
//            address = "شیراز، انتهای خیابان بوستان",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "باغ دلگشا",
//            description = "یکی از قدیمی‌ترین باغ‌های شیراز با عمارت تاریخی و فضای سرسبز، مناسب برای گردش و استراحت در طبیعت.",
//            imageRes = R.drawable.bagh_delgosha,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۰٬۰۰۰ تومان",
//            address = "شیراز، خیابان بوستان، نزدیکی سعدیه",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "باغ نارنجستان قوام",
//            description = "باغی زیبا با عمارت تاریخی متعلق به دوره قاجار، با کاشی‌کاری‌ها و آینه‌کاری‌های هنرمندانه.",
//            imageRes = R.drawable.narenjestan_qavam,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۵٬۰۰۰ تومان",
//            address = "شیراز، خیابان لطفعلی‌خان زند",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "ارگ کریم‌خان",
//            description = "قلعه‌ای تاریخی از دوره زندیه با معماری خاص و برج‌های بلند، نماد قدرت و حکومت کریم‌خان زند.",
//            imageRes = R.drawable.arg_karimkhan,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۵٬۰۰۰ تومان",
//            address = "شیراز، میدان شهرداری",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "بازار وکیل",
//            description = "بازاری سنتی با معماری زیبا و فروشگاه‌های متنوع، مناسب برای خرید سوغات و صنایع دستی.",
//            imageRes = R.drawable.bazaar_vakil,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "رایگان",
//            address = "شیراز، خیابان لطفعلی‌خان زند",
//            telephone = "07132270007",
//            WorkingHours = "۹ صبح تا ۹ شب"
//        ),
//        TourPlace(
//            name = "دروازه قرآن",
//            description = "یکی از نمادهای تاریخی شیراز، دروازه‌ای قدیمی که در گذشته قرآن بر بالای آن قرار داشته است.",
//            imageRes = R.drawable.darvazeh_quran,
//            Visit_duration = "۳۰ دقیقه",
//            Visit_price = "رایگان",
//            address = "شیراز، ورودی شمال شرقی شهر",
//            telephone = "07132270007",
//            WorkingHours = "۲۴ ساعته"
//        ),
//        TourPlace(
//            name = "باغ عفیف‌آباد",
//            description = "باغی تاریخی با موزه نظامی و فضای سرسبز، مناسب برای علاقه‌مندان به تاریخ و طبیعت.",
//            imageRes = R.drawable.bagh_afifabad,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۵٬۰۰۰ تومان",
//            address = "شیراز، خیابان عفیف‌آباد",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "باغ جهان‌نما",
//            description = "یکی از قدیمی‌ترین باغ‌های شیراز با فضای دل‌نشین و معماری سنتی.",
//            imageRes = R.drawable.bagh_jahannama,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۰٬۰۰۰ تومان",
//            address = "شیراز، خیابان حافظ، نزدیکی حافظیه",
//            telephone = "07132270007",
//            WorkingHours = "۸ صبح تا ۸ شب"
//        ),
//        TourPlace(
//            name = "عمارت شاپوری",
//            description = "عمارت تاریخی با معماری خاص دوره پهلوی اول، شامل باغ و ساختمان زیبا.",
//            imageRes = R.drawable.amarat_shapouri,
//            Visit_duration = "۱ ساعت",
//            Visit_price = "۱۰٬۰۰۰ تومان",
//            address = "شیراز، خیابان انوری",
//            telephone = "07132270007",
//            WorkingHours = "۹ صبح تا ۹ شب"
//        )
//    )
//
//
//
//    LazyRow(
//        reverseLayout = true,
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//    ) {
//        items(cityList) { place ->
//            TourPlcCard(place)
//        }
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCityCards() {
//    TourCardList()
//}
