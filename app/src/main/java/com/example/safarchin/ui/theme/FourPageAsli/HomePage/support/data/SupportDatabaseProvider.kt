package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data

import android.content.Context
import androidx.room.Room

object SupportDatabaseProvider {
    @Volatile
    private var INSTANCE: SupportMessageDatabase? = null

    fun getDatabase(context: Context): SupportMessageDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                SupportMessageDatabase::class.java,
                "support_message_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}
