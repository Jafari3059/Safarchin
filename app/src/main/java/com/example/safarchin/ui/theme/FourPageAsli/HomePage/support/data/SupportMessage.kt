package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "support_messages")
data class SupportMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val email: String,
    val subject: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
