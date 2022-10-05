package com.example.aldawaa.domain.usecases.Signupusecase

data class SignupValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
