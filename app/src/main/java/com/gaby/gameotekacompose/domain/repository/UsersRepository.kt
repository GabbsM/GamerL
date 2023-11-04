package com.gaby.gameotekacompose.domain.repository

import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun create(user: User):Response<Boolean>
    fun getUserById(id:String): Flow<User>

}