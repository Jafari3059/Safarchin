package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.iranSans

@Composable
fun SoqatiCard(soqati: Soqati, navController: NavController, modifier: Modifier = Modifier) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = (screenWidth * 0.35).dp

    Box(
        modifier = modifier
            .width(160.dp)
            .height(210.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                val imageParam = soqati.imageResList.joinToString(",")
                navController.navigate(
                    "SouvenirDetailScreen/" +
                            Uri.encode(soqati.name) + "/" +
                            Uri.encode(soqati.description) + "/" +
                            imageParam
                )
            }
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
                    painter = painterResource(id = soqati.imageResList.firstOrNull() ?: R.drawable.shiraz),
                    contentDescription = "Soqati Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = soqati.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = Color.Black,
                textAlign = TextAlign.Right,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = soqati.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
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
fun SoqatiGrid(navController: NavController) {
    val soqatiItems = listOf(
        Soqati(
            "پوکّه",
            "پوکّه یا نان پوکه نوعی شیرینی سنتی شیراز است که خیلی خووووووووووشمزه است",
            listOf(R.drawable.khajo, R.drawable.shiraz)
        ),
        Soqati(
            "فالوده شیرازی",
            "دسر سنتی با طعمی خنک و شیرین که با لیمو ترش سرو می‌شود.",
            listOf(R.drawable.shiraz, R.drawable.meydan_emam)
        )
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(soqatiItems.chunked(2)) { rowItems ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                rowItems.forEach { item ->
                    SoqatiCard(soqati = item, navController = navController)
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.width(160.dp))
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSoqatiGrid() {
//    SoqatiGrid()
//}