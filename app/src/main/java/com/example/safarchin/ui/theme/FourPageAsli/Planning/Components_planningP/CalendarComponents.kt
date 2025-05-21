package com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// تقویم
@Composable
fun CalendarDialog(
    onDismissRequest: () -> Unit,
    onSaveClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBCBCBC).copy(alpha = 0.63f))
            .clickable(onClick = onDismissRequest),     // برای بستن با کلیک بیرون
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(320.dp)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable(enabled = false) {} // جلوگیری از کلیک روی خود باکس
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7043) // رنگ دکمه ذخیره
                    )
                ) {
                    Text("ذخیره", color = Color.White)
                }
            }
        }
    }
}
//تقویم
@Composable
fun PersianCalendarDialog(
    onDismiss: () -> Unit,
    onSave: (start: String, end: String?) -> Unit
) {
    // سایه‌ی پشت تقویم
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBCBCBC).copy(alpha = 0.63f))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        PersianCalendarContent(
            onSave = { start, end -> onSave(start, end) },
            onDismiss = onDismiss
        )
    }
}
// تقویم
@Composable
fun PersianCalendarContent(
    onSave: (start: String, end: String?) -> Unit,
    onDismiss: () -> Unit
) {
    // فرضی: بعدا با دیتای واقعی جایگزین کن
    var selectedStartDate by remember { mutableStateOf("۱۴۰۳/۰۲/۱۲") }
    var selectedEndDate by remember { mutableStateOf("۱۴۰۳/۰۲/۲۰") }

    Box(
        modifier = Modifier
            .size(width = 319.dp, height = 343.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable(enabled = false) {}, // نذاره کلیک از بین بره
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Text("انتخاب تاریخ", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(16.dp))

            // اینجا بعدا تقویم واقعی رو جایگزین کن
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFF2F2F2)),
                contentAlignment = Alignment.Center
            ) {
                Text("نمایش تقویم شمسی")
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { onSave(selectedStartDate, selectedEndDate) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0066FF)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(126.dp)
                    .height(51.dp)
            ) {
                Text("ذخیره", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
