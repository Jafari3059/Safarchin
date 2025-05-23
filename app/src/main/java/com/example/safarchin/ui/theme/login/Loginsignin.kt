package com.example.safarchin.ui.theme.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.safarchin.R
import com.example.safarchin.data.api.RetrofitInstance
import com.example.safarchin.data.model.PhoneRequest
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun login(navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val viewModel: LoginViewModel = viewModel()

    val borderColor = when {
        phoneNumber.text.length < 2 -> Color(0xFF5F5F5F)
        isFocused -> Color(0xFFFFA500)
        !isValid -> Color.Red
        else -> Color(0xFF5F5F5F)
    }

    fun isValidIranianPhone(phone: String): Boolean {
        val regex = Regex("^09[0-9]{9}")
        return phone.matches(regex)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x802A2A2A)),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 60.dp)
                    .width(screenWidth * 0.88f)
                    .height(screenHeight * 0.28f)
                    .background(Color(0x60FFFFFF), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Text(
                            text = "ثبت نام",
                            fontSize = (screenWidth * 0.04f).value.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                    OutlinedTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            isValid = it.text.length < 2 || isValidIranianPhone(it.text)
                        },
                        placeholder = {
                            Text(
                                text = "شماره خود را وارد کنید",
                                modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                                textAlign = TextAlign.Right,
                                fontSize = (screenWidth * 0.04f).value.sp,
                                color = Color(0xFFCFD1D4),
                                fontWeight = FontWeight.ExtraLight,
                            )
                        },
                        trailingIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.phone),
                                contentDescription = "Phone Icon",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        shape = RoundedCornerShape(screenWidth * 0.03f),
                        modifier = Modifier
                            .fillMaxWidth(0.83f)
                            .height(screenHeight * 0.07f)
                            .background(Color(0xFF5F5F5F), RoundedCornerShape(screenWidth * 0.03f))
                            .border(2.dp, borderColor, RoundedCornerShape(screenWidth * 0.03f))
                            .onFocusChanged { isFocused = it.isFocused },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                    Button(
                        onClick = {
                            if (isValidIranianPhone(phoneNumber.text)) {
                                viewModel.sendCode(phoneNumber.text)
                                coroutineScope.launch {
                                    try {
                                        val response = RetrofitInstance.api.sendCode(PhoneRequest(phoneNumber.text))
                                        result = response.message
                                        navController.navigate("codelogin/${phoneNumber.text}")
                                    } catch (e: Exception) {
                                        result = "خطا: ${e.message}"
                                    }
                                }
                            } else {
                                isValid = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB26B)),
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(screenHeight * 0.065f),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "ثبت",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = result, color = Color.White)
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginPreview() {
////    val navController = rememberNavController()
//    login(NavController)
//}
