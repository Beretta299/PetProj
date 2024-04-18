package com.karas.petproj.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karas.petproj.main.LoginViewModel
import com.karas.petproj.main.MainViewModel
import com.karas.petproj.main.RegisterViewModel
import com.karas.petproj.ui.components.LoginScreen
import com.karas.petproj.ui.components.MainScreenComponent
import com.karas.petproj.ui.components.RegistrationScreen
import com.karas.petproj.ui.components.SplashScreen

fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    mainViewModel: MainViewModel,
    startDestination: String
) {
    navigation(
        startDestination = startDestination,
        route = MAIN_ROUTE,
    ) {
        composable(route = NavigationItem.Login.route) {
            LoginScreen(navController = navHostController, viewModel = loginViewModel)
        }
        composable(route = NavigationItem.Registration.route) {
            RegistrationScreen(navController = navHostController, viewModel = registerViewModel)
        }
        composable(route = NavigationItem.Splash.route) {
            SplashScreen(navHostController = navHostController)
        }
        composable(route = NavigationItem.Main.route) {
            MainScreenComponent(mainViewModel)
        }
    }
}