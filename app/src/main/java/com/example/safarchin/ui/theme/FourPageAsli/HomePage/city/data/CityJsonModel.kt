package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data

import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.rest_kafe
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.shopCenter
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.detailstouristplaces.touristplaceJsonModel
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.spqati.SoqatiJsonModel


data class CityJsonModel(
    val name: String,
    val description: String,
    val imageRes: String,
    val latitude: Double,
    val longitude: Double,
    val touristPlaces: List<touristplaceJsonModel>,
    val shoppingCenters: List<shopCenter>,
    val souvenirs: List<SoqatiJsonModel>,
    val restaurants: List<rest_kafe>
)
