package com.gaby.gameotekacompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gaby.gameotekacompose.presentation.screens.LoginScreen
import com.gaby.gameotekacompose.presentation.screens.profile.ProfileScreen
import com.gaby.gameotekacompose.presentation.screens.signUp.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = AppScreen.Login.route
    ) {
        composable(route = AppScreen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = AppScreen.SignUp.route) {
            SignUpScreen(navController)

        }

        composable(route = AppScreen.Profile.route) {
            ProfileScreen(navController)

        }
    }
}
