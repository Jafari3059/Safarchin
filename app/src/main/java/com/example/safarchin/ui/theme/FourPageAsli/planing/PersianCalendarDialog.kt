//package com.example.safarchin.ui.theme.FourPageAsli.planing
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.White
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.window.Dialog
//import com.example.safarchin.ui.theme.BlueMain
//import com.example.safarchin.ui.theme.White
//
//@Composable
//fun PersianCalendarDialog(
//    onDismiss: () -> Unit,
//    onSave: () -> Unit
//) {
//    Dialog(onDismissRequest = onDismiss) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFBCBCBC).copy(alpha = 0.63f)) // هاله مشکی نیمه‌شفاف پشت تقویم
//                .clickable(onClick = onDismiss)
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(width = 319.dp, height = 343.dp)
//                    .align(Alignment.Center)
//                    .clip(RoundedCornerShape(16.dp))
//                    .background(Color.White)
//            ) {
//                PersianCalendarContent(onSave = onSave)
//            }
//        }
//    }
//}
//
//@Composable
//fun PersianCalendarContent(
//    onSave: () -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        // جای تقویم اصلی (در مرحله بعدی پر میشه)
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//                .background(Color.LightGray)
//        ) {
//            Text(
//                text = "تقویم شمسی اینجا میاد",
//                fontSize = 16.sp,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//
//        // دکمه ذخیره
//        Box(
//            modifier = Modifier
//                .size(width = 126.dp, height = 51.dp)
//                .align(Alignment.CenterHorizontally)
//                .clip(RoundedCornerShape(12.dp))
//                .background(BlueMain)
//                .clickable(onClick = onSave),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = "ذخیره",
//                fontSize = 16.sp,
//                color = White
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PersianCalendarDialogPreview() {
//    Surface(color = MaterialTheme.colorScheme.background) {
//        PersianCalendarDialog(onDismiss = {}, onSave = {})
//    }
//}
//
//
