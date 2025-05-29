package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data.SupportDatabaseProvider

class SupportViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = SupportDatabaseProvider.getDatabase(context).supportMessageDao()

        if (modelClass.isAssignableFrom(SupportViewModel::class.java)) {
            return SupportViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
