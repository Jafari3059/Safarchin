package com.example.safarchin.ui.theme.FourPageAsli.Planning.overviewP.Saved

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// دیتا مدل مکان ذخیره شده
data class SavedPlace(
    val id: Int,
    val name: String,
    val imageRes: Int
)

data class SelectedPlace(
    val id: Int,
    val name: String,
    val imageRes: Int
)

// ویو مدل لیست مکان‌های ذخیره شده
class SavedPlacesViewModel : ViewModel() {
    val savedPlaces = mutableStateListOf<SavedPlace>()

    fun addPlace(place: SavedPlace) {
        if (savedPlaces.none { it.id == place.id }) {
            savedPlaces.add(place)
        }
    }

    fun removePlace(placeId: Int) {
        savedPlaces.removeAll { it.id == placeId }
    }
}
