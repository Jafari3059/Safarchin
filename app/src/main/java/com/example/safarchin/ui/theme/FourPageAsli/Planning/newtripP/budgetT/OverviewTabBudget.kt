package com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.budgetT

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.SafarchinTheme
import java.text.NumberFormat
import java.util.*

@Composable
fun OverviewTabBudget(
    currentValue: Int,
    maxValue: Int,
    items: List<Pair<String, Long>>
) {
    val config = LocalConfiguration.current
    val screenWidthDp = config.screenWidthDp.dp
    val screenHeightDp = config.screenHeightDp.dp

    val percentage = currentValue.toFloat() / maxValue.toFloat()
    val animatedSweep by animateFloatAsState(
        targetValue = percentage * 180f,
        animationSpec = tween(durationMillis = 1000), label = "arcAnim"
    )

    val (color, message) = when {
        percentage < 0.4f -> Pair(Color(0xFF09830E), "بودجه‌بندی عالیه")
        percentage in 0.4f..0.8f -> Pair(Color(0xFFFBAD0C), "نصف بودجه مصرف شده")
        else -> Pair(Color(0xFFED2E2E), "خیلی خرج کردی")
    }

    val iconRes = when {
        percentage < 0.4f -> R.drawable.tikicon
        percentage in 0.4f..0.8f -> R.drawable.khataricon
        else -> R.drawable.warningicon
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = screenHeightDp * 0.01f, bottom = screenHeightDp * 0.12f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // باکس بودجه نیم‌دایره‌ای
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally)
                .height(285.dp)
                .padding(vertical = 12.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFFEDEDED), shape = RoundedCornerShape(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .size(width = screenWidthDp * 0.9f, height = 216.dp)
                    .align(Alignment.Center)
            ) {
                Canvas(
                    modifier = Modifier
                        .size(200.dp)
                        .offset(y = 60.dp)
                        .align(Alignment.TopCenter)
                ) {
                    drawArc(
                        color = Color(0xFFD9D9D9),
                        startAngle = 180f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(width = 60f, cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = color,
                        startAngle = 180f,
                        sweepAngle = animatedSweep,
                        useCenter = false,
                        style = Stroke(width = 60f, cap = StrokeCap.Round)
                    )
                }

                val formattedMaxValue = remember(maxValue) {
                    NumberFormat.getNumberInstance(Locale("fa")).format(maxValue.toInt())
                }

                Text(
                    text = formattedMaxValue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-90).dp)
                )



                Text(
                    text = "مانده",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF0C0C0C),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = -5.dp)
                )

                Text(
                    text = "%,d".format(currentValue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = 0.dp, y = 30.dp)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = -10.dp)
                        .padding(horizontal = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = message,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = color
                    )
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 4.dp),
                    )
                }
            }
        }

        // لیست آیتم‌ها
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { (label, amount) ->
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    BudgetItemRow(label = label, amount = amount)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun BudgetItemRow(label: String, amount: Long) {
    val config = LocalConfiguration.current
    val screenWidthDp = config.screenWidthDp.dp

    val formattedAmount = remember(amount) {
        NumberFormat.getNumberInstance(Locale("fa")).format(amount)
    }

    val iconRes = when (label) {
        "خرید" -> R.drawable.kharid
        "اقامتگاه" -> R.drawable.eghamat
        "خوردنی" -> R.drawable.khordani
        "تفریح" -> R.drawable.tafrih
        "حمل و نقل" -> R.drawable.hamlonaghl
        "متفرقه" -> R.drawable.motafareghe
        else -> R.drawable.khataricon
    }

    Row(
        modifier = Modifier
            .width(screenWidthDp * 0.9f)
            .height(61.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFEDEDED), shape = RoundedCornerShape(8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "تومان",
                color = Color(0xFF353535),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = formattedAmount,
                color = Color(0xFF353535),
                fontSize = 14.sp
            )
        }

        Row(
            modifier = Modifier.padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OverviewTabBudgetPreview() {
    SafarchinTheme {
        OverviewTabBudget(
            currentValue = 800000,
            maxValue = 900000,
            items = listOf(
                "خرید" to 850000,
                "اقامتگاه" to 500000,
                "خوردنی" to 10000,
                "تفریح" to 2000,
                "حمل و نقل" to 10000,
                "متفرقه" to 100000
            )
        )
    }
}
