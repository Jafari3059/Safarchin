package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data

import android.content.Context
import com.example.safarchin.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace

fun loadCitiesFromAssets(context: Context): List<CityEntity> {
    val jsonString = context.assets.open("cities.json")
        .bufferedReader().use { it.readText() }

    val type = object : TypeToken<List<CityJsonModel>>() {}.type
    val cityJsonList: List<CityJsonModel> = Gson().fromJson(jsonString, type)

    return cityJsonList.map { jsonModel ->

        val resolvedSouvenirs = jsonModel.souvenirs.map { soqatiJson ->
            val resolvedImages = soqatiJson.imageResList.mapNotNull { getDrawableIdByName(context, it) }
            Soqati(
                name = soqatiJson.name,
                description = soqatiJson.description,
                imageResList = resolvedImages
            )
        }

        val resolvedTouristPlaces = jsonModel.touristPlaces.map { placeJson ->
            val resolvedImages = placeJson.imageResList.mapNotNull { getDrawableIdByName(context, it) }
            TourPlace(
                name = placeJson.name,
                description = placeJson.description,
                imageResList = resolvedImages,
                Visit_duration = placeJson.Visit_duration,
                Visit_price = placeJson.Visit_price,
                address = placeJson.address,
                telephone = placeJson.telephone,
                WorkingHours = placeJson.WorkingHours
            )
        }

        CityEntity(
            name = jsonModel.name,
            description = jsonModel.description,
            imageRes = getDrawableIdByName(context, jsonModel.imageRes) ?: R.drawable.profile_image,
            latitude = jsonModel.latitude,
            longitude = jsonModel.longitude,
            touristPlacesJson = Gson().toJson(resolvedTouristPlaces),
            shoppingCentersJson = Gson().toJson(jsonModel.shoppingCenters),
            souvenirsJson = Gson().toJson(resolvedSouvenirs),
            restaurantsJson = Gson().toJson(jsonModel.restaurants)
        )
    }
}

fun getDrawableIdByName(context: Context, name: String): Int? {
    val resId = context.resources.getIdentifier(name, "drawable", context.packageName)
    return if (resId != 0) resId else null
}
