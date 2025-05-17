package com.example.safarchin.ui.theme.FourPageAsli.Profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.iranSans
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.UserEntity
import java.io.File


@Composable
fun popupSettting(
    user: UserEntity,
    onPhoneChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSubmit: (UserEntity) -> Unit
)
{
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val isPhonePopupVisible = remember { mutableStateOf(false) }
    var selectedImageUri by remember {
        mutableStateOf(user.imageUri?.let { Uri.fromFile(File(it)) })
    }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    var isCheckedS by remember { mutableStateOf(user.showTripsPublicly) }
    var isCheckedN by remember { mutableStateOf(user.notifyEnabled) }

    val inputValues = remember {
        mutableStateListOf(
            user.username,
            user.name,
            user.lastName,
            user.age,
            user.city,
            user.phone
        )
    }

    Box(
            modifier = Modifier
                .width(screenWidth * 0.9f)
                .wrapContentHeight() // 👈 ارتفاع به اندازه محتوا
                .background(color = Color(0xFFF6F4F4), RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = Color(0xFFF6F4F4))
                ) {
                    // عکس و آپلود
                    Box(
                        modifier = Modifier
                            .wrapContentHeight() // ✅ تغییر مهم
                            .weight(1f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier.height(screenHeight * 0.01f))

                            Image(
                                painter = if (selectedImageUri != null)
                                    rememberAsyncImagePainter(selectedImageUri)
                                else
                                    painterResource(id = R.drawable.profile_pic),
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(screenWidth * 0.22f)
                                    .clip(CircleShape)
                            )


                            Text(
                                text = "آپلود عکس",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Bold,
                                fontSize = (screenWidth.value * 0.022).sp,
                                color = Color.Blue,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = screenHeight * 0.005f)
                                    .clickable {
                                        imagePickerLauncher.launch("image/*")
                                    },
                                style = androidx.compose.ui.text.TextStyle(
                                    textDecoration = TextDecoration.Underline
                                )
                            )

                        }
                    }

                    // فیلدها
                    Box(
                        modifier = Modifier
                            .wrapContentHeight() // ✅ تغییر مهم
                            .weight(2f)
                    ) {
                        Column {
                            val labels = listOf(
                                ":نام کاربری",
                                ":نام",
                                ":نام خانوادگی",
                                ":سن",
                                ":شهر محل زندگی",
                                ":شماره"
                            )
                            val dropDownIndices = listOf(3, 4) // سن = index 3، شهر = index 4
                            val ageOptions = (18..70).map { "$it سال" }
                            val cityOptions = listOf("تهران", "مشهد", "اصفهان", "شیراز", "تبریز", "رشت", "کرج", "قم", "اهواز", "یزد", "ارومیه", "زاهدان", "سنندج", "گرگان", "بندرعباس", "قزوین", "زنجان", "کرمان", "خرم‌آباد", "ایلام", "بوشهر", "ساری", "کاشان", "بجنورد", "سبزوار", "کیش", "قشم", "شهرکرد", "اردبیل", "همدان", "ملایر", "مراغه", "بابل", "آمل", "نجف‌آباد", "ورامین", "اندیمشک", "شوشتر", "ساوه", "بیرجند", "نیشابور", "دزفول", "لار", "آبادان", "ماهشهر", "خمینی‌شهر", "رفسنجان", "ایرانشهر", "سیرجان", "جاجرم", "گرمسار", "طبس", "دهدشت", "درود", "بندر گناوه", "تربت‌حیدریه")



                            labels.forEachIndexed { index, label ->
                                var expanded by remember { mutableStateOf(false) }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = screenHeight * 0.004f),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(5f)
                                            .height(screenHeight * 0.04f)
                                            .background(Color.White, RoundedCornerShape(screenWidth * 0.03f))
                                            .clickable(enabled = index in dropDownIndices) { expanded = true }
                                            .border(1.dp, Color.LightGray, RoundedCornerShape(screenWidth * 0.03f)),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (index in dropDownIndices) {
                                            // فیلد کشویی (سن و شهر)
                                            Text(
                                                text = inputValues[index].ifEmpty { "انتخاب کنید" },
                                                fontFamily = iranSans,
                                                fontSize = (screenWidth.value * 0.026).sp,
                                                color = if (inputValues[index].isEmpty()) Color.Gray else Color(0xFFFF7F54),
                                                modifier = Modifier
                                                    .padding(horizontal = 8.dp)
                                                    .fillMaxWidth(),
                                                textAlign = TextAlign.Right
                                            )

                                            DropdownMenu(
                                                expanded = expanded,
                                                onDismissRequest = { expanded = false },
                                                modifier = Modifier
                                                    .fillMaxWidth(0.38f)
                                                    .heightIn(max = screenHeight * 0.3f)
                                                    .background(Color.White)
                                            ) {
                                                val items = if (index == 3) ageOptions else cityOptions
                                                items.forEach { option ->
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = option,
                                                                fontFamily = iranSans,
                                                                fontSize = (screenWidth.value * 0.024).sp
                                                            )
                                                        },
                                                        onClick = {
                                                            inputValues[index] = option
                                                            expanded = false
                                                        }
                                                    )
                                                }
                                            }
                                        }else if (index == 5) {
                                            Text(
                                                text = inputValues[index],
                                                fontFamily = iranSans,
                                                fontSize = (screenWidth.value * 0.026).sp,
                                                color = Color(0xFF969696),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 8.dp)
                                                    .clickable {
                                                        isPhonePopupVisible.value = true
                                                    },
                                                textAlign = TextAlign.Right
                                            )
                                        } else {
                                            // بقیه فیلدها قابل ویرایش
                                            BasicTextField(
                                                value = inputValues[index],
                                                onValueChange = { inputValues[index] = it },
                                                singleLine = true,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 6.dp),
                                                textStyle = TextStyle(
                                                    fontFamily = iranSans,
                                                    fontSize = (screenWidth.value * 0.026).sp,
                                                    color = Color(0xFFFF7F54),
                                                    textAlign = TextAlign.Right
                                                )
                                            )
                                        }
                                    }

                                    Text(
                                        text = label,
                                        fontFamily = iranSans,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = (screenWidth.value * 0.025).sp,
                                        color = Color.Black,
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier
                                            .weight(2f)
                                            .padding(start = 6.dp)
                                    )
                                }
                            }


                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.05f)
                        .background(Color.Transparent),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    // Switch سفارشی‌شده
                    Switch(
                        checked = isCheckedS,
                        onCheckedChange = { isCheckedS = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFFFFC98D), // نارنجی کم‌رنگ
                            uncheckedThumbColor = Color.Black,
                            uncheckedTrackColor = Color.Transparent
                        ),
                        modifier = Modifier.scale(0.85f)
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("ایا مایل هستید سفرهای برنامه ریزی شده شما در صفحه پروفایلتان قرار بگیرد؟ ")

                            withStyle(
                                style = SpanStyle(
                                    fontSize = (screenWidth.value * 0.020).sp, // کوچکتر از متن اصلی
                                    color = Color.Gray // می‌تونی رنگ رو هم عوض کنی
                                )
                            ) {
                                append("(با اینکار کاربران دیگر میتوانند محتوای سفر شما را مشاهده کنند)")
                            }
                        },
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth.value * 0.025).sp, // سایز متن اصلی
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.05f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(
                        checked = isCheckedN,
                        onCheckedChange = { isCheckedN = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFFFFC98D),
                            uncheckedThumbColor = Color.Black,
                            uncheckedTrackColor = Color.Transparent // برای حذف ظاهر خاموش
                        ),
                        modifier = Modifier
                            .scale(0.85f)
                    )
                    Spacer(modifier = Modifier.weight(1f)) // 👈 فاصله‌دهنده بین متن و سوییچ

                    Text(
                        text = "اعلان ها",
                        fontFamily = iranSans,
                        fontWeight = FontWeight.Bold,
                        fontSize = (screenWidth.value * 0.025).sp,
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                    )

                }
                Spacer(modifier = Modifier.height(16.dp)) // 👈 فاصله افقی بین دو دکمه


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.07f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight * 0.07f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // خروج
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(screenWidth * 0.04f))
                                .background(Color.White)
                                .border(1.5.dp, Color(0xFFFF3A3A), RoundedCornerShape(screenWidth * 0.04f))
                                .clickable { onDismiss() }, // 👈 فقط بستن
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "خروج",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.04).sp,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.width(screenWidth * 0.06f))

                        // ثبت
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(screenWidth * 0.04f))
                                .background(Color(0xFFFF7F54))
                                .clickable {
                                    val updatedUser = user.copy(
                                        username = inputValues[0],
                                        name = inputValues[1],
                                        lastName = inputValues[2],
                                        age = inputValues[3],
                                        city = inputValues[4],
                                        phone = inputValues[5],
                                        notifyEnabled = isCheckedN,
                                        showTripsPublicly = isCheckedS,
                                        imageUri = selectedImageUri?.path ?: user.imageUri
                                    )
                                    onSubmit(updatedUser)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ثبت",
                                fontFamily = iranSans,
                                fontWeight = FontWeight.Medium,
                                fontSize = (screenWidth.value * 0.04).sp,
                                color = Color.White
                            )
                        }
                    }

                }

            }



        }

    if (isPhonePopupVisible.value) {
        Dialog(
            onDismissRequest = { isPhonePopupVisible.value = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            )
        ) {
            PhoneVerificationDialog(
                phone = inputValues[5], // ← از inputValues بخون
                onDismiss = { isPhonePopupVisible.value = false },
                onConfirm = { newPhone ->
                    onPhoneChange(newPhone)
                    inputValues[5] = newPhone // ✅ این خط خیلی مهمه
                    isPhonePopupVisible.value = false
                }
            )
        }
    }







}

//@Preview(showBackground = true)
//@Composable
//fun PopupS() {
//    popupSettting()
//}