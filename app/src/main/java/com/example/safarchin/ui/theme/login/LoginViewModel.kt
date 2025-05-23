package com.example.safarchin.ui.theme.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safarchin.data.api.RetrofitInstance
import com.example.safarchin.data.model.PhoneRequest
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    fun sendCode(phone: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.sendCode(PhoneRequest(phone))
                Log.d("SendCode", "status: ${response.status}, message: ${response.message}")
            } catch (e: Exception) {
                Log.e("SendCode", "Error: ${e.message}")
            }
        }
    }
}
