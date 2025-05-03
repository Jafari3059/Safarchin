package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsSoqati


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.iranSans


@Composable
fun SoqatiCard(soqati: Soqati, modifier: Modifier = Modifier) {
    val cornerRadius = 12.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = (screenWidth * 0.35).dp

    Box(
        modifier = modifier
            .width(160.dp)
            .height(210.dp)
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 6.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        )  {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(RoundedCornerShape(11.dp))
            ) {
                Image(
                    painter = painterResource(id = soqati.imageRes),
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
fun SoqatiGrid() {
    val soqatiItems = listOf(
        Soqati("پوکّه", "پوکّه یا نان پوکه نوعی شیرینی سنتی شیراز است که خیلی خووووووووووشمزه است", R.drawable.khajo),
        Soqati("پوکّه", "پوکّه یا نان پوکه نوعی شیرینی سنتی شیراز است که...", R.drawable.shiraz)
    )

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
//            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        items(soqatiItems.chunked(2)) { rowItems ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (item in rowItems) {
                    SoqatiCard(soqati = item)
                }
                // برای جای خالی در ردیف ناقص
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.width(160.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSoqatiGrid() {
    SoqatiGrid()
}