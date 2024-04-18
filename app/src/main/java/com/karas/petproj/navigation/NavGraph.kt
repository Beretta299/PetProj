package com.karas.petproj.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.karas.petproj.main.LoginViewModel
import com.karas.petproj.main.MainViewModel
import com.karas.petproj.main.RegisterViewModel

@Composable
fun NavGraph(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    mainViewModel: MainViewModel,
    startDestination: String,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = MAIN_ROUTE
        ) {
            mainNavGraph(navHostController = navController, loginViewModel, registerViewModel, mainViewModel =  mainViewModel,startDestination)
        }
    }
}