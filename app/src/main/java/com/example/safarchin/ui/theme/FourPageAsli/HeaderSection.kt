package com.example.safarchin.ui.theme.FourPageAsli

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import com.example.safarchin.ui.theme.FourPageAsli.Profile.data.UserEntity
import com.example.safarchin.ui.theme.iranSans

import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.io.File

@Composable
fun HeaderSection(
    onNotificationClick: () -> Unit,
    onHelpClick: () -> Unit,
    user: UserEntity?
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val name = user?.name ?: "کاربر"
    val profileImage = user?.imageUri

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenWidth * 0.06f, vertical = screenHeight * 0.055f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // سمت چپ: آیکون‌ها
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = onHelpClick,
                modifier = Modifier.size(screenWidth * 0.1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.question_icon),
                    contentDescription = "Help Icon",
                    modifier = Modifier.size(screenWidth * 0.07f)
                )
            }

            Spacer(modifier = Modifier.width(screenWidth * 0.01f))

            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier.size(screenWidth * 0.1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.notification_icon),
                    contentDescription = "Notification Icon",
                    modifier = Modifier.size(screenWidth * 0.08f)
                )
            }
        }

        // سمت راست: پروفایل و متن خوشامدگویی
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "سلام $name",
                    fontSize = (screenWidth * 0.035f).value.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = iranSans,
                    color = Color.Black,
                    textAlign = TextAlign.End
                )
                Text(
                    text = "حاضری یه ماجراجویی دیگه رو شروع کنیم؟",
                    fontSize = (screenWidth * 0.025f).value.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = iranSans,
                    color = Color.Black,
                    textAlign = TextAlign.End
                )
            }

            Spacer(modifier = Modifier.width(screenWidth * 0.03f))

            val currentUser = user
            val imageFile = currentUser?.imageUri?.let { File(it) }

            val painter = if (imageFile != null && imageFile.exists()) {
                rememberAsyncImagePainter(imageFile)
            } else {
                painterResource(id = R.drawable.profile_pic)
            }

            Image(
                painter = painter,
                contentDescription = "Profile Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )






        }
    }
}

fun saveImageToInternalStorage(context: Context, uri: Uri): String? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val fileName = "profile.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        file.absolutePath // این آدرس رو تو Room ذخیره کن
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HeaderSectionPreview() {
//    HeaderSection(
//        onNotificationClick = {},
//        onHelpClick = {}
//    )
//}
