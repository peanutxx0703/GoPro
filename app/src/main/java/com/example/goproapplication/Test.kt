package com.example.goproapplication

import ProfileViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.RoleButtonComponent
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.screens.ForgotPasswordScreen
import com.example.goproapplication.screens.WelcomeScreen
import com.example.goproapplication.student.profile.StudentProfileScreen
import com.example.goproapplication.student.settings.StudentChangePasswordScreen
import com.example.goproapplication.student.settings.StudentContactUsScreen
import com.example.goproapplication.student.settings.StudentPrivacyPolicyScreen
import com.example.goproapplication.student.settings.StudentSettingsScreen
import com.example.goproapplication.student.settings.StudentTermsAndConditionsScreen
import com.example.goproapplication.student.signup.StudentSignUpScreen
import com.example.goproapplication.teacher.login.StudentLoginScreen
import com.example.goproapplication.teacher.login.TeacherLoginScreen
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
fun GoPro(){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash" // Set your initial destination
    ) {
        composable("splash"){ SplashScreen(navController = navController) }
        composable("welcome") { WelcomeScreen() }
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
        composable("studentLogin") { StudentLoginScreen(navController = rememberNavController(),TeacherLoginViewModel()) }
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
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
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
            Image(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            HeadingTextComponent(value = stringResource(id = R.string.welcome_option))
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = {
                    navController.navigate("teacherLogin")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(30.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(40.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
                            shape = RoundedCornerShape(40.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Teacher",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.regular_font))
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp)) // Add a spacer between buttons
            Button(
                onClick = {
                    navController.navigate("studentLogin")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(30.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(40.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
                            shape = RoundedCornerShape(40.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Student",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.regular_font))
                    )
                }
            }
        }
    }
}
