package com.karas.petproj.data

data class ErrorStates(var hasNameError: Boolean = false,
                        var hasEmailError: Boolean = false,
                        var hasPasswordError: Boolean = false,
                        var hasRepeatPasswordError: Boolean = false)