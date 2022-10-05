package com.example.aldawaa.domain.usecases.Signupusecase

import android.util.Patterns

class SignupValidateBirthday {

    fun execute(birthday: String): SignupValidationResult {
        if(birthday.isBlank()) {
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