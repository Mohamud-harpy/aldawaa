package com.example.aldawaa.domain.usecases.Signupusecase

import android.util.Patterns

class SignupValidateGender {

    fun execute(gender: String): SignupValidationResult {
        if(gender.isBlank()) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }

        return SignupValidationResult(
            successful = true
        )
    }
}