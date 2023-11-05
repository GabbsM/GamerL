package com.gaby.gameotekacompose.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.domain.use_cases.auth.AuthUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase) : ViewModel() {

    // ESTADO DE FORMULARIO
    var state by mutableStateOf(LoginState())
        private set

    //EMAIL

    var isMailValid by mutableStateOf(false)
        private set
    var emailErrorMsg by mutableStateOf("")
        private set


    //PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMsg by mutableStateOf("")
        private set

    //ENABLE BUTTON
    var isEnableLoginButton = false

    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCase.getCurrentUser()

    init {
        if (currentUser != null) { // SESION INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun enableLoginButton() {
        isEnableLoginButton = isMailValid && isPasswordValid
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordIpunt(password: String) {
        state = state.copy(password = password)
    }

    //LLAMADA A LA FUNCION LOGIN
    fun login() {
        viewModelScope.launch {
            loginResponse = Response.Loading
            val result = authUseCase.login(state.email, state.password)
            loginResponse = result
        }
    }


    fun validateEmail() {
        //ES UN MAIL VALIDO:
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isMailValid = true
            emailErrorMsg = ""
        } else {
            isMailValid = false
            emailErrorMsg = "El email no es válido"
        }
        enableLoginButton()
    }

    fun validatePassword() {
        //ES UN PASSWORD VALIDO:
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrorMsg = ""
        } else {
            isPasswordValid = false
            passwordErrorMsg = "La contraseña debe tener como minimo 6 caracters"
        }
        enableLoginButton()
    }
}
