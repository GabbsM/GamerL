package com.gaby.gameotekacompose.domain.use_cases.auth

data class AuthUseCase (

    val getCurrentUser: GetCurrentUser,
    val login:Login,
    val logout: Logout,
    val signUp: SignUp

)