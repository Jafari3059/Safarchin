package com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.City


@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val imageRes: Int,
    val latitude: Double,
    val longitude: Double,
    val touristPlacesJson: String,
    val shoppingCentersJson: String,
    val souvenirsJson: String,
    val restaurantsJson: String
)
fun CityEntity.toCity(): City {
    return City(
        name = name,
        description = description,
        imageRes = imageRes,
        latitude = latitude,
        longitude = longitude,
        touristPlaces = emptyList(),
        shoppingCenters = emptyList(),
        souvenirs = emptyList(),
        restaurants = emptyList()
    )
}
