package com.example.goproapplication.data.signup

data class SignUpUIState(
    var name : String = "",
    var email : String = "",
    var password : String = "",

    var nameError : Boolean = false,
    var emailError : Boolean = false,
    var passwordError: Boolean = false,
)
