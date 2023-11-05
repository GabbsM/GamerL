package com.gaby.gameotekacompose.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.presentation.components.ProgressBar
import com.gaby.gameotekacompose.presentation.navigation.AppScreen
import com.gaby.gameotekacompose.presentation.screens.login.LoginViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    when (val loginResponse = viewModel.loginResponse) {
        //MOSTRAR QUE SE ESTÁ REALIZANDO LA PETICIÓN Y QUE TODAVÍA ESTÁ EN PROCESO
        is Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(route = AppScreen.Profile.route) {
                    popUpTo(AppScreen.Login.route) { inclusive = true }
                }
            }
        }

        is Response.Failure -> {
            Toast.makeText(
                LocalContext.current,
                loginResponse.exception?.message ?: "Error descononovido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }
}