package com.example.goproapplication.student.profile

import ProfileViewModel
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goproapplication.R
import com.example.goproapplication.components.BoldTextComponent
import com.example.goproapplication.components.ButtonComponent
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.NormalTextComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen

@Composable
fun StudentProfileScreen(profileViewModel: ProfileViewModel = viewModel()) {
    val userProfile = profileViewModel.userProfile.value
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

    Box(
        modifier = Modifier.fillMaxSize()
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

            HeadingTextComponent(value = stringResource(id = R.string.student_profile))
            Spacer(modifier = Modifier.padding(15.dp))
            Image(
                painter = painterResource(id = R.drawable.student),
                contentDescription = "Student",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(15.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.dark_purple)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(bottom = 20.dp)
            ) {
                if (userProfile != null) {
                    BoldTextComponent(value = stringResource(id = R.string.name))
                    NormalTextComponent(value = "${userProfile.name}")
                    BoldTextComponent(value = stringResource(id = R.string.email))
                    NormalTextComponent(value = "${userProfile.email}")
                }
            }
            Card(
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.dark_purple)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(bottom = 20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.friends),
                        contentDescription = stringResource(id = R.string.invite_header),
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                    )
                    Column {
                        BoldTextComponent(value = stringResource(id = R.string.invite_header))
                        NormalTextComponent(value = stringResource(id = R.string.invite_text))
                        ButtonComponent(
                            value = stringResource(id = R.string.invite),
                            onButtonClicked = shareOrCopyMessage,
                            isEnabled = true
                        )
                    }
                }
            }
            ButtonComponent(
                value = stringResource(id = R.string.more_settings),
                onButtonClicked = { GoProAppRoute.navigateTo(Screen.StudentSettingsScreen) },
                isEnabled = true
            )
        }
    }

}

@Preview
@Composable
fun StudentProfileScreenPreview(){
    StudentProfileScreen(ProfileViewModel())
}
