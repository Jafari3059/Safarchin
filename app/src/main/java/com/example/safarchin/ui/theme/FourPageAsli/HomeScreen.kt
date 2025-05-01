package com.example.safarchin.ui.theme.FourPageAsli

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.safarchin.ui.theme.FourPageAsli.Eshterak.EshterakP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.HomeP
import com.example.safarchin.ui.theme.FourPageAsli.Profile.profileP
import com.example.safarchin.ui.theme.FourPageAsli.planing.planingP


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    var selectedIndex by remember { mutableStateOf(3) }
    var showDialog by remember { mutableStateOf(false) } // ✅ اینجا کنترل پاپ‌آپ

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var isDrawerOpen by remember { mutableStateOf(false) }


    Box(modifier = Modifier.
    fillMaxSize()
        .background(Color(0xFFF6F4F4))) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(selectedIndex) { index ->
                    selectedIndex = index
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // ⬇ صفحه‌ها
                when (selectedIndex) {
                    0 -> profileP()
                    1 -> EshterakP()
                    2 -> planingP(navController)
                    3 -> HomeP()
                }
            }
        }

        // ⬇ لایه‌ی محو و دیالوگ در بالاترین سطح
        if (showDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .clickable(enabled = true, onClick = {}) // جلوگیری از کلیک زیر
                    .zIndex(1f),
                contentAlignment = Alignment.Center
            ) {
//                UnavailableDialog(onDismiss = { showDialog = false })
            }
        }


    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginbPreview() {
////    val navController = rememberNavController()
//    HomeScreen()
//}
