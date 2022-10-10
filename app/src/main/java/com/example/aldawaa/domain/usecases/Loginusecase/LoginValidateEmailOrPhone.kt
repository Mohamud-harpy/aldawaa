package com.example.aldawaa.domain.usecases.Loginusecase

import android.util.Patterns

class LoginValidateEmailOrPhone {
    fun execute(emailorphone: String): LoginValidationResult {
        if(emailorphone.isBlank()) {
            return LoginValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailorphone).matches()||!Patterns.PHONE.matcher(emailorphone).matches()) {
            return LoginValidationResult(
                successful = false,
                errorMessage = "That's not a valid "
            )
        }
        return LoginValidationResult(
            successful = true
        )
    }
}