package com.example.rickproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickproject.navigation.Screen
import com.example.rickproject.presentation.view.CharacterDetail
import com.example.rickproject.presentation.view.CharactersScreen
import com.example.rickproject.presentation.viewmodels.CharactersViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    ) {
        composable(Screen.List.route) {
            val vm: CharactersViewModel = viewModel()
            CharactersScreen(navController, vm)
        }
        composable(Screen.Detail.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Screen.List.route)
            }
            val vm: CharactersViewModel = viewModel(parentEntry)
            CharacterDetail(vm)
        }
    }
}