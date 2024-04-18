package com.karas.petproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.karas.petproj.main.LoginViewModel
import com.karas.petproj.main.MainViewModel
import com.karas.petproj.main.RegisterViewModel
import com.karas.petproj.navigation.NavGraph
import com.karas.petproj.network.YouTService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels ()

    private lateinit var startDestination: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                mainViewModel.getStartDestination()
                startDestination = mainViewModel.userLoggedState.value
            }

            withContext(Dispatchers.Main) {
                setContent {
                    val navController = rememberNavController()
                    NavGraph(loginViewModel, navController = navController, registerViewModel = registerViewModel, mainViewModel = mainViewModel, startDestination = startDestination)
                }
            }
        }
    }
}