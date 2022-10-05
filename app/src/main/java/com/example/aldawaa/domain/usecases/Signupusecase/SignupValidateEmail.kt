package com.example.aldawaa.domain.usecases.Signupusecase

import android.util.Patterns

class SignupValidateEmail {

    fun execute(email: String): SignupValidationResult {
        if(email.isBlank()) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "That's not a valid email"
            )
        }
        return SignupValidationResult(
            successful = true
        )
    }
}