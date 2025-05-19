package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati

fun loadCitiesFromAssets(context: Context): List<CityEntity> {

    val jsonString = context.assets.open("cities.json")
        .bufferedReader()
        .use { it.readText() }

    val type = object : TypeToken<List<CityJsonModel>>() {}.type
    val cityJsonList: List<CityJsonModel> = Gson().fromJson(jsonString, type)

    return cityJsonList.map { jsonModel ->

        // تبدیل نام عکس‌های سوغاتی به لیست resource ID
        val resolvedSouvenirs = jsonModel.souvenirs.map { soqatiJson ->
            val resolvedImages: List<Int> = soqatiJson.imageResList.map { name ->
                getDrawableIdByName(context, name)
            }
            Soqati(
                name = soqatiJson.name,
                description = soqatiJson.description,
                imageResList = resolvedImages
            )
        }



        CityEntity(
            name = jsonModel.name,
            description = jsonModel.description,
            imageRes = getDrawableIdByName(context, jsonModel.imageRes),
            latitude = jsonModel.latitude,
            longitude = jsonModel.longitude,
            touristPlacesJson = Gson().toJson(jsonModel.touristPlaces),
            shoppingCentersJson = Gson().toJson(jsonModel.shoppingCenters),
            souvenirsJson = Gson().toJson(resolvedSouvenirs),
            restaurantsJson = Gson().toJson(jsonModel.restaurants)
        )
    }
}

fun getDrawableIdByName(context: Context, name: String): Int {
    return context.resources.getIdentifier(name, "drawable", context.packageName)
}
