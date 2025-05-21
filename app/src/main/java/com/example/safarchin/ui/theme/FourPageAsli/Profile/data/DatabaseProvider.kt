package com.example.safarchin.ui.theme.FourPageAsli.Profile.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (db == null) {
//            val dbFile = context.getDatabasePath("safarchin_db")// برای حذف کردن فایل دیتابیس به صورت دستی
//            if (dbFile.exists()) {
//                dbFile.delete()
//            }
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "safarchin_db"
            )
                .fallbackToDestructiveMigration() // فعلاً برای اجرای اولیه
                .build()

        }
        return db!!
    }
}
