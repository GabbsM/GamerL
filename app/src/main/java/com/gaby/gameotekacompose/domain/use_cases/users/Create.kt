package com.gaby.gameotekacompose.domain.use_cases.users

import com.gaby.gameotekacompose.domain.model.User
import com.gaby.gameotekacompose.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)
}