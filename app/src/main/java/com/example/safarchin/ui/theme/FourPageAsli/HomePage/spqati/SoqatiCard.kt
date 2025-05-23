package com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.SharedViewModel
import com.example.safarchin.ui.theme.iranSans
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun SoqatiCard(soqati: Soqati, navController: NavController, modifier: Modifier = Modifier) {

    val viewModel = viewModel<SharedViewModel>(viewModelStoreOwner = LocalContext.current as androidx.lifecycle.ViewModelStoreOwner)
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val imageHeight = (screenWidth * 0.35).dp
    viewModel.selectedSouvenir = soqati

    Box(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                navController.navigate("SouvenirDetailScreen")
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
