package com.gaby.gameotekacompose.domain.use_cases.users

import com.gaby.gameotekacompose.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById  @Inject constructor(private val repository: UsersRepository){

    operator fun invoke(id:String) = repository.getUserById(id)
}