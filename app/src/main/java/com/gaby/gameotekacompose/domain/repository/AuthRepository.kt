package com.gaby.gameotekacompose.domain.repository

import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    val currentUser: FirebaseUser?

    suspend fun login(email:String, password:String): Response<FirebaseUser>
    suspend fun signUp(user:User):Response<FirebaseUser>
    fun logout()





}