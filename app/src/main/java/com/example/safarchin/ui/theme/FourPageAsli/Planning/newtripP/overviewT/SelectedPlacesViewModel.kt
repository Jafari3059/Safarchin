package com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.overviewT

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.safarchin.ui.theme.FourPageAsli.Planning.newtripP.overviewT.Saved.SavedPlace

class SelectedPlacesViewModel : ViewModel() {
    val selectedPlaces = mutableStateListOf<SavedPlace>()

    fun addPlace(place: SavedPlace) {
        if (selectedPlaces.none { it.id == place.id }) {
            selectedPlaces.add(place)
        }
    }

    fun removePlace(placeId: Int) {
        selectedPlaces.removeAll { it.id == placeId }
    }
}
