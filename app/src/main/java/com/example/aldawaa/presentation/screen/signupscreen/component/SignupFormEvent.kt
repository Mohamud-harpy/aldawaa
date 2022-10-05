package com.example.aldawaa.presentation.screen.signupscreen.component

sealed class SignupFormEvent {
    data class SignupNameChanged(val signupname: String) : SignupFormEvent()
    data class SignupTitleChanged(val signuptitle: String) : SignupFormEvent()
    data class SignupGenderChanged(val signupgender: String) : SignupFormEvent()
    data class SignupEmailChanged(val signupemail: String) : SignupFormEvent()
    data class SignupPhoneChanged(val signupphone: String) : SignupFormEvent()
    data class SignupBirthdayChanged(val signupbirthday: String) : SignupFormEvent()
    data class SignupPasswordChanged(val signuppassword: String) : SignupFormEvent()
    data class SignupConfirmPasswordChanged(val signupconfirmPassword: String) : SignupFormEvent()


    object Signup: SignupFormEvent()
}
