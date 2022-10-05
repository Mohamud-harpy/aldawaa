package com.example.aldawaa.domain.usecases.Signupusecase


class SignupValidateConfirmPassword {

    fun execute(password: String, repeatedPassword: String): SignupValidationResult {
        if(password != repeatedPassword) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The passwords don't match"
            )
        }
        return SignupValidationResult(
            successful = true
        )
    }
}