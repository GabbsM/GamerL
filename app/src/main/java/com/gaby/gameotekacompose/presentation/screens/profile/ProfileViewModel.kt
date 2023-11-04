package com.gaby.gameotekacompose.presentation.screens.profile

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gameotekacompose.domain.use_cases.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.gaby.gameotekacompose.domain.model.User.*
import com.gaby.gameotekacompose.domain.use_cases.users.UsersUseCases
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCases: UsersUseCases)
    : ViewModel (){



    var userData by mutableStateOf(com.gaby.gameotekacompose.domain.model.User())
        private set

    val currentUser = authUseCase.getCurrentUser()

    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
            usersUseCases.getUserById(currentUser!!.uid).collect(){
                userData = it
            }
    }


    fun logout(){
        authUseCase.logout()
    }


}


