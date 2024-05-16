package com.example.goproapplication.data.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.goproapplication.data.rules.Validator
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class TeacherLoginViewModel : ViewModel() {
    private val TAG = TeacherLoginViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

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

    fun login(context: Context) {
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
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    GoProAppRoute.navigateTo(Screen.TeacherProfileScreen)
                } else {
                    Log.d(TAG, "Login failed: ${task.exception?.message}")
                    allValidationPassed.value = false
                    Toast.makeText(context, "Login failed. Please check your info and try again.", Toast.LENGTH_SHORT).show()
                }
                loginInProgress.value = false
            }
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