package com.example.goproapplication.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object WelcomeScreen : Screen()
    object ForgotPasswordScreen : Screen()

    object TeacherSignUpScreen : Screen()
    object TeacherLoginScreen : Screen()
    object TeacherProfileScreen : Screen()
    object TeacherSettingsScreen : Screen()
    object TeacherChangePasswordScreen : Screen()
    object TeacherContactUsScreen : Screen()
    object TeacherTermsAndConditionsScreen : Screen()
    object TeacherPrivacyPolicyScreen : Screen()

    object StudentSignUpScreen : Screen()
    object StudentLoginScreen : Screen()
    object StudentProfileScreen : Screen()
    object StudentSettingsScreen : Screen()
    object StudentChangePasswordScreen : Screen()
    object StudentContactUsScreen : Screen()
    object StudentTermsAndConditionsScreen : Screen()
    object StudentPrivacyPolicyScreen : Screen()
}

object GoProAppRoute {
    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.WelcomeScreen)

    fun navigateTo(destination : Screen) {
        currentScreen.value = destination
    }
}