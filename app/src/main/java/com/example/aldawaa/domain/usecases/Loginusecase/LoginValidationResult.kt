package com.example.aldawaa.domain.usecases.Loginusecase

data class LoginValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
