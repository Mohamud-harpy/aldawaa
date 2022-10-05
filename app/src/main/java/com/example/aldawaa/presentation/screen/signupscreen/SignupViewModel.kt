package com.example.aldawaa.presentation.screen.signupscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aldawaa.domain.usecases.Signupusecase.*
import com.example.aldawaa.presentation.screen.signupscreen.component.SignupFormEvent
import com.example.aldawaa.presentation.screen.signupscreen.component.SignupFormState

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignupViewModel(
    private val SignupvalidateName: SignupValidateName = SignupValidateName(),
    private val SignupvalidateTitle: SignupValidateTitle = SignupValidateTitle(),
    private val SignupvalidateGender: SignupValidateGender = SignupValidateGender(),
    private val SignupvalidateEmail: SignupValidateEmail = SignupValidateEmail(),
    private val SignupvalidatePhone: SignupValidatePhone = SignupValidatePhone(),
    private val SignupvalidateBirthday: SignupValidateBirthday = SignupValidateBirthday(),
    private val SignupvalidatePassword: SignupValidatePassword = SignupValidatePassword(),
    private val SignupvalidateConfirmPassword: SignupValidateConfirmPassword = SignupValidateConfirmPassword(),
): ViewModel() {

    var state by mutableStateOf(SignupFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignupFormEvent) {
        when(event) {
            is SignupFormEvent.SignupNameChanged -> {
                state = state.copy(signupname = event.signupname)
            }
            is SignupFormEvent.SignupTitleChanged -> {
                state = state.copy(signuptitle = event.signuptitle)
            }
            is SignupFormEvent.SignupGenderChanged -> {
                state = state.copy(signupgender = event.signupgender)
            }
            is SignupFormEvent.SignupEmailChanged -> {
                state = state.copy(signupemail = event.signupemail)
            }
            is SignupFormEvent.SignupPhoneChanged -> {
                state = state.copy(signupphone = event.signupphone)
            }
            is SignupFormEvent.SignupBirthdayChanged -> {
                state = state.copy(signupbirthday = event.signupbirthday)
            }
            is SignupFormEvent.SignupPasswordChanged -> {
                state = state.copy(signuppassword = event.signuppassword)
            }
            is SignupFormEvent.SignupConfirmPasswordChanged -> {
                state = state.copy(signupconfirmPassword = event.signupconfirmPassword)
            }
            is SignupFormEvent.Signup -> {
                SignupData()
            }
        }
    }

    private fun SignupData() {
        val signupnameResult = SignupvalidateName.execute(state.signupname)
        val signuptitleResult = SignupvalidateTitle.execute(state.signuptitle)
        val signupgenderResult = SignupvalidateGender.execute(state.signupgender)
        val signupemailResult = SignupvalidateEmail.execute(state.signupemail)
        val signupphoneResult = SignupvalidatePhone.execute(state.signupphone)
        val signupbirthdayResult = SignupvalidateBirthday.execute(state.signupbirthday)
        val signuppasswordResult = SignupvalidatePassword.execute(state.signuppassword)
        val signupconfirmPasswordResult = SignupvalidateConfirmPassword.execute(
            state.signuppassword, state.signupconfirmPassword
        )

        val hasError = listOf(
            signupnameResult,
            signuptitleResult,
            signupgenderResult,
            signupemailResult,
            signupphoneResult,
            signupbirthdayResult,
            signuppasswordResult,
            signupconfirmPasswordResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                signupnameError = signupnameResult.errorMessage,
                signuptitleError = signuptitleResult.errorMessage,
                signupgenderError = signupgenderResult.errorMessage,
                signupemailError = signupemailResult.errorMessage,
                signupphoneError = signupphoneResult.errorMessage,
                signupbirthdayError = signupbirthdayResult.errorMessage,
                signuppasswordError = signuppasswordResult.errorMessage,
                signupconfirmPasswordError = signupconfirmPasswordResult.errorMessage,

            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}