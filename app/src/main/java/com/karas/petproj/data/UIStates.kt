package com.karas.petproj.data

sealed class UIStates {
    data class NameChanged(val name:String) : UIStates()
    data class EmailChanged(val email:String) : UIStates()
    data class PasswordChanged(val password:String) : UIStates()
    data class RepeatPasswordChanged(val repeatPassword:String) : UIStates()
}