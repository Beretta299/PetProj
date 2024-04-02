package com.karas.petproj.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.karas.petproj.main.MainViewModel
import com.karas.petproj.ui.components.LoginScreen
import com.karas.petproj.ui.components.RegistrationScreen

fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    viewModel: MainViewModel,
) {
    navigation(
        startDestination = NavigationItem.Login.route,
        route = MAIN_ROUTE,
    ) {
        composable(route = NavigationItem.Login.route) {
            LoginScreen(navController = navHostController, viewModel = viewModel)
        }
        composable(route = NavigationItem.Registration.route) {
            RegistrationScreen(navController = navHostController, viewModel = viewModel)
        }
    }
}