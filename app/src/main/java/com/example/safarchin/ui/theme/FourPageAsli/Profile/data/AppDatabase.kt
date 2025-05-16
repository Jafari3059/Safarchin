package com.example.safarchin.ui.theme.FourPageAsli.Profile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityDao
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityEntity
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.city.data.CityTypeConverters

@Database(entities = [UserEntity::class, CityEntity::class], version = 4)
@TypeConverters(CityTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cityDao(): CityDao
}
