package com.example.aldawaa.presentation.screen.loginscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aldawaa.domain.usecases.Loginusecase.LoginValidateEmailOrPhone
import com.example.aldawaa.domain.usecases.Loginusecase.LoginValidatePassword
import com.example.aldawaa.presentation.screen.loginscreen.component.LoginFormEvent
import com.example.aldawaa.presentation.screen.loginscreen.component.LoginFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginValidateEmailOrPhone: LoginValidateEmailOrPhone = LoginValidateEmailOrPhone(),
    private val loginValidatePassword: LoginValidatePassword = LoginValidatePassword(),
): ViewModel() {

    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when(event) {
            is LoginFormEvent.LoginEmailOrPhoneChanged -> {
                state = state.copy(loginemailorphone = event.loginemailorphone)
            }
            is LoginFormEvent.LoginPasswordChanged -> {
                state = state.copy(loginpassword = event.loginpassword)
            }
            is LoginFormEvent.login -> {
                LoginData()
            }

        }
    }

    private fun LoginData() {
        val loginemailResult = loginValidateEmailOrPhone.execute(state.loginemailorphone)
        val loginpasswordResult = loginValidatePassword.execute(state.loginpassword)

        val hasError = listOf(
            loginemailResult,
            loginpasswordResult
        ).any { !it.successful }

        if(hasError) {
            state = state.copy(
                loginemailorphoneError = loginemailResult.errorMessage,
                loginpasswordError = loginpasswordResult.errorMessage,
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