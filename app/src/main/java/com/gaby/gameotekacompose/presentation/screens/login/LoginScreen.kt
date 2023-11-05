package com.gaby.gameotekacompose.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gaby.gameotekacompose.presentation.screens.login.components.LoginBottomBar
import com.gaby.gameotekacompose.presentation.screens.login.components.LoginContent
import com.gaby.gameotekacompose.presentation.ui.theme.GameOTekaComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {


    Scaffold(topBar = {},
        content = { LoginContent(navController) },
        bottomBar = { LoginBottomBar(navController) })

    //MANEJAR EL ESTADO DE LA PETICION DE LOGIN
    LoginContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)


//Previsualizacion en LoginScren
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GameOTekaComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}