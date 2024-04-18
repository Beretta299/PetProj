package com.karas.petproj.main

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karas.petproj.data.ErrorStates
import com.karas.petproj.data.RegistrationUIState
import com.karas.petproj.data.UIStates
import com.karas.petproj.db.data.UserEntity
import com.karas.petproj.repository.YouTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val youTRepository: YouTRepository) : ViewModel() {
    private var _registrationUIState = mutableStateOf(RegistrationUIState())
    var registrationErrorState = mutableStateOf(ErrorStates())
    var buttonUIState = mutableStateOf(false)

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
                registrationErrorState.value = registrationErrorState.value.copy(hasRepeatPasswordError = (!_registrationUIState.value.password.equals(event.repeatPassword)) and isValidPass(event.repeatPassword))
            }
        }


        with(registrationErrorState.value) {
            buttonUIState.value = (!hasEmailError and !hasNameError and !hasPasswordError and !hasRepeatPasswordError)
        }
    }

    public fun register() {
        if(buttonUIState.value) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    youTRepository.addUser(UserEntity(null,
                        _registrationUIState.value.name,
                        _registrationUIState.value.email,
                        _registrationUIState.value.password))
                }
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