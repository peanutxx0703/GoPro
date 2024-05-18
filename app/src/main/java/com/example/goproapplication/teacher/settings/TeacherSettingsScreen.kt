package com.example.goproapplication.teacher.settings

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goproapplication.R
import com.example.goproapplication.components.BoldTextComponent
import com.example.goproapplication.components.ButtonComponent
import com.example.goproapplication.components.CardItemComponent
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler

@Composable
fun TeacherSettingsScreen(teacherLoginViewModel: TeacherLoginViewModel){
    val context = LocalContext.current
    val shareLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ){ }
    val shareOrCopyMessage: () -> Unit = {
        val message = context.getString(R.string.copy_text)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)

        shareLauncher.launch(Intent.createChooser(shareIntent, "Share via"))
    }

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { GoProAppRoute.navigateTo(Screen.TeacherProfileScreen) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                HeadingTextComponent(value = stringResource(id = R.string.settings_text))
            }
            BoldTextComponent(value = stringResource(id = R.string.account))
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.change_password),
                value = stringResource(id = R.string.change_password),
                onClick = { GoProAppRoute.navigateTo(Screen.TeacherChangePasswordScreen)}
            )
            BoldTextComponent(value = stringResource(id = R.string.general))
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.notifications),
                value = stringResource(id = R.string.notifications),
                onClick = { }
            )
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.share),
                value = stringResource(id = R.string.share),
                onClick = shareOrCopyMessage
            )
            BoldTextComponent(value = stringResource(id = R.string.support))
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.about),
                value = stringResource(id = R.string.contact_us),
                onClick = { GoProAppRoute.navigateTo(Screen.TeacherContactUsScreen) }
            )
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.privacy_policy),
                value = stringResource(id = R.string.privacy_policy),
                onClick = { GoProAppRoute.navigateTo(Screen.TeacherPrivacyPolicyScreen) }
            )
            CardItemComponent(
                painterResource = painterResource(id = R.drawable.terms_and_conditions),
                value = stringResource(id = R.string.terms_and_conditions),
                onClick = { GoProAppRoute.navigateTo(Screen.TeacherTermsAndConditionsScreen) }
            )
            ButtonComponent(
                value = stringResource(id = R.string.log_out),
                onButtonClicked = { teacherLoginViewModel.logout(context) },
                isEnabled = true
            )


        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.TeacherProfileScreen)
    }
}

@Preview
@Composable
fun TeacherSettingsScreenPreview(){
    TeacherSettingsScreen(TeacherLoginViewModel())
}