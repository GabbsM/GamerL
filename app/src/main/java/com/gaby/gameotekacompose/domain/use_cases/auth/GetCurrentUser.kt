package com.gaby.gameotekacompose.domain.use_cases.auth

import com.gaby.gameotekacompose.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() =  repository.currentUser
}