package com.example.aldawaa.presentation.screen.loginscreen.component

sealed class LoginFormEvent {
    data class LoginEmailOrPhoneChanged(val loginemailorphone: String) : LoginFormEvent()
    data class LoginPasswordChanged(val loginpassword: String) : LoginFormEvent()


    object login: LoginFormEvent()
}
