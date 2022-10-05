package com.example.aldawaa.domain.usecases.Loginusecase

class LoginValidatePassword {

    fun execute(password: String): LoginValidationResult {
        if(password.length < 8) {
            return LoginValidationResult(
                successful = false,
                errorMessage = "The password needs to consist of at least 8 characters"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return LoginValidationResult(
                successful = false,
                errorMessage = "The password needs to contain at least one letter and digit"
            )
        }
        return LoginValidationResult(
            successful = true
        )
    }
}