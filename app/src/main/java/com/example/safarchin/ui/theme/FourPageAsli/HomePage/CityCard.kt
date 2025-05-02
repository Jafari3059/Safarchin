package com.example.safarchin.ui.theme.FourPageAsli.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.rest_kafe
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.shopCenter
import com.example.safarchin.ui.theme.iranSans
import com.example.safarchin.ui.theme.irgitiFont

data class City(
    val name: String,
    val description: String,
    val imageRes: Int,
    val location: Int,
    val touristPlaces: List<TourPlace>,
    val shoppingCenters: List<shopCenter>,
    val souvenirs: List<Soqati>,
    val restaurants: List<rest_kafe>

)

@Composable
fun CityCard(city: City, onClick: () -> Unit){

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp
    val cardImageHeight = (screenWidth * 0.3).dp
    val titleFontSize = (screenWidth * 0.025).sp
    val descriptionFontSize = (screenWidth * 0.022).sp
    val imageTextFontSize = (screenWidth * 0.06).sp
    val paddingInside = (screenWidth * 0.015).dp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onClick() }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingInside, vertical = paddingInside),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardImageHeight)
                    .clip(RoundedCornerShape(11.dp))
            ) {
                Image(
                    painter = painterResource(id = city.imageRes),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Text(
                    text = city.name,
                    fontFamily = irgitiFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = imageTextFontSize,
                    color = Color.White,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0f, 0f),
                            blurRadius = 10f
                        )
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = paddingInside)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = city.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = city.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = descriptionFontSize,
                color = Color.Gray,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CityCardList(navToCityScreen: (City) -> Unit) {
    val cityList = listOf(
        City(
            name = "شیراز",
            description = "شهر زیبای شیراز در ۲۰ کیلشهر تاریخی اصفهان با معماریشهر تاریخی اصفهان با معماریومتری فارس واقع شده است.",
            imageRes = R.drawable.shiraz,
            location = 1,
            touristPlaces = emptyList(),
            shoppingCenters = emptyList(),
            souvenirs = emptyList(),
            restaurants = emptyList()
        ),

        City(
            name = "اصفهان",
            description = "شهر تاریخی شهر تاریخی اصفهان با معماریشهر تاریخی اصفهان با معماریاصفهان با معماری بی‌نظیر.",
            imageRes = R.drawable.shiraz,
            location = 2,
            touristPlaces = emptyList(),
            shoppingCenters = emptyList(),
            souvenirs = emptyList(),
            restaurants = emptyList()
        ),

        City(
            name = "تبریز",
            description = "شهر اولین‌ها در شمال‌غرب sdcjdc pwdcjn DKFCشهر تاریخی اصفهان با معماریJ epfjایران.",
            imageRes = R.drawable.shiraz,
            location = 3,
            touristPlaces = emptyList(),
            shoppingCenters = emptyList(),
            souvenirs = emptyList(),
            restaurants = emptyList()
        )
    )


    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(cityList) { city ->
            CityCard(city = city, onClick = { navToCityScreen(city) })
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCityCards() {
//    CityCardList()
//}
