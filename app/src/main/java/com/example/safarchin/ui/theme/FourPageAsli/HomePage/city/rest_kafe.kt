package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

data class rest_kafe(
    val title: String,
    val description: String,
    val rating: Int = 5,
    val imageResId: Int,
    val address: String,
    val telephone: String,
    val WorkingHours: String,
)

@Composable
fun RestKafeCard(place: rest_kafe) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp      // حدود 160dp روی موبایل
    val imageHeight = (screenWidth * 0.3).dp    // حدود 120dp
    val iconSize = (screenWidth * 0.03).dp      // حدود 10dp
    val titleFontSize = (screenWidth * 0.025).sp
    val descFontSize = (screenWidth * 0.02).sp

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
                .padding(horizontal = 6.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(11.dp))
            ) {
                Image(
                    painter = painterResource(id = place.imageResId),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = place.title,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Right
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                repeat(place.rating) {
                    Icon(
                        painter = painterResource(id = R.drawable.fullstar),
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(iconSize)
                    )
                }
            }

            Text(
                text = place.description,
                fontFamily = iranSans,
                fontSize = descFontSize,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                textAlign = TextAlign.Right,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RestKafeSection() {
    val restList = listOf(
        rest_kafe(
            "رستوران هفت‌خوان",
            "مجموعه‌ای لوکس با ۵ رستوران و ۲ کافی‌شاپ، ارائه‌دهنده انواع غذاهای ایرانی، فرنگی و فست‌فود در فضایی با معماری الهام‌گرفته از شاهنامه.",
            5,
            R.drawable.haft_khan,
            "شیراز، بلوار جدید قرآن، نبش کوچه هفدهم",
            "09173131542",
            "۷ صبح تا ۱۱:۳۰ شب"
        ),
        rest_kafe(
            "رستوران سنتی شرزه",
            "یکی از قدیمی‌ترین رستوران‌های شیراز با فضای سنتی و منوی متنوع از غذاهای ایرانی، مخصوصاً کباب‌های متنوع.",
            4,
            R.drawable.sherzeh,
            "شیراز، مجموعه وکیل، زیرگذر زند",
            "09173131542",
            "۱۲ ظهر تا ۱۱ شب"
        ),
        rest_kafe(
            "رستوران کته ماس",
            "ارائه‌دهنده غذاهای خاص ایرانی مانند کته کنجه، جوجه لاری و کلم‌پلو در محیطی سنتی و دل‌نشین.",
            4,
            R.drawable.kateh_mass,
            "شیراز، خیابان لطفعلی‌خان زند، سه‌راه نمازی",
            "09173131542",
            "۸ صبح تا ۱۱ شب"
        ),
        rest_kafe(
            "کافه عمارت فتح‌الملوکی",
            "کافه‌ای با فضای سنتی و طراحی نوستالژیک، واقع در عمارت تاریخی با منوی متنوع از نوشیدنی‌ها و غذاهای سنتی.",
            4,
            R.drawable.fath_olmolk,
            "شیراز، پشت ارگ کریم‌خانی، خیابان ۲۲ بهمن، کوچه پارکینگ اتحاد",
            "09173131542",
            "۱۰ صبح تا ۱۲ شب"
        ),
        rest_kafe(
            "کافه موزه زرنگار",
            "کافه‌ای در عمارت تاریخی با حیاطی دل‌نشین و منوی متنوع از نوشیدنی‌ها و دسرهای خوشمزه.",
            4,
            R.drawable.zar_negar,
            "شیراز، خیابان لطفعلی‌خان زند، کوچه ۳۳، پلاک ۱۴",
            "09173131542",
            "۸ صبح تا ۵:۳۰ عصر"
        )
    )


    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(restList) { item ->
            RestKafeCard(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRestKafe() {
    RestKafeSection()
}
