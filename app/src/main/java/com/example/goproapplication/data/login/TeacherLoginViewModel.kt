package com.example.goproapplication.data.login

import ProfileViewModel
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.CourseViewScreen
import com.example.goproapplication.GoProApp
import com.example.goproapplication.SplashScreen
import com.example.goproapplication.StudentNavBar
import com.example.goproapplication.TeacherCourseViewScreen
import com.example.goproapplication.TeacherNav
import com.example.goproapplication.TeacherNavBar
import com.example.goproapplication.data.rules.Validator
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
import com.google.firebase.auth.FirebaseAuth


class TeacherLoginViewModel : ViewModel() {
    private val TAG = TeacherLoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    var loginSuccess = mutableStateOf(false)
    fun onEvent(event : LoginUIEvent){
        when(event){
            is  LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is  LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
//            is  LoginUIEvent.LoginButtonClicked -> {
//                login(context)
//            }
        }
        validateLoginDataWithRules()
    }

    private fun validateLoginDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        if(emailResult.status && passwordResult.status){
            allValidationPassed.value = true
        } else {
            allValidationPassed.value = false
        }
    }


    fun login(context: Context): Boolean {
        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Login successful")
                    allValidationPassed.value = true
                    loginSuccess.value = true
                    Toast.makeText(context, "Login successful ${loginSuccess.value}", Toast.LENGTH_SHORT).show()



                } else {
                    Log.d(TAG, "Login failed: ${task.exception?.message}")
                    allValidationPassed.value = false
                    loginSuccess.value = false
                    Toast.makeText(context, "Login failed. Please check your info and try again.", Toast.LENGTH_SHORT).show()

                }


            }
        return loginSuccess.value
    }

    fun logout(context: Context){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if(it.currentUser == null){
                Log.d(TAG, "Inside log out success")
                Toast.makeText(context, "Logout successful", Toast.LENGTH_SHORT).show()
                GoProAppRoute.navigateTo(Screen.WelcomeScreen)
            } else {
                Log.d(TAG, "Inside log out unsuccessful")
                Toast.makeText(context, "Logout failed", Toast.LENGTH_SHORT).show()
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}