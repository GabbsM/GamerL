package com.gaby.gameotekacompose.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gaby.gameotekacompose.presentation.components.DefaultButton
import com.gaby.gameotekacompose.presentation.navigation.AppScreen
import com.gaby.gameotekacompose.presentation.screens.profile.components.ProfileContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController,viewModel: ProfileViewModel = hiltViewModel()){

    Scaffold (
        topBar = {},
        content = {
        ProfileContent(navController)
        },
        bottomBar = {}
    )
}