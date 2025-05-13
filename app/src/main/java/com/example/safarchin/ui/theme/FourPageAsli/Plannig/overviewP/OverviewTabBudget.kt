package com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP

import android.media.Image
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.safarchin.R
import com.example.safarchin.ui.theme.SafarchinTheme
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun OverviewTabBudget(currentValue: Int, maxValue: Int) {
    val percentage = currentValue.toFloat() / maxValue.toFloat()
    val animatedSweep by animateFloatAsState(
        targetValue = percentage * 180f,
        animationSpec = tween(durationMillis = 1000), label = "arcAnim"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(285.dp)
            .padding(horizontal = 41.dp, vertical = 12.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFEDEDED), shape = RoundedCornerShape(16.dp))
    ) {
        Box(
            modifier = Modifier
                .size(width = 320.dp, height = 216.dp)
                .align(Alignment.Center)
        ) {
            // Canvas نیم‌دایره‌ای
            Canvas(
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = 60.dp)
                    .align(Alignment.TopCenter)
            ) {
                // زمینه خاکستری
                drawArc(
                    color = Color(0xFFD9D9D9),
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    style = Stroke(width = 60f, cap = StrokeCap.Round)
                )
                // بخش سبز داینامیک
                drawArc(
                    color = Color(0xFF09830E),
                    startAngle = 180f,
                    sweepAngle = animatedSweep,
                    useCenter = false,
                    style = Stroke(width = 60f, cap = StrokeCap.Round)
                )
            }

            // مقدار کل
            Text(
                text = "%,d".format(maxValue),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 130.dp)
                    .offset(y = (-90).dp)
            )

            // متن "مانده"
            Text(
                text = "مانده",
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF0C0C0C),
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = -5.dp)
            )

            // مقدار مانده
            Text(
                text = "%,d".format(currentValue),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = 0.dp, y = 30.dp)
            )

            // نوشته سبز پایین
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -10.dp) // فاصله از پایین
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // بارگذاری آیکون از فایل jpg
                Image(
                    painter = painterResource(id = R.drawable.tikicon), // نام فایل شما (در اینجا "icon.jpg")
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp) // اندازه آیکون 16*16
                        .padding(end = 4.dp), // فاصله بین آیکون و متن
                )

                // متن
                Text(
                    text = "بودجه‌بندی عالیه",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF09830E)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun OverviewTabBudgetPreview() {
    SafarchinTheme {
        OverviewTabBudget(currentValue = 450000, maxValue = 900000)
    }
}
