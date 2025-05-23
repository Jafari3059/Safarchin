package com.example.safarchin.ui.theme.FourPageAsli.Planning.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.DatabaseProvider
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TripsViewModel(application: Application) : AndroidViewModel(application) {

    private val tripDao = DatabaseProvider.getDatabase(application).tripDao()

    var searchQuery = MutableStateFlow("")
    var selectedTab = MutableStateFlow("همه")

    fun getTrips(userId: String): Flow<List<TripEntity>> {
        return selectedTab.flatMapLatest { tab ->
            val baseFlow = when (tab) {
                "تمام‌شده" -> tripDao.getCompletedTrips(userId)
                "در حال برنامه‌ریزی" -> tripDao.getPlanningTrips(userId)
                else -> tripDao.getAllTrips(userId)
            }
            baseFlow.combine(searchQuery) { trips, query ->
                if (query.isBlank()) trips
                else trips.filter { it.title.contains(query, ignoreCase = true) }
            }
        }
    }

    fun insertTrip(trip: TripEntity) = viewModelScope.launch {
        tripDao.insertTrip(trip)
    }

    fun deleteTripById(id: Int) = viewModelScope.launch {
        tripDao.deleteTripById(id)
    }

    fun updateTrip(trip: TripEntity) = viewModelScope.launch {
        tripDao.insertTrip(trip) // چون onConflict = REPLACE هست، این کار آپدیت هم محسوب میشه
    }

    fun updateTripStatus(id: Int, newStatus: String) = viewModelScope.launch {
        tripDao.updateStatus(id, newStatus)
    }

    suspend fun getLastPlannedTrip(userId: String): TripEntity? {
        return tripDao.getLastPlannedTrip(userId)
    }

}
