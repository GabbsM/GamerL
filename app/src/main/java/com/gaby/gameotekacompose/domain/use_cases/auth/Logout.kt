package com.gaby.gameotekacompose.domain.use_cases.auth

import androidx.compose.runtime.Composable
import com.gaby.gameotekacompose.domain.repository.AuthRepository
import javax.inject.Inject


class Logout @Inject constructor (private val repository: AuthRepository){

    operator fun invoke () = repository.logout()
}