package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city

import android.net.Uri
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.iranSans

// ✅ تغییر: استفاده از لیست برای نگهداری چند تصویر
data class Soqati(
    val name: String,
    val description: String,
    val imageResList: List<Int>
)

@Composable
fun SoqatiCard(soqati: Soqati, navController: NavController) {
    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cardWidth = (screenWidth * 0.33).dp
    val imageHeight = (screenWidth * 0.3).dp
    val titleFontSize = (screenWidth * 0.025).sp
    val descFontSize = (screenWidth * 0.02).sp

    Box(
        modifier = Modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                viewModel.selectedSouvenir = soqati
                navController.navigate("souvenirDetail")
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
                    painter = painterResource(id = soqati.imageResList.firstOrNull() ?: R.drawable.meydan_emam),
                    contentDescription = "Soqati Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = soqati.name,
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = soqati.description,
                fontFamily = iranSans,
                fontWeight = FontWeight.Light,
                fontSize = descFontSize,
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
fun SoqatiList(items: List<Soqati>, navController: NavController) {
    LazyRow(
        reverseLayout = true,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items) { item ->
            SoqatiCard(soqati = item, navController = navController)
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun PreviewSoqatiCards() {
//    SoqatiList()
//}
