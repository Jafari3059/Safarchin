package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data.SupportMessage
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data.SupportMessageDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SupportViewModel(private val dao: SupportMessageDao) : ViewModel() {

    val allMessages: Flow<List<SupportMessage>> = dao.getAllMessages()

    fun insertMessage(message: SupportMessage) {
        viewModelScope.launch {
            dao.insertMessage(message)
        }
    }
}
