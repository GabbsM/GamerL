package com.gaby.gameotekacompose.presentation.screens.signUp.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.presentation.components.ProgressBar
import com.gaby.gameotekacompose.presentation.navigation.AppScreen
import com.gaby.gameotekacompose.presentation.screens.signUp.SignUpViewModel

@Composable
fun SignUp(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
    when (val signUpResponse = viewModel.SignupResponse) {
        Response.Loading -> {

            ProgressBar()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                //Borramos el historico de pantallas
                navController.popBackStack(AppScreen.Login.route, true)
                navController.navigate(route = AppScreen.Profile.route)
            }
        }

        is Response.Failure -> {

            Toast.makeText(
                LocalContext.current,
                signUpResponse.exception?.message ?: "Error desconocido",Toast.LENGTH_LONG).show()
        }


        else -> {}
    }
}

