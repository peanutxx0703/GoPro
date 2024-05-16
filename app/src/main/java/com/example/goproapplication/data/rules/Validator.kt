package com.example.goproapplication.data.rules

object Validator {
    fun validateName(name : String) : ValidationResult{
        return  ValidationResult(
            (!name.isNullOrEmpty() && name.length >= 3)
        )
    }
    fun validateEmail(email : String) : ValidationResult{
        val isValidFormat = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return  ValidationResult(
            (!email.isNullOrEmpty() && isValidFormat)
        )
    }
    fun validatePassword(password : String) : ValidationResult{
        return  ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 6)
        )
    }
}

data class ValidationResult(
    val status : Boolean = false
)