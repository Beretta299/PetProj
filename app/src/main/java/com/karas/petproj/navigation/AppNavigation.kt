package com.karas.petproj.navigation


const val MAIN_ROUTE = "main"

enum class Screen {
    REGISTRATION,
    LOGIN,
}
sealed class NavigationItem(val route: String) {
    object Registration : NavigationItem(Screen.REGISTRATION.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}