package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SupportMessage::class], version = 1)
abstract class SupportMessageDatabase : RoomDatabase() {
    abstract fun supportMessageDao(): SupportMessageDao
}
