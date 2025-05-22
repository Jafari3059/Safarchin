package com.example.safarchin.ui.theme.FourPageAsli.Planning.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {

    @Query("SELECT * FROM trips WHERE userId = :userId")
    fun getAllTrips(userId: String): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE userId = :userId AND status = 'تمام‌شده'")
    fun getCompletedTrips(userId: String): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE userId = :userId AND status = 'در حال برنامه‌ریزی'")
    fun getPlanningTrips(userId: String): Flow<List<TripEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip: TripEntity)

    @Delete
    suspend fun deleteTrip(trip: TripEntity)

    @Update
    suspend fun updateTrip(trip: TripEntity)

    @Query("DELETE FROM trips WHERE id = :tripId")
    suspend fun deleteTripById(tripId: Int)

    @Query("UPDATE trips SET status = :newStatus WHERE id = :id")
    suspend fun updateStatus(id: Int, newStatus: String)

    @Query("SELECT * FROM trips WHERE userId = :userId AND status = 'در حال برنامه‌ریزی' ORDER BY startDate DESC LIMIT 1")
    suspend fun getLastPlannedTrip(userId: String): TripEntity?



}
