package com.example.safarchin.ui.theme.FourPageAsli.Profile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityDao
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityEntity
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityTypeConverters
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripEntity
import com.example.safarchin.ui.theme.FourPageAsli.Planning.data.TripDao

@Database(entities = [UserEntity::class, CityEntity::class, TripEntity::class], version = 6)
@TypeConverters(CityTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tripDao(): TripDao
    abstract fun cityDao(): CityDao
}
