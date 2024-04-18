package com.karas.petproj.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.karas.petproj.R
import com.karas.petproj.data.UIStates
import com.karas.petproj.main.LoginViewModel
import com.karas.petproj.main.RegisterViewModel


@Composable
fun RegistrationScreen(viewModel: RegisterViewModel, navController: NavController) {
    val state = viewModel.registrationErrorState.value

    Surface(color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TitleLabel(resourceID = R.string.registration_label)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp))
                UserInputField(R.string.name_label, onTextSelected = {
                    viewModel.onEvent(UIStates.NameChanged(it))
                }, err = viewModel.registrationErrorState.value.hasNameError)
                UserInputField(R.string.email_label, leadingIcon = Icons.Rounded.MailOutline, onTextSelected = {
                    viewModel.onEvent(UIStates.EmailChanged(it))
                },err = state.hasEmailError)
                UserInputField(R.string.pass_label, leadingIcon = Icons.Rounded.Lock, true, onTextSelected = {
                    viewModel.onEvent(UIStates.PasswordChanged(it))
                },err = viewModel.registrationErrorState.value.hasPasswordError)
                UserInputField(R.string.pass_label, leadingIcon = Icons.Rounded.Lock, true, ImeAction.Done, onTextSelected = {
                    viewModel.onEvent(UIStates.RepeatPasswordChanged(it))
                }, err = viewModel.registrationErrorState.value.hasRepeatPasswordError)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp))
                ActionButton(R.string.registration_label, {

                }, isActive = viewModel.buttonUIState.value)
            }
        }
    }
}