package com.karas.petproj.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.karas.petproj.data.RegistrationUIState
import com.karas.petproj.data.UIStates
import com.karas.petproj.repository.YouTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
private val youTRepository: YouTRepository
): ViewModel() {
}