package com.gaby.gameotekacompose.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.model.User
import com.gaby.gameotekacompose.domain.use_cases.auth.AuthUseCase
import com.gaby.gameotekacompose.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCase: AuthUseCase,private val usersUseCases: UsersUseCases) : ViewModel() {

    //USERNAME
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")


    //CONFIRMAR CONTRASEÑA
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmedPassword: MutableState<Boolean> = mutableStateOf(false)
    var ConfirmPasswordErrMsg: MutableState<String> = mutableStateOf("")


    var email: MutableState<String> = mutableStateOf("")
    var isMailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMsg: MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMsg: MutableState<String> = mutableStateOf("")

    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow : StateFlow<Response<FirebaseUser>?> = _signupFlow

    var user = User()

    fun onSignUp(){
        user.username = username.value
        user.email = email.value
        user.password = password.value


        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCase.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        _signupFlow.value = Response.Loading
        val result = authUseCase.signUp(user)
        _signupFlow.value = result
    }

    //Button
    var isEnableLoginButton = false

    fun enableLoginButton() {
        isEnableLoginButton =
            isMailValid.value && isPasswordValid.value && isConfirmedPassword.value && isUsernameValid.value
    }

    //VALIDAR EMAIL

    fun validateEmail() {
        //Es un email valido:
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isMailValid.value = true
            emailErrorMsg.value = ""
        } else {
            isMailValid.value = false
            emailErrorMsg.value = "El email no es valido"
        }
        enableLoginButton()
    }

    //VALIDAR CONTRASEÑA

    fun validatePassword() {
        //Es un password valido:
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            passwordErrorMsg.value = ""
        } else {
            isPasswordValid.value = false
            passwordErrorMsg.value = "La contrasenia debe tener como minimo 6 caracters"
        }
        enableLoginButton()
    }

    //VALIDAR CONTRASEÑA

    fun validateUsername() {
        if (username.value.length >= 5) {
            isUsernameValid.value = true
            usernameErrMsg.value = ""
        } else {
            isUsernameValid.value = false
            usernameErrMsg.value = "El nombre de usuario debe tener como mínimo 5 caracteres"
        }
        enableLoginButton()

    }

    //VALIDAR CONFIRMACION CONTRASEÑA

    fun validateConfirmPassword() {
        if (password.value == confirmPassword.value) {
            isConfirmedPassword.value = true
            ConfirmPasswordErrMsg.value = ""
        } else {
            isConfirmedPassword.value = false
            ConfirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

}