package com.example.goproapplication

import ProfileViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
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
fun MyApp(){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "teacherLogin" // Set your initial destination
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
fun SplashScreen(navController: NavController){
    Button(onClick = { navController.navigate("teacherNav") }) {
        Text(text = "Welcome!")
    }
}

@Composable
fun NavScreen(){
    Button(onClick = { GoProAppRoute.navigateTo(Screen.TeacherNav) }) {
        Text(text = "Welcome!")
    }
}

