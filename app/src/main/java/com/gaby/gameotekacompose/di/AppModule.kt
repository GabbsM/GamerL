package com.gaby.gameotekacompose.di

import com.gaby.gameotekacompose.core.Constants.USERS
import com.gaby.gameotekacompose.data.repository.AuthRepositoryImpl
import com.gaby.gameotekacompose.data.repository.UsersRepositoryImpl
import com.gaby.gameotekacompose.domain.repository.AuthRepository
import com.gaby.gameotekacompose.domain.repository.UsersRepository
import com.gaby.gameotekacompose.domain.use_cases.auth.AuthUseCase
import com.gaby.gameotekacompose.domain.use_cases.auth.GetCurrentUser
import com.gaby.gameotekacompose.domain.use_cases.auth.Login
import com.gaby.gameotekacompose.domain.use_cases.auth.Logout
import com.gaby.gameotekacompose.domain.use_cases.auth.SignUp
import com.gaby.gameotekacompose.domain.use_cases.users.Create
import com.gaby.gameotekacompose.domain.use_cases.users.GetUserById
import com.gaby.gameotekacompose.domain.use_cases.users.UsersUseCases
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl:AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun providesUsersRepository(impl:UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db:FirebaseFirestore):CollectionReference = db.collection(USERS)

    @Provides
    fun provideUserUseCase(repository: UsersRepository ) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )
}