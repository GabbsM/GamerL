package com.gaby.gameotekacompose.domain.use_cases.auth

import com.gaby.gameotekacompose.data.repository.AuthRepositoryImpl
import com.gaby.gameotekacompose.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository) {


    suspend operator fun invoke(email:String,password:String) = repository.login(email,password)
}