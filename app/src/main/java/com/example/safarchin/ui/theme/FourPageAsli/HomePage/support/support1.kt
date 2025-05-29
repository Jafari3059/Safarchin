
package com.example.safarchin.ui.theme.FourPageAsli.HomePage.support

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.safarchin.R
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data.SupportDatabaseProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.safarchin.ui.theme.FourPageAsli.HomePage.support.data.SupportMessage




@Composable
fun Support1(navController: NavController) {

    val context = LocalContext.current
    val db = SupportDatabaseProvider.getDatabase(context)
    val dao = db.supportMessageDao()
    val viewModel: SupportViewModel = viewModel(factory = SupportViewModelFactory(context))

    val helpItems = listOf(
        "سوالات متداول" to "placeholder",
        "راهنمای استفاده (با تصویر و ویدیو)" to "placeholder",
        "جستجوی مشکلات رایج" to "placeholder",
        "تماس با پشتیبانی" to "برای ارتباط مستقیم با پشتیبانی می‌توانید از گزینه‌های تماس، ایمیل یا ارسال پیام استفاده کنید.",
        "راهنمای مکان‌یابی و مجوزها" to "placeholder",
        "بروزرسانی‌ها و تغییرات نسخه جدید" to "لیستی از بهبودها، امکانات جدید و تغییرات نسخه‌های اخیر برنامه در این بخش ارائه می‌شود.",
        "بخش ارسال بازخورد یا پیشنهادات" to "در صورت داشتن نظر، پیشنهاد یا مشکل خاص، از این قسمت برای ارسال بازخورد استفاده کنید."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF6F4F4))
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "پل خواجو",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(249.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.next_icon),
                        contentDescription = "بازگشت",
                        tint = Color(0xFFFF7B54),
                        modifier = Modifier.size(24.dp)
                            .clickable {
                                navController.popBackStack() // 👈 رفتن به صفحه قبل
                            }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.notification_icon),
                        contentDescription = "زنگ",
                        tint = Color(0xFFFF7B54),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFFF5F5F5))
                        )
                    )
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        helpItems.forEach { (title, content) ->
            ExpandableHelpItem(title = title, content = content ,    viewModel = viewModel )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "آیا این راهنما برایتان مفید بود؟",
            fontSize = 14.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { /* Like */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_thumb_up),
                    contentDescription = "مفید بود",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(28.dp) // یا مثلاً 32.dp
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            IconButton(onClick = { /* Dislike */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_thumb_down),
                    contentDescription = "مفید نبود",
                    tint = Color(0xFFF44336),
                    modifier = Modifier.size(28.dp)
                )
            }
        }

    }
}

@Composable
fun ExpandableHelpItem(
    title: String,
    content: String,
    viewModel: SupportViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "arrowRotation"
    )




    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 27.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        )
    )

    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.down),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(rotation)
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Right,
                    color = Color.Black,
                    style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right,
                color = Color.Black,
                style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl)
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            when (title) {
                "سوالات متداول" -> {
                    Column {

                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        FAQItem("چطور می‌تونم یک سفر جدید بسازم؟", "از بخش «سفرهای من»، روی دکمه + کلیک کنید. عنوان و تاریخ سفر را وارد کرده و دکمه ذخیره را بزنید.")
                        FAQItem("چطور می‌تونم مکان‌های دیدنی رو به سفرم اضافه کنم؟", "بعد از ایجاد سفر، از طریق نقشه یا جستجو، مکان مورد نظر رو پیدا کرده و با زدن دکمه افزودن به سفر، اون رو به برنامه‌تون اضافه کنید.")
                        FAQItem("آیا امکان اشتراک‌گذاری برنامه سفر با دیگران وجود دارد؟", "بله. در صفحه هر سفر، گزینه «اشتراک‌گذاری» وجود دارد که می‌توانید لینک سفر را برای دیگران بفرستید.")
                        FAQItem("آیا می‌تونم سفرم رو به‌صورت آفلاین هم ببینم؟", "بله، اطلاعات سفر پس از بارگیری اولیه ذخیره می‌شود و در صورت عدم دسترسی به اینترنت هم قابل مشاهده هستند.")
                        FAQItem("چرا نقشه برای من نمایش داده نمی‌شود؟", "لطفاً مطمئن شوید که مجوز دسترسی به موقعیت مکانی فعال است و اتصال اینترنت برقرار است.")
                        FAQItem("حتماً. نرفته یک مکان، میشه حذفش کرد؟", "بله، از بخش ویرایش سفر می‌توانید مکان‌ها رو حذف کنید.")
                    }
                }
                "راهنمای استفاده (با تصویر و ویدیو)" -> {
                    Column {
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        FAQItem("ساخت سفر جدید", "برای شروع، وارد بخش سفرهای من شوید و روی دکمه + کلیک کنید. عنوان سفر، مقصد و تاریخ را وارد کرده و ذخیره کنید.")
                        FAQItem("افزودن مکان‌های دیدنی", "در صفحه سفر با استفاده از نقشه یا جستجو، مکان‌های مورد نظر را پیدا کنید و با زدن گزینه «افزودن»، آن‌ها را به برنامه سفر اضافه نمایید.")
                        FAQItem("برنامه‌ریزی زمان‌بندی", "برای هر مکان زمان رسیدن موردنظر را مشخص کنید تا برنامه روزانه منظمی داشته باشید.")
                        FAQItem("مسیر سفر روی نقشه", "با فعال کردن موقعیت مکانی، می‌توانید مسیرهای پیشنهادی برای رسیدن به هر مکان را روی نقشه ببینید.")
                        FAQItem("اشتراک‌گذاری برنامه سفر", "با زدن دکمه «اشتراک‌گذاری» می‌توانید برنامه سفر خود را با دوستان یا همراهانتان به‌صورت لینک یا عکس ارسال کنید.")
                        FAQItem("ویرایش یا حذف برنامه", "در هر زمان می‌توانید با مراجعه به صفحه سفر، مکان‌ها یا زمان‌ها را ویرایش یا حذف کنید.")
                        Image(
                            painter = painterResource(id = R.drawable.video),
                            contentDescription = "ویدیو آموزشی",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(top = 16.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                }
                "جستجوی مشکلات رایج" -> {
                    Column {
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )

                        FAQItem("برنامه باز نمی‌شود یا هنگ می‌کند", "بررسی اتصال اینترنت، به‌روزرسانی برنامه به آخرین نسخه و در صورت ادامه مشکل، پاک‌سازی کش (Cache) یا نصب مجدد برنامه.")
                        FAQItem("نقشه‌ها یا مکان‌ها لود نمی‌شوند", "لطفاً مطمئن شوید که دسترسی به موقعیت مکانی فعال است و اتصال اینترنت برقرار است. اگر همچنان مشکلی بود، از تنظیمات دستگاه بررسی کنید.")
                    }
                }
                "راهنمای مکان‌یابی و مجوزها" -> {
                    Column {
                        HorizontalDivider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        FAQItem("برای اینکه تجربه‌ی بهتری در سفر داشته باشید، اپلیکیشن نیاز به دسترسی به موقعیت مکانی شما دارد تا بتواند:", "\n• مکان فعلی شما را روی نقشه نمایش دهد\n• مسیرهای پیشنهادی و تخمینی زمان رسیدن تا مکان‌ها را نشان دهد")
                        FAQItem("در دستگاه‌های اندرویدی:", "\n• وارد بخش تنظیمات شوید\n• به بخش مجوزها (Permissions) کلیک کنید\n• گزینه Location را انتخاب کرده و حالت دسترسی مناسب را اعمال کنید")
                        FAQItem("در دستگاه‌های iOS:", "\n• وارد Settings شوید\n• Privacy & Security > Location را انتخاب کنید\n• Services را فعال کنید\n• گزینه While Using the App را فعال کنید\n• اگر خواستید، گزینه Precise Location را هم روشن کنید")
                    }
                }
                "بخش ارسال بازخورد یا پیشنهادات" -> {
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FeedbackForm()
                }

                "تماس با پشتیبانی" -> {
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SupportForm(viewModel = viewModel)
                }


                "بروزرسانی‌ها و تغییرات نسخه جدید" -> {
                    HorizontalDivider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    UpdatesSection()
                }



                else -> {
                    Text(
                        text = content,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Right,
                        style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@Composable
fun FAQItem(question: String, answer: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // سوال نارنجی
        Text(
            text = question,
            color = Color(0xFFFF7B00),
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp, start = 24.dp) // ← تورفتگی از راست
        )

        // جواب مشکی
        Text(
            text = answer,
            color = Color.Black,
            fontSize = 14.sp,
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start =16.dp, top = 4.dp) // ← کم‌تر تو رفته از بالا
        )
    }
}


@Composable
fun FeedbackForm() {
    var selectedType by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "ما همیشه در تلاشیم تجربه سفر شما با اپلیکیشن بهتر و راحت‌تر بشه. اگر پیشنهادی برای بهبود برنامه دارید یا مشکلی مشاهده کردید، خوشحال می‌شیم ازتون بشنویم.",
            fontSize = 14.sp,
            color = Color(0xFFFF7B54),
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("نام و نام خانوادگی (اختیاری)", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("ایمیل یا شماره تماس", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "نوع بازخورد:",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl)
        )

        val feedbackTypes = listOf("پیشنهاد بهبود", "گزارش باگ", "نظر کلی")
        feedbackTypes.forEach { type ->
            val isSelected = selectedType == type
            Button(
                onClick = { selectedType = type },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFF939B62) else Color(0xFFEFEFEF),
                    contentColor = if (isSelected) Color.White else Color.Black
                )
            ) {
                Text(type)
            }
        }

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            placeholder = { Text("اینجا می‌تونید جزئیات بازخورد یا پیشنهادتون رو بنویسید", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) }
        )

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { /* ارسال بازخورد */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54))
        ) {
            Text("ارسال بازخورد")
        }
    }
}










@Composable
fun SupportForm(viewModel: SupportViewModel) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // گرفتن پیام‌های ذخیره‌شده از ViewModel
    val messages by viewModel.allMessages.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxWidth()) {

        // متن توضیحی بالای فرم
        Text(
            text = "می‌تونید از طریق فرم زیر پیام‌تون رو ارسال کنید...",
            fontSize = 14.sp,
            color = Color(0xFFFF7B54),
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 27.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // فیلدهای ورودی
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = { Text("نام و نام خانوادگی", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 27.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("ایمیل یا شماره تماس", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 27.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = subject,
            onValueChange = { subject = it },
            placeholder = { Text("موضوع مشکل", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 27.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("توضیحات بیشتر...", textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth()) },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 27.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // دکمه ارسال
        Button(
            onClick = {
                if (fullName.isNotBlank() && email.isNotBlank() && subject.isNotBlank() && message.isNotBlank()) {
                    viewModel.insertMessage(
                        SupportMessage(
                            fullName = fullName,
                            email = email,
                            subject = subject,
                            message = message
                        )
                    )
                    fullName = ""
                    email = ""
                    subject = ""
                    message = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 53.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7B54))
        ) {
            Text("ارسال")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // نمایش پیام‌های ذخیره‌شده
        if (messages.isNotEmpty()) {
            Text(
                "پیام‌های ثبت‌شده:",
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 27.dp, bottom = 8.dp)
            )

            messages.forEach { msg ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 27.dp, vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("نام: ${msg.fullName}", fontSize = 13.sp)
                        Text("ایمیل: ${msg.email}", fontSize = 13.sp)
                        Text("موضوع: ${msg.subject}", fontSize = 13.sp)
                        Text("پیام: ${msg.message}", fontSize = 13.sp)
                    }
                }
            }
        }
    }
}











@Composable
fun UpdatesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    )
    {
        Text(
            text = "خوشحالیم که با شما هستیم! در این نسخه، بهبودهایی برای تجربه‌ی بهتر سفر انجام دادیم:",
            fontSize = 14.sp,
            color = Color.Black,
            textAlign = TextAlign.Right,
            style = LocalTextStyle.current.copy(textDirection = TextDirection.Rtl),
            modifier = Modifier.padding(start = 8.dp) // ← فاصله از سمت راست (برای فارسی)
        )

        Spacer(modifier = Modifier.height(12.dp))

        FAQItem(
            "ویژگی‌های جدید:",
            "• قابلیت اشتراک‌گذاری برنامه سفر با یک کلیک\n" +
                    "• مشاهده موقعیت مکانی من در نقشه\n" +
                    "• پیشنهادات مسیر برای مکان‌های بازدید (رستوران‌ها، دیدنی‌ها)"
        )

        FAQItem(
            "بهبودهای عملکرد:",
            "• افزایش پایداری ذخیره‌سازی نقشه‌ها و مسیر سفر\n" +
                    "• بهینه‌سازی بارگذاری لیست مکان‌ها و دسته‌بندی‌ها\n" +
                    "• بهبود نمایش پیام پشتیبانی و ارسال گزارش خطا"
        )

        FAQItem(
            "باک‌فیکس‌ها و رفع اشکال:",
            "• رفع مشکل ناپدیدشدن آدرس ایمیل\n" +
                    "• حل مشکل باز نشدن عکس‌ها در قسمت مکان"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(

            onClick = { /* عملیات بروزرسانی */ },
            modifier = Modifier

                .fillMaxWidth()
                .padding(horizontal = 53.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F54))
        ) {
            Text("بروزرسانی")
        }


    }
}