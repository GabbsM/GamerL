package com.gaby.gameotekacompose.data.repository

import com.gaby.gameotekacompose.di.AppModule
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.model.User
import com.gaby.gameotekacompose.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth ): AuthRepository {


    //CODIGO QUE NOS PERMITE OBTENER UN USUARIO
    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    // CODIGO QUE NOS PERMITE HACER LOGIN
    override suspend fun login(email: String, password: String): Response<FirebaseUser> {

        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Response.Success(result.user!!)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            val result =firebaseAuth.createUserWithEmailAndPassword(user.email,user.password).await()
            Response.Success(result.user!!)
        }catch (e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}