package com.example.safarchin.ui.theme.FourPageAsli.Profile.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val phone: String,
    val username: String,
    val name: String,
    val lastName: String,
    val age: String,
    val city: String,
    val notifyEnabled: Boolean,
    val showTripsPublicly: Boolean,
    val imageUri: String? = null // آدرس عکس پروفایل (nullable)
)
