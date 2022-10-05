package com.example.aldawaa.presentation.screen.signupscreen.component

data class SignupFormState(
    val signupname: String = "",
    val signupnameError: String ?= null,
    val signuptitle: String = "",
    val signuptitleError: String ?= null,
    val signupgender: String = "",
    val signupgenderError: String ?= null,
    val signupemail: String = "",
    val signupemailError: String? = null,
    val signupphone: String = "",
    val signupphoneError: String? = null,
    val signupbirthday: String = "",
    val signupbirthdayError: String ?= null,
    val signuppassword: String = "",
    val signuppasswordError: String? = null,
    val signupconfirmPassword: String = "",
    val signupconfirmPasswordError: String? = null,

)
