package com.example.goproapplication.teacher.login

import ProfileViewModel
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.CourseViewScreen
import com.example.goproapplication.SplashScreen
import com.example.goproapplication.R
import com.example.goproapplication.StudentNavBar
import com.example.goproapplication.TeacherCourseViewScreen
import com.example.goproapplication.TeacherNav
import com.example.goproapplication.TeacherNavBar
import com.example.goproapplication.components.ButtonComponent
import com.example.goproapplication.components.ClickableLoginComponent
import com.example.goproapplication.components.DividerTextComponent
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.MyTextFieldComponent
import com.example.goproapplication.components.PasswordTextFieldComponent
import com.example.goproapplication.components.PinTextFieldComponent
import com.example.goproapplication.components.UnderlinedTextComponent
import com.example.goproapplication.data.login.LoginUIEvent
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler

@Composable
fun StudentLoginScreen(navController: NavController,teacherLoginViewModel: TeacherLoginViewModel){

    val context = LocalContext.current
    val loginSuccess by teacherLoginViewModel.loginSuccess

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
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.student_log_in_text))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email),
                onTextSelected = {
                    teacherLoginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                errorStatus = teacherLoginViewModel.loginUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.password),
                onTextSelected = {
                    teacherLoginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                errorStatus = teacherLoginViewModel.loginUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnderlinedTextComponent(
                value = stringResource(id = R.string.forgot_password),
                onForgotClicked = { GoProAppRoute.navigateTo(Screen.ForgotPasswordScreen) }
            )


            ButtonComponent(
                value = stringResource(id = R.string.log_in_text),
                onButtonClicked = {
                    if (loginSuccess) {
                        navController.navigate("teacherNav")
                    }
                    teacherLoginViewModel.login(context)
                    // Navigate to TeacherNav when login is successful

                },
//                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                isEnabled = teacherLoginViewModel.allValidationPassed.value
            )
            Spacer(modifier = Modifier.height(30.dp))
            DividerTextComponent()
            Spacer(modifier = Modifier.height(30.dp))
            ClickableLoginComponent(
                tryingToLogin = false,
                onTextSelected = {
                    GoProAppRoute.navigateTo(Screen.TeacherSignUpScreen)
                })
        }
        if(teacherLoginViewModel.loginInProgress.value){
            CircularProgressIndicator()
        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.WelcomeScreen)
    }
}

