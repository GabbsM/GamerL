package com.gaby.gameotekacompose.presentation.navigation

sealed class AppScreen(val route: String) {

    object  Login: AppScreen("login")
    object  SignUp: AppScreen("signup")
    object  Profile: AppScreen ("profile")

}
