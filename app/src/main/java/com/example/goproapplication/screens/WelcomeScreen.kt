package com.example.goproapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goproapplication.R
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.RoleButtonComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen

@Composable
fun WelcomeScreen(){
    Box(modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gopro_background1),
            contentDescription = "gopro_background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.welcome_text))
            Spacer(modifier = Modifier.height(30.dp))
            Image(painter = painterResource(
                id = R.drawable.robot),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            HeadingTextComponent(value = stringResource(id = R.string.welcome_option))
            Spacer(modifier = Modifier.height(40.dp))
            RoleButtonComponent(
                value = stringResource(id = R.string.welcome_teacher),
                onClick = {
                    GoProAppRoute.navigateTo(Screen.TeacherLoginScreen)
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            RoleButtonComponent(
                value = stringResource(id = R.string.welcome_student),
                onClick = {
                    GoProAppRoute.navigateTo(Screen.StudentLoginScreen)
                }
            )

        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}