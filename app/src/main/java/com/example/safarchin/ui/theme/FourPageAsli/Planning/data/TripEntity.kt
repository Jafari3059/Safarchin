package com.example.safarchin.ui.theme.FourPageAsli.Planning.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,           // آیدی کاربر لاگین‌شده
    val title: String,            // عنوان سفر
    val city: String,             // مقصد
    val startDate: String,        // تاریخ شروع
    val endDate: String,          // تاریخ پایان
    val travelers: Int,           // تعداد همسفر
    val budget: Int,              // مبلغ بودجه
    val budgetForAll: Boolean,    // آیا بودجه برای همه‌ست؟
    val status: String,           // وضعیت سفر
    val date: String,
    val imageRes: Int             // تصویر مربوطه
)
