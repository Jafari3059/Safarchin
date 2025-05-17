package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data

import androidx.room.TypeConverter
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.Soqati
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.TourPlace
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CityTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<TourPlace> {
        val listType = object : TypeToken<List<TourPlace>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<TourPlace>): String {
        return gson.toJson(list)
    }
    @TypeConverter
    fun fromSoqatiList(value: String): List<Soqati> {
        val listType = object : TypeToken<List<Soqati>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toSoqatiList(list: List<Soqati>): String {
        return gson.toJson(list)
    }

    // مشابه همین برای shopCenter، Soqati، rest_kafe تعریف کن
}
