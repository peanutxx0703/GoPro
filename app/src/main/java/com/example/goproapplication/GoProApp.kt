package com.example.goproapplication

import ProfileViewModel
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.screens.ForgotPasswordScreen
import com.example.goproapplication.student.login.StudentLoginScreen
import com.example.goproapplication.teacher.login.TeacherLoginScreen
import com.example.goproapplication.student.profile.StudentProfileScreen
import com.example.goproapplication.teacher.profile.TeacherProfileScreen
import com.example.goproapplication.student.signup.StudentSignUpScreen
import com.example.goproapplication.teacher.signup.TeacherSignUpScreen
import com.example.goproapplication.screens.WelcomeScreen
import com.example.goproapplication.student.settings.StudentChangePasswordScreen
import com.example.goproapplication.student.settings.StudentContactUsScreen
import com.example.goproapplication.student.settings.StudentPrivacyPolicyScreen
import com.example.goproapplication.student.settings.StudentSettingsScreen
import com.example.goproapplication.student.settings.StudentTermsAndConditionsScreen
import com.example.goproapplication.teacher.settings.TeacherChangePasswordScreen
import com.example.goproapplication.teacher.settings.TeacherContactUsScreen
import com.example.goproapplication.teacher.settings.TeacherPrivacyPolicyScreen
import com.example.goproapplication.teacher.settings.TeacherSettingsScreen
import com.example.goproapplication.teacher.settings.TeacherTermsAndConditionsScreen
import com.example.goproapplication.ui.theme.AnnouncementScreen
import com.example.goproapplication.ui.theme.GenerateTuitionFeeMonthly
import com.example.goproapplication.ui.theme.PostAnnouncement
import com.example.goproapplication.ui.theme.StudentAnnouncementScreen
import com.example.goproapplication.ui.theme.StudentDashboardScreen
import com.example.goproapplication.ui.theme.TeacherDashboardScreen


@Composable
fun GoProApp(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = GoProAppRoute.currentScreen) { currentState->
            when(currentState.value){
                is Screen.WelcomeScreen -> WelcomeScreen(navController = rememberNavController())
                is Screen.ForgotPasswordScreen -> ForgotPasswordScreen()

                //Teacher
                is Screen.TeacherLoginScreen -> TeacherLoginScreen(navController = rememberNavController(),TeacherLoginViewModel())
                is Screen.TeacherSignUpScreen -> TeacherSignUpScreen(SignUpViewModel())
                is Screen.TeacherProfileScreen -> TeacherProfileScreen(ProfileViewModel())
                is Screen.TeacherSettingsScreen -> TeacherSettingsScreen(TeacherLoginViewModel())
                is Screen.TeacherChangePasswordScreen -> TeacherChangePasswordScreen()
                is Screen.TeacherContactUsScreen -> TeacherContactUsScreen()
                is Screen.TeacherTermsAndConditionsScreen -> TeacherTermsAndConditionsScreen()
                is Screen.TeacherPrivacyPolicyScreen -> TeacherPrivacyPolicyScreen()

                //Student
                is Screen.StudentLoginScreen -> StudentLoginScreen(StudentLoginViewModel())
                is Screen.StudentSignUpScreen -> StudentSignUpScreen(SignUpViewModel())
                is Screen.StudentProfileScreen -> StudentProfileScreen(ProfileViewModel())
                is Screen.StudentSettingsScreen -> StudentSettingsScreen(StudentLoginViewModel())
                is Screen.StudentChangePasswordScreen -> StudentChangePasswordScreen()
                is Screen.StudentContactUsScreen -> StudentContactUsScreen()
                is Screen.StudentTermsAndConditionsScreen -> StudentTermsAndConditionsScreen()
                is Screen.StudentPrivacyPolicyScreen -> StudentPrivacyPolicyScreen()

                //QR
                is Screen.CourseViewScreen -> CourseViewScreen()
                is Screen.TeacherCourseViewScreen -> TeacherCourseViewScreen()

                //WJ
                is Screen.TeacherViewScreen -> MyApp()
                is Screen.AnnouncementScreen -> AnnouncementScreen()
                is Screen.TeacherDashboardScreen -> TeacherDashboardScreen()
                is Screen.PostAnnouncementScreen -> PostAnnouncement()
                is Screen.TeacherNav -> TeacherNavBar()

                is Screen.StudentDashboardScreen -> StudentDashboardScreen()
                is Screen.GenerateTuitionFeeMonthlyScreen -> GenerateTuitionFeeMonthly()
                is Screen.StudentAnnouncementScreen -> StudentAnnouncementScreen()
                is Screen.StudentNav -> StudentNavBar()
            }
        }
    }
}


