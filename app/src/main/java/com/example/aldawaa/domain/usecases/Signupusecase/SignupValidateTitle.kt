package com.example.aldawaa.domain.usecases.Signupusecase

import android.util.Patterns

class SignupValidateTitle {

    fun execute(title: String): SignupValidationResult {
        if(title.isBlank()) {
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