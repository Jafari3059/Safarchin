package com.example.safarchin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.safarchin.ui.theme.Advertisement_logopage.AdvertisementScreen
import com.example.safarchin.ui.theme.Advertisement_logopage.logopage
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.TourPlaceDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.CityP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsResturantCaffe.RestCaffeDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailsResturantCaffe.ShopCenterDetaP
import com.example.safarchin.ui.theme.FourPageAsli.HomeScreen
import com.example.safarchin.ui.theme.login.login
import com.example.safarchin.ui.theme.login.codeLogin
import com.example.safarchin.ui.theme.SafarchinTheme
import kotlinx.coroutines.delay

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

    NavHost(navController = navController, startDestination = "firstLogo") {

        // صفحه لوگو اول
        composable("firstLogo") {
            FirstLogoPage(
                onNavigate = {
                    navController.navigate("advertisement1") {
                        popUpTo("firstLogo") { inclusive = true }
                    }
                }
            )
        }

        // تبلیغ اول
        composable("advertisement1") {
            AdvertisementScreenPage(
                imageResId = R.drawable.gift,
                title = "سفر فقط رفتن نیست... یه تجربه ست!",
                description = "با برنامه ریزی هوشمند، لحظه هات رو خاص کن.",
                indicatorState = listOf(true, false, false),
                onNext = { navController.navigate("advertisement2") },
                onSkip = { navController.navigate("login") }
            )
        }

        // تبلیغ دوم
        composable("advertisement2") {
            AdvertisementScreenPage(
                imageResId = R.drawable.gift,
                title = "برنامه ریزی ساده، سفر آسوده",
                description = "از انتخاب مقصد تا جاذبه‌های دیدنی، همراهتم!",
                indicatorState = listOf(false, true, false),
                onNext = { navController.navigate("advertisement3") },
                onSkip = { navController.navigate("login") }
            )
        }

        // تبلیغ سوم
        composable("advertisement3") {
            AdvertisementScreenPage(
                imageResId = R.drawable.gift,
                title = "سفرتو همینجا شروع کن!",
                description = "مقصدتو انتخاب کن، بقیش با ما!",
                indicatorState = listOf(false, false, true),
                onNext = { navController.navigate("login") },
                onSkip = { navController.navigate("login") }
            )
        }

        // صفحه لاگین
        composable("login") {
            login(navController = navController)
        }

        // صفحه کد ورود
        composable("codelogin") {
            codeLogin(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("cityDetail") {
            CityP(navController = navController) // یا CityP(cityViewModel.selectedCity.value) اگر ViewModel داری
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
