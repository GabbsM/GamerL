package com.gaby.gameotekacompose.presentation.screens.signUp

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gaby.gameotekacompose.presentation.components.DefaultTopBar
import com.gaby.gameotekacompose.presentation.screens.signUp.components.SignUpContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            DefaultTopBar(
                tittle = "Nuevo Usuario",
                upAvailable = true,
                navController = navController

            )
        },
        content = {
            SignUpContent(navController)
        },
        bottomBar = {}
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupScrenPreview() {
    SignUpScreen(rememberNavController())
}