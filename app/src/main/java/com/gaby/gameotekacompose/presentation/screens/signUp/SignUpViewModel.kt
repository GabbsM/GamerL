package com.gaby.gameotekacompose.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.model.User
import com.gaby.gameotekacompose.domain.use_cases.auth.AuthUseCase
import com.gaby.gameotekacompose.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase, private val usersUseCases: UsersUseCases) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(SignUpState())


    //USERNAME
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    //MAIL
    var isMailValid by mutableStateOf(false)
        private set
    var emailErrorMsg by mutableStateOf("")

    //PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMsg by mutableStateOf("")

    //CONFIRM PASSWORD
    var isConfirmedPassword by mutableStateOf(false)
        private set
    var ConfirmPasswordErrMsg by mutableStateOf("")
        private set



    var SignupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set


    var user = User()

    fun onSignUp() {
        user.username = state.username
        user.email = state.email
        user.password = state.password


        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCase.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        SignupResponse = Response.Loading
        val result = authUseCase.signUp(user)
        SignupResponse = result
    }

    //Button
    var isEnableLoginButton = false

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordlInput(password: String) {
        state = state.copy(password = password)
    }

    fun onUserNamelInput(username: String) {
        state = state.copy(username = username)
    }

    fun onConfirmPasswordlInput(confirmpassword: String) {
        state = state.copy(confirmpassword = confirmpassword)
    }

    fun enableLoginButton() {
        isEnableLoginButton =
                    isMailValid && isPasswordValid && isConfirmedPassword && isUsernameValid
    }

    //VALIDAR EMAIL

    fun validateEmail() {
        //Es un email valido:
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isMailValid = true
            emailErrorMsg = ""
        } else {
            isMailValid = false
            emailErrorMsg = "El email no es valido"
        }
        enableLoginButton()
    }

    //VALIDAR CONTRASEÑA

    fun validatePassword() {
        //Es un password valido:
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrorMsg = ""
        } else {
            isPasswordValid = false
            passwordErrorMsg = "La contrasenia debe tener como minimo 6 caracters"
        }
        enableLoginButton()
    }

    //VALIDAR CONTRASEÑA

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = ""
        } else {
            isUsernameValid = false
            usernameErrMsg = "El nombre de usuario debe tener como mínimo 5 caracteres"
        }
        enableLoginButton()

    }

    //VALIDAR CONFIRMACION CONTRASEÑA

    fun validateConfirmPassword() {
        if (state.password == state.confirmpassword) {
            isConfirmedPassword = true
            ConfirmPasswordErrMsg = ""
        } else {
            isConfirmedPassword = false
            ConfirmPasswordErrMsg = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

}