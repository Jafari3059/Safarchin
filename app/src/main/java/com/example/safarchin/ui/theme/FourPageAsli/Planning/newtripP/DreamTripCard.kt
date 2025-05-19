package com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

@Composable
fun DreamTripCard(modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val boxHeight = screenHeight * 0.22f
    val imageHeight = screenHeight * 0.11f
    val iconSize = screenWidth * 0.03f
    val titleFontSize = screenWidth.value.times(0.03).sp
    val subTextFontSize = screenWidth.value.times(0.028).sp

    Box(
        modifier = modifier
            .height(boxHeight)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.meydan_emam),
                contentDescription = null,
                modifier = Modifier
                    .height(imageHeight)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "سفر رویایی به اصفهان",
                fontFamily = iranSans,
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.End
            ) {
                InfoRow(icon = R.drawable.location, text = "اصفهان", fontSize = subTextFontSize, iconSize = iconSize)
                InfoRow(icon = R.drawable.companions, text = "3 نفر", fontSize = subTextFontSize, iconSize = iconSize)
                InfoRow(icon = R.drawable.money, text = "1,200,000", fontSize = subTextFontSize, iconSize = iconSize)
            }
        }

        Icon(
            painter = painterResource(id = R.drawable.editproficon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 16.dp)
                .size(28.dp),
            tint = Color.Gray
        )
    }
}

@Composable
private fun InfoRow(icon: Int, text: String, fontSize: TextUnit, iconSize: Dp) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text, fontSize = fontSize, color = Color.Gray)
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewDreamTripCard() {
    MaterialTheme {
        Surface {
            DreamTripCard(modifier = Modifier.padding(16.dp))
        }
    }
}
