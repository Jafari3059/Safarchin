package com.example.safarchin.ui.theme.FourPageAsli.Planning.Components_planningP

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun calculateDaysLeft(startDate: String): Int {
    return try {
        val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val start = sdf.parse(startDate)
        val now = Date()

        val diffInMillis = start.time - now.time
        val daysLeft = TimeUnit.MILLISECONDS.toDays(diffInMillis)

        daysLeft.toInt()
    } catch (e: Exception) {
        -1
    }
}
