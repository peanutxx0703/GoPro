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
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.goproapplication.screens.ForgotPasswordScreen
import com.example.goproapplication.screens.WelcomeScreen
import com.example.goproapplication.student.login.StudentLoginScreen
import com.example.goproapplication.student.profile.StudentProfileScreen
import com.example.goproapplication.student.settings.StudentChangePasswordScreen
import com.example.goproapplication.student.settings.StudentContactUsScreen
import com.example.goproapplication.student.settings.StudentPrivacyPolicyScreen
import com.example.goproapplication.student.settings.StudentSettingsScreen
import com.example.goproapplication.student.settings.StudentTermsAndConditionsScreen
import com.example.goproapplication.student.signup.StudentSignUpScreen
import com.example.goproapplication.teacher.profile.TeacherProfileScreen
import com.example.goproapplication.teacher.settings.TeacherChangePasswordScreen
import com.example.goproapplication.teacher.settings.TeacherContactUsScreen
import com.example.goproapplication.teacher.settings.TeacherPrivacyPolicyScreen
import com.example.goproapplication.teacher.settings.TeacherSettingsScreen
import com.example.goproapplication.teacher.settings.TeacherTermsAndConditionsScreen
import com.example.goproapplication.teacher.signup.TeacherSignUpScreen
import com.example.goproapplication.ui.theme.AnnouncementScreen
import com.example.goproapplication.ui.theme.GenerateTuitionFeeMonthly
import com.example.goproapplication.ui.theme.PostAnnouncement
import com.example.goproapplication.ui.theme.StudentAnnouncementScreen
import com.example.goproapplication.ui.theme.StudentDashboardScreen
import com.example.goproapplication.ui.theme.TeacherDashboardScreen

@Composable
fun MyApp(){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home" // Set your initial destination
    ) {
        composable("home"){ SplashScreen(navController = navController) }
        composable("welcome") { WelcomeScreen(navController = navController) }
        composable("forgotPassword") { ForgotPasswordScreen() }

        // Teacher screens
        composable("teacherLogin") {
            TeacherLoginScreen(navController = navController, teacherLoginViewModel = TeacherLoginViewModel())
        }
        composable("teacherSignUp") { TeacherSignUpScreen(SignUpViewModel()) }
        composable("teacherProfile") { TeacherProfileScreen(ProfileViewModel()) }
        composable("teacherSettings") { TeacherSettingsScreen(TeacherLoginViewModel()) }
        composable("teacherChangePassword") { TeacherChangePasswordScreen() }
        composable("teacherContactUs") { TeacherContactUsScreen() }
        composable("teacherTermsAndConditions") { TeacherTermsAndConditionsScreen() }
        composable("teacherPrivacyPolicy") { TeacherPrivacyPolicyScreen() }

        // Student screens
        composable("studentLogin") { StudentLoginScreen(StudentLoginViewModel()) }
        composable("studentSignUp") { StudentSignUpScreen(SignUpViewModel()) }
        composable("studentProfile") { StudentProfileScreen(ProfileViewModel()) }
        composable("studentSettings") { StudentSettingsScreen(StudentLoginViewModel()) }
        composable("studentChangePassword") { StudentChangePasswordScreen() }
        composable("studentContactUs") { StudentContactUsScreen() }
        composable("studentTermsAndConditions") { StudentTermsAndConditionsScreen() }
        composable("studentPrivacyPolicy") { StudentPrivacyPolicyScreen() }

        // Other screens
        composable("courseView") { CourseViewScreen() }
        composable("teacherCourseView") { TeacherCourseViewScreen() }
        composable("announcement") { AnnouncementScreen() }
        composable("teacherDashboard") { TeacherDashboardScreen() }
        composable("postAnnouncement") { PostAnnouncement() }
        composable("teacherNav") { TeacherNavBar() }
        composable("studentDashboard") { StudentDashboardScreen() }
        composable("generateTuitionFeeMonthly") { GenerateTuitionFeeMonthly() }
        composable("studentAnnouncement") { StudentAnnouncementScreen() }
        composable("studentNav") { StudentNavBar() }
    }
}
@Composable
fun TeacherLoginScreen(navController: NavController,teacherLoginViewModel: TeacherLoginViewModel){

    val context = LocalContext.current
    val expectedPin = "GoPro123***"
    var isPinCorrect by remember { mutableStateOf(false) }
    var enteredPin by remember { mutableStateOf("") }
    val onPinEntered: (String) -> Unit = { enteredPin ->
        isPinCorrect = enteredPin == expectedPin
    }
    if (enteredPin == expectedPin) { isPinCorrect = true }

    // Observe login success state
    //val loginSuccess by teacherLoginViewModel.loginSuccess

    // Navigate to TeacherNav when login is successful
    //if (loginSuccess) {
        //navController.navigate("teacherNav")
    //}
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
            HeadingTextComponent(value = stringResource(id = R.string.teacher_log_in_text))
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
            PinTextFieldComponent(
                labelValue = stringResource(id = R.string.authentication_pin),
                painterResource(id = R.drawable.password),
                expectedPin = expectedPin,
                onPinEntered = onPinEntered
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnderlinedTextComponent(
                value = stringResource(id = R.string.forgot_password),
                onForgotClicked = { GoProAppRoute.navigateTo(Screen.ForgotPasswordScreen) }
            )
            ButtonComponent(
                value = stringResource(id = R.string.log_in_text),
                onButtonClicked = {
                    teacherLoginViewModel.login(context)
                                  },
//                    loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                isEnabled = teacherLoginViewModel.allValidationPassed.value && isPinCorrect
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

