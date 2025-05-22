package com.example.safarchin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safarchin.ui.theme.Advertisement_logopage.AdvertisementScreen
import com.example.safarchin.ui.theme.Advertisement_logopage.logopage
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.TourPlaceDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.CityP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop.ShopCenterDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsResturantCaffe.RestCaffeDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsCentershop.SoqatiDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.spqati.Souvenir.SouvenirDetailScreen
import com.example.safarchin.ui.theme.FourPageAsli.HomeScreen
import com.example.safarchin.ui.theme.login.login
import com.example.safarchin.ui.theme.login.codeLogin
import com.example.safarchin.ui.theme.SafarchinTheme
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext
import com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.OverviewScreen
import com.example.safarchin.ui.theme.FourPageAsli.Planning.planingP
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafarchinTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = DatabaseProvider.getDatabase(context)

    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val user = withContext(Dispatchers.IO) {
            db.userDao().getAnyUser()
        }

        startDestination = if (user != null) {
            "homeScreen/${user.phone}"
        } else {
            "firstLogo"
        }
    }

    startDestination?.let { destination ->
        NavHost(navController = navController, startDestination = destination) {
            composable("firstLogo") {
                FirstLogoPage(
                    onNavigate = {
                        navController.navigate("advertisement1") {
                            popUpTo("firstLogo") { inclusive = true }
                        }
                    }
                )
            }

            composable("advertisement1") {
                AdvertisementScreenPage(
                    imageResId = R.drawable.ad1,
                    title = "سفر فقط رفتن نیست... یه تجربه ست!",
                    description = "با برنامه ریزی هوشمند، لحظه هات رو خاص کن.",
                    indicatorState = listOf(true, false, false),
                    onNext = { navController.navigate("advertisement2") },
                    onSkip = { navController.navigate("login") }
                )
            }

            composable("advertisement2") {
                AdvertisementScreenPage(
                    imageResId = R.drawable.adv2,
                    title = "برنامه ریزی ساده، سفر آسوده",
                    description = "از انتخاب مقصد تا جاذبه‌های دیدنی، همراهتم!",
                    indicatorState = listOf(false, true, false),
                    onNext = { navController.navigate("advertisement3") },
                    onSkip = { navController.navigate("login") }
                )
            }

            composable("advertisement3") {
                AdvertisementScreenPage(
                    imageResId = R.drawable.adv3,
                    title = "سفرتو همینجا شروع کن!",
                    description = "مقصدتو انتخاب کن، بقیش با ما!",
                    indicatorState = listOf(false, false, true),
                    onNext = { navController.navigate("login") },
                    onSkip = { navController.navigate("login") }
                )
            }

            composable("login") {
                login(navController = navController)
            }

            composable("codelogin/{phone}") { backStackEntry ->
                val phone = backStackEntry.arguments?.getString("phone") ?: ""
                codeLogin(navController, phone)
            }

            composable("homeScreen/{phone}") { backStackEntry ->
                val phone = backStackEntry.arguments?.getString("phone") ?: ""
                HomeScreen(navController = navController, phone = phone)
            }

            composable("cityDetail") {
                CityP(navController = navController)
            }

            composable("tourDetails") {
                TourPlaceDetaP(navController = navController)
            }

            composable("RestDetails") {
                RestCaffeDetaP(navController = navController)
            }

            composable("shopDetails") {
                ShopCenterDetaP(navController = navController)
            }

            composable("soqatiDetails") {
                SoqatiDetaP(navController = navController)
            }

            composable("planing") {
                planingP(navController = navController)
            }

            composable("overview") {
                OverviewScreen(navController = navController)
            }

            composable("SouvenirDetailScreen") {
                SouvenirDetailScreen(navController = navController)
            }

        }
    }
}

@Composable
fun FirstLogoPage(onNavigate: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(5000L)
        onNavigate()
    }

    logopage(onNavigateToLogin = { onNavigate() })
}

@Composable
fun AdvertisementScreenPage(
    imageResId: Int,
    title: String,
    description: String,
    indicatorState: List<Boolean>,
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    AdvertisementScreen(
        imageResId = imageResId,
        title = title,
        description = description,
        indicatorState = indicatorState,
        onNext = onNext,
        onSkip = onSkip
    )
}