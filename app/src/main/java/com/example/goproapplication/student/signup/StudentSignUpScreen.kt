package com.example.goproapplication.student.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goproapplication.R
import com.example.goproapplication.components.ButtonComponent
import com.example.goproapplication.components.ClickableLoginComponent
import com.example.goproapplication.components.DividerTextComponent
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.MyTextFieldComponent
import com.example.goproapplication.components.PasswordTextFieldComponent
import com.example.goproapplication.data.SignUpUIEvent
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler

@Composable
fun StudentSignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.student_sign_up_text))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.name),
                painterResource(id = R.drawable.person),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.NameChanged(it), context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.nameError
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it), context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.password),
                onTextSelected = {
                    signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it), context)
                },
                errorStatus = signUpViewModel.signUpUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                value = stringResource(id = R.string.sign_up_text),
                onButtonClicked = {
                    signUpViewModel.onEvent(SignUpUIEvent.SignUpButtonClicked, context)
                },
                isEnabled = signUpViewModel.allValidationPassed.value
            )
            Spacer(modifier = Modifier.height(50.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(50.dp))
            ClickableLoginComponent(tryingToLogin = true,
                onTextSelected = {
                    GoProAppRoute.navigateTo(Screen.StudentLoginScreen)
                })
        }
        if(signUpViewModel.signUpInProgress.value){
            CircularProgressIndicator()
        }
    }

    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.StudentLoginScreen)
    }
}

@Preview
@Composable
fun StudentSignUpScreenPreview(){
    StudentSignUpScreen()
}