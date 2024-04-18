package com.karas.petproj.data

data class ErrorStates(var hasNameError: Boolean = true,
                        var hasEmailError: Boolean = true,
                        var hasPasswordError: Boolean = true,
                        var hasRepeatPasswordError: Boolean = true)