package com.example.safarchin.ui.theme.FourPageAsli.Plannig.overviewP

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.safarchin.R


@Composable
fun AddNewCostPopup(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .heightIn(min = 720.dp, max = 1500.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                AddNewCostContent()
            }
        }
    }
}

@Composable
fun AddNewCostContent() {
    val categories = listOf("خرید", "اقامتگاه", "خوردنی", "تفریح", "حمل و نقل", "متفرقه")
    var selectedCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    fun validateDateFormat(input: String): String {
        // فقط اجازه می‌دیم عدد و / وارد بشه
        val allowed = input.filter { it.isDigit() || it == '/' }

        // حداکثر طول 10 کاراکتر (yyyy/mm/dd)
        return if (allowed.length <= 10) allowed else allowed.take(10)
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.cardsimage),
            contentDescription = "کارت بانکی",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // دسته بندی
        LabelledText(label = "دسته بندی هزینه")
        Box(
            modifier = Modifier.clickable { expanded = true }
                .fillMaxWidth(0.9f)
                .clickable { expanded = true }
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                placeholder = {
                    Text("دسته‌بندی", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = true }
                    )
                },
                textStyle = TextStyle(textAlign = TextAlign.Right),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp)
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // مبلغ هزینه
        LabelledText(label = "مبلغ هزینه")
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            placeholder = {
                Text("مبلغ", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(53.dp),
            singleLine = true,
            leadingIcon = {
                Text("تومان", color = Color.Gray, modifier = Modifier.padding(start = 8.dp))
            },
            textStyle = TextStyle(textAlign = TextAlign.Right)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // توضیح هزینه
        LabelledText(label = "توضیح هزینه")
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            placeholder = {
                Text("توضیح کوتاه", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(53.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(
                textDirection = TextDirection.Rtl,
                textAlign = TextAlign.Right,
                fontFamily = FontFamily(Font(R.font.iransans_medium))
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // تاریخ
        LabelledText(label = "تاریخ")
        OutlinedTextField(
            value = date,
            onValueChange = { date = validateDateFormat(it) },
            placeholder = {
                Text("yyyy/mm/dd", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(53.dp),
            textStyle = TextStyle(textAlign = TextAlign.Right),
            singleLine = true,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.calendaricon),
                    contentDescription = "آیکون تاریخ"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )



        Spacer(modifier = Modifier.height(32.dp))

        // دکمه ثبت هزینه
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(53.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("ثبت هزینه", color = Color.DarkGray)
        }
    }
}


// 🧾 تابع کوچک برای نوشتن عنوان‌های هر فیلد
@Composable
fun LabelledText(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(bottom = 4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(text = label)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAddNewCostPopup() {
    MaterialTheme {
        AddNewCostPopup(onDismiss = {})
    }
}