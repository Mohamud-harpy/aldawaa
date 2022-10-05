package com.example.aldawaa.domain.usecases.Signupusecase

class SignupValidatePassword {

    fun execute(password: String): SignupValidationResult {
        if(password.length < 8) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return SignupValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }
        return SignupValidationResult(
            successful = true
        )
    }
}