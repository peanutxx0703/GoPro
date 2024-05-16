package com.example.goproapplication.data

sealed class SignUpUIEvent {
    data class NameChanged(val name: String) : SignUpUIEvent()
    data class EmailChanged(val email: String) : SignUpUIEvent()
    data class PasswordChanged(val password: String) : SignUpUIEvent()

    object SignUpButtonClicked : SignUpUIEvent()
}