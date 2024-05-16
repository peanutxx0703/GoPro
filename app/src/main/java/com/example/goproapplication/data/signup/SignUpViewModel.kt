package com.example.goproapplication.data.signup

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.goproapplication.data.SignUpUIEvent
import com.example.goproapplication.data.rules.Validator
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpViewModel : ViewModel(){
    private val TAG = SignUpViewModel::class.simpleName

    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidationPassed = mutableStateOf(false)
    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUIEvent, context: Context) {
        when {
            event is SignUpUIEvent.NameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(name = event.name)
                printState()
            }
            event is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(email = event.email)
                printState()
            }
            event is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(password = event.password)
                printState()
            }
            event is SignUpUIEvent.SignUpButtonClicked -> {
                signUp(context)
            }
        }
        validateDataWithRules()
    }

    private fun signUp(context: Context) {
        Log.d(TAG, "Inside_signUp")
        printState()

        createUserInFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password,
            context = context
        )
    }

    private fun validateDataWithRules() {
        val nameResult = Validator.validateName(signUpUIState.value.name)
        val emailResult = Validator.validateEmail(signUpUIState.value.email)
        val passwordResult = Validator.validatePassword(signUpUIState.value.password)

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "nameResult= $nameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")

        signUpUIState.value = signUpUIState.value.copy(
            nameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
        )

        allValidationPassed.value = nameResult.status && emailResult.status && passwordResult.status
    }

    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signUpUIState.value.toString())
    }

    private fun createUserInFirebase(email: String, password: String, context: Context) {
        signUpInProgress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                signUpInProgress.value = false
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let {
                        Log.d(TAG, "User created in FirebaseAuth with UID: ${it.uid}")
                        saveUserInFirestore(it.uid)
                        Toast.makeText(context, "Signup successful. Please login.", Toast.LENGTH_SHORT).show()
                        GoProAppRoute.navigateTo(Screen.WelcomeScreen)
                    }
                } else {
                    Log.w(TAG, "User creation failed", task.exception)
                    Toast.makeText(context, "Signup failed.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                signUpInProgress.value = false
                Log.w(TAG, "FirebaseAuth exception", e)
            }
    }

    private fun saveUserInFirestore(userId: String) {
        val usersCollection = FirebaseFirestore.getInstance().collection("users")
        val signUpData = hashMapOf(
            "name" to signUpUIState.value.name,
            "email" to signUpUIState.value.email
        )

        usersCollection.document(userId)
            .set(signUpData)
            .addOnSuccessListener {
                Log.d(TAG, "User data saved in Firestore with ID: $userId")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}