package com.gaby.gameotekacompose.domain.use_cases.auth

import com.gaby.gameotekacompose.domain.model.User
import com.gaby.gameotekacompose.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.signUp(user)
}