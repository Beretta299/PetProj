package com.karas.petproj.navigation


const val MAIN_ROUTE = "main"

enum class Screen {
    REGISTRATION,
    LOGIN,
    SPLASH,
    MAIN,
}
sealed class NavigationItem(val route: String) {
    object Registration : NavigationItem(Screen.REGISTRATION.name)
    object Login : NavigationItem(Screen.LOGIN.name)
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Main : NavigationItem(Screen.MAIN.name)
}