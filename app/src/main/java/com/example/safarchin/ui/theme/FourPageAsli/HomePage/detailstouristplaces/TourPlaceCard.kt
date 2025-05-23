package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces


import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
//import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlcCard
import com.example.safarchin.ui.theme.iranSans

@Composable
fun TourPlaceCard(
    place: TourPlace,
    showRemove: Boolean = false,
    onRemoveClick: (() -> Unit)? = null
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val cardHeight = (screenHeight * 0.29).dp
    val imageWidth = (screenWidth * 0.35).dp
    val titleFontSize = (screenWidth * 0.035).sp
    val descFontSize = (screenWidth * 0.028).sp
    val iconSize = (screenWidth * 0.04).dp
    val iconSaveSize = (screenWidth * 0.055).dp

    val paddingSize = (screenWidth * 0.025).dp
    val minDescriptionHeight = (descFontSize.value * 1.2f * 7).dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = place.imageRes),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(imageWidth)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = place.name,
                    fontFamily = iranSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = titleFontSize,
                    color = Color.Black,
                    textAlign = TextAlign.Right
                )

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Text(
                        text = place.description,
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Light,
                        fontSize = descFontSize,
                        color = Color.Black,
                        textAlign = TextAlign.Justify,
                        lineHeight = descFontSize * 1.4,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = minDescriptionHeight)
                    )
                }

                InfoRow(icon = R.drawable.clock2, text = "مدت بازدید: ${place.Visit_duration}", iconSize)
                InfoRow(icon = R.drawable.money2, text = "هزینه بازدید: ${place.Visit_price}", iconSize)
                InfoRow(icon = R.drawable.location, text = "آدرس: ${place.address}", iconSize)
                InfoRow(icon = R.drawable.clock, text = "ساعت کاری: ${place.WorkingHours}", iconSize)
                InfoRow(icon = R.drawable.phone, text = "تماس: ${place.telephone}", iconSize)

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "افزودن سفر",
                        tint = Color(0xFFBEBAB3),
                        modifier = Modifier.size(iconSaveSize)
                    )

                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFB26B), RoundedCornerShape(8.dp))
                            .clickable(enabled = showRemove, onClick = { onRemoveClick?.invoke() })
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = if (showRemove) "حذف از منتخبین" else "توضیحات بیشتر",
                            fontSize = (screenWidth * 0.025).sp,
                            color = Color.White,
                            fontFamily = iranSans
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun InfoRow(icon: Int, text: String, iconSize: Dp) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontFamily = iranSans,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Right,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFFFFB26B),
            modifier = Modifier.size(iconSize)
        )
    }
}


@Composable
fun TourPlaceList() {
    val cityList = listOf(
        TourPlace(
            name = "حافظیه",
            description = "آرامگاه حافظ، شاعر بزرگ ایرانی، با معماری زیبا و فضای دل‌نشین، مکانی مناسب برای علاقه‌مندان به شعر و ادب فارسی است.",
            imageRes = R.drawable.hafezieh,
            Visit_duration = "۱ ساعت",
            Visit_price = "۲۰٬۰۰۰ تومان",
            address = "شیراز، بلوار حافظ",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۱۰ شب"
        ),
        TourPlace(
            name = "سعدیه",
            description = "آرامگاه سعدی، شاعر و حکیم بزرگ ایرانی، در باغی زیبا با فضای آرامش‌بخش واقع شده و محل مناسبی برای دوستداران ادبیات است.",
            imageRes = R.drawable.saadiyeh,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۵٬۰۰۰ تومان",
            address = "شیراز، انتهای خیابان بوستان",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "باغ دلگشا",
            description = "یکی از قدیمی‌ترین باغ‌های شیراز با عمارت تاریخی و فضای سرسبز، مناسب برای گردش و استراحت در طبیعت.",
            imageRes = R.drawable.bagh_delgosha,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۰٬۰۰۰ تومان",
            address = "شیراز، خیابان بوستان، نزدیکی سعدیه",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "باغ نارنجستان قوام",
            description = "باغی زیبا با عمارت تاریخی متعلق به دوره قاجار، با کاشی‌کاری‌ها و آینه‌کاری‌های هنرمندانه.",
            imageRes = R.drawable.narenjestan_qavam,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۵٬۰۰۰ تومان",
            address = "شیراز، خیابان لطفعلی‌خان زند",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "ارگ کریم‌خان",
            description = "قلعه‌ای تاریخی از دوره زندیه با معماری خاص و برج‌های بلند، نماد قدرت و حکومت کریم‌خان زند.",
            imageRes = R.drawable.arg_karimkhan,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۵٬۰۰۰ تومان",
            address = "شیراز، میدان شهرداری",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "بازار وکیل",
            description = "بازاری سنتی با معماری زیبا و فروشگاه‌های متنوع، مناسب برای خرید سوغات و صنایع دستی.",
            imageRes = R.drawable.bazaar_vakil,
            Visit_duration = "۱ ساعت",
            Visit_price = "رایگان",
            address = "شیراز، خیابان لطفعلی‌خان زند",
            telephone = "07132270007",
            WorkingHours = "۹ صبح تا ۹ شب"
        ),
        TourPlace(
            name = "دروازه قرآن",
            description = "یکی از نمادهای تاریخی شیراز، دروازه‌ای قدیمی که در گذشته قرآن بر بالای آن قرار داشته است.",
            imageRes = R.drawable.darvazeh_quran,
            Visit_duration = "۳۰ دقیقه",
            Visit_price = "رایگان",
            address = "شیراز، ورودی شمال شرقی شهر",
            telephone = "07132270007",
            WorkingHours = "۲۴ ساعته"
        ),
        TourPlace(
            name = "باغ عفیف‌آباد",
            description = "باغی تاریخی با موزه نظامی و فضای سرسبز، مناسب برای علاقه‌مندان به تاریخ و طبیعت.",
            imageRes = R.drawable.bagh_afifabad,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۵٬۰۰۰ تومان",
            address = "شیراز، خیابان عفیف‌آباد",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "باغ جهان‌نما",
            description = "یکی از قدیمی‌ترین باغ‌های شیراز با فضای دل‌نشین و معماری سنتی.",
            imageRes = R.drawable.bagh_jahannama,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۰٬۰۰۰ تومان",
            address = "شیراز، خیابان حافظ، نزدیکی حافظیه",
            telephone = "07132270007",
            WorkingHours = "۸ صبح تا ۸ شب"
        ),
        TourPlace(
            name = "عمارت شاپوری",
            description = "عمارت تاریخی با معماری خاص دوره پهلوی اول، شامل باغ و ساختمان زیبا.",
            imageRes = R.drawable.amarat_shapouri,
            Visit_duration = "۱ ساعت",
            Visit_price = "۱۰٬۰۰۰ تومان",
            address = "شیراز، خیابان انوری",
            telephone = "07132270007",
            WorkingHours = "۹ صبح تا ۹ شب"
        )
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(cityList) { place ->
            TourPlaceCard(place = place)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewsdfvSoqatiCards() {
    TourPlaceList()
}