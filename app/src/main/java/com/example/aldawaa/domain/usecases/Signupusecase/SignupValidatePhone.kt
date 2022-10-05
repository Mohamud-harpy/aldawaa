package com.example.aldawaa.domain.usecases.Signupusecase

import android.util.Patterns

class SignupValidatePhone {

    fun execute(Phone: String): SignupValidationResult {
        if(Phone.isBlank()) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.PHONE.matcher(Phone).matches()) {
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