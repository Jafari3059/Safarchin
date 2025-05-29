package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SupportMessageDao {

    @Insert
    suspend fun insertMessage(message: SupportMessage)

    @Query("SELECT * FROM support_messages ORDER BY timestamp DESC")
    fun getAllMessages(): kotlinx.coroutines.flow.Flow<List<SupportMessage>>
    }