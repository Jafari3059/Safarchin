package com.example.safarchin.ui.theme.FourPageAsli.planing.overviewP
// SuggestedPlacesSection.kt

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans

@Composable
fun SuggestedPlacesSection() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    // سایزهای متناسب با اندازه صفحه
    val boxWidth = screenWidth * 0.90f
    val cardWidth = screenWidth * 0.28f
    val cardHeight = screenHeight * 0.18f
    val imageHeight = screenHeight * 0.1f
    val buttonWidth = screenWidth * 0.22f
    val buttonHeight = screenHeight * 0.035f
    val fontSize = screenWidth.value.times(0.028).sp

    Box(
        modifier = Modifier
            .width(boxWidth)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "مکان‌های پیشنهادی",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = iranSans
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    painter = painterResource(id = R.drawable.location2),
                    contentDescription = null,
                    tint = Color(0xFF939B62),
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
            ) {
                listOf(
                    "مسجد نصیرالملک" to R.drawable.shiraz,
                    "مسجد نصیرالملک" to R.drawable.shiraz,
                    "باغ ارم" to R.drawable.shiraz
                ).forEach { (title, imageRes) ->
                    Card(
                        modifier = Modifier
                            .width(cardWidth)
                            .height(cardHeight)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(6.dp)),
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(cardWidth * 0.85f)
                                    .height(imageHeight)
                                    .clip(RoundedCornerShape(15.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = title,
                                fontSize = fontSize,
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.End).padding(end = 8.dp),
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF939B62)),
                                modifier = Modifier
                                    .width(buttonWidth)
                                    .height(buttonHeight),
                                shape = RoundedCornerShape(6.dp),
                                contentPadding = PaddingValues(horizontal = 4.dp)
                            ) {
                                Text(
                                    text = "اضافه کردن به برنامه",
                                    fontSize = fontSize.times(0.85f),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
@Preview(showBackground = true, locale = "fa")
@Composable
fun PreviewSuggestedPlacesSection() {
    MaterialTheme {
        Surface {
            SuggestedPlacesSection()
        }
    }
}