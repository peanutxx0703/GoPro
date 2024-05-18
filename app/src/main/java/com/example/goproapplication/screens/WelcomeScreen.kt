package com.example.goproapplication.screens

import ProfileViewModel
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.CourseViewScreen
import com.example.goproapplication.SplashScreen
import com.example.goproapplication.R
import com.example.goproapplication.StudentNavBar
import com.example.goproapplication.TeacherCourseViewScreen
import com.example.goproapplication.TeacherNavBar
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.components.RoleButtonComponent
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.student.login.StudentLoginScreen
import com.example.goproapplication.student.profile.StudentProfileScreen
import com.example.goproapplication.student.settings.StudentChangePasswordScreen
import com.example.goproapplication.student.settings.StudentContactUsScreen
import com.example.goproapplication.student.settings.StudentPrivacyPolicyScreen
import com.example.goproapplication.student.settings.StudentSettingsScreen
import com.example.goproapplication.student.settings.StudentTermsAndConditionsScreen
import com.example.goproapplication.student.signup.StudentSignUpScreen
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
fun WelcomeScreen(navController: NavController){
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
                    navController.navigate("teacherLogin")
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            RoleButtonComponent(
                value = stringResource(id = R.string.welcome_student),
                onClick = {
                    navController.navigate("studentLogin")
                }
            )

        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}