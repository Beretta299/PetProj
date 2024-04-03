package com.karas.petproj.main

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.karas.petproj.data.ErrorStates
import com.karas.petproj.data.RegistrationUIState
import com.karas.petproj.data.UIStates
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private var _registrationUIState = mutableStateOf(RegistrationUIState())
    var registrationErrorState = mutableStateOf(ErrorStates())

    fun onEvent(event: UIStates) {
        when(event) {
            is UIStates.NameChanged -> {
                _registrationUIState.value.name = event.name
                registrationErrorState.value = registrationErrorState.value.copy(hasNameError = event.name.isEmpty())
            }
            is UIStates.EmailChanged -> {
                _registrationUIState.value.email = event.email
                registrationErrorState.value = registrationErrorState.value.copy(hasEmailError = !isValidEmail(event.email))
            }
            is UIStates.PasswordChanged -> {
                _registrationUIState.value.password = event.password
                registrationErrorState.value = registrationErrorState.value.copy(hasPasswordError = !isValidPass(event.password))
            }
            is UIStates.RepeatPasswordChanged -> {
                _registrationUIState.value.repeatPassword = event.repeatPassword
                registrationErrorState.value = registrationErrorState.value.copy(hasRepeatPasswordError = !_registrationUIState.value.password.equals(event.repeatPassword))
            }
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPass(pass: CharSequence): Boolean {
        return pass.isNotEmpty() && pass.length >= 8
    }
}