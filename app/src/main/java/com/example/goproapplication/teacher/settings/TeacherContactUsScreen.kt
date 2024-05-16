package com.example.goproapplication.teacher.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goproapplication.R
import com.example.goproapplication.components.ContactInfoItem
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler

@Composable
fun TeacherContactUsScreen(){
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gopro_background2),
            contentDescription = "gopro_background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { GoProAppRoute.navigateTo(Screen.TeacherSettingsScreen) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                HeadingTextComponent(value = stringResource(id = R.string.contact_us))
            }

            Image(
                painter = painterResource(id = R.drawable.contact_us),
                contentDescription = "Contact Us",
                modifier = Modifier.size(350.dp)
            )

            // Email
            ContactInfoItem(
                leadingIcon = Icons.Default.Email,
                infoText = "gopro@customerservice.com",
                buttonText = stringResource(id = R.string.email),
                onClick = { openEmailIntent(context, "gopro@customerservice.com") }
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = Color.Gray
            )

            // Phone
            ContactInfoItem(
                leadingIcon = Icons.Default.Phone,
                infoText = "03-1234 5678",
                buttonText = "Call",
                onClick = { openPhoneIntent(context, "03-1234 5678") }
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = Color.Gray
            )

            // Location
            ContactInfoItem(
                leadingIcon = Icons.Default.LocationOn,
                infoText = "Jalan Genting Kelang, Setapak, 53300 Kuala Lumpur",
                buttonText = "Map",
                onClick = { openMapIntent(context, "Jalan Genting Kelang, Setapak, 53300 Kuala Lumpur") }
            )

        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.TeacherSettingsScreen)
    }
}

fun openEmailIntent(context: Context, emailAddress: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$emailAddress")
    }
    context.startActivity(intent)
}

fun openPhoneIntent(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}

fun openMapIntent(context: Context, address: String) {
    val uri = Uri.parse("geo:0,0?q=$address")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}

@Preview
@Composable
fun TeacherContactUsScreenPreview(){
    TeacherContactUsScreen()
}