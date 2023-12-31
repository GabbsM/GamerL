package com.gaby.gameotekacompose.presentation.screens.signUp.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gaby.gameotekacompose.R
import com.gaby.gameotekacompose.domain.model.Response
import com.gaby.gameotekacompose.presentation.components.DefaultButton
import com.gaby.gameotekacompose.presentation.components.DefaultTextField
import com.gaby.gameotekacompose.presentation.navigation.AppScreen
import com.gaby.gameotekacompose.presentation.screens.signUp.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {

    val state = viewModel.state


    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Color(0xFF018786))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(75.dp))
                Image(
                    modifier = Modifier
                        .height(130.dp)
                        .size(100.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Registro"
                )

            }

        }
        Card(
            Modifier.padding(
                start = 40.dp, top = 200.dp, end = 40.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor, ingresa los datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                //USERNAME
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUserNamelInput(it) },
                    label = "Nombre Usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = { viewModel.validateUsername() }
                )


                Spacer(modifier = Modifier.height(10.dp))

                //MAIL
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.email,
                    onValueChange = { viewModel.onEmailInput(it) },
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    errorMsg = viewModel.emailErrorMsg,
                    validateField = { viewModel.validateEmail() }

                )


                Spacer(modifier = Modifier.height(10.dp))


                //PASWORD
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.password,
                    onValueChange = { viewModel.onPasswordlInput(it) },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrorMsg,
                    validateField = { viewModel.validatePassword() }
                )

                Spacer(modifier = Modifier.height(10.dp))


                //CONFIRM PASSWORD
                DefaultTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = state.confirmpassword,
                    onValueChange = { viewModel.onConfirmPasswordlInput(it) },
                    label = "Confirmar Contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.ConfirmPasswordErrMsg,
                    validateField = { viewModel.validateConfirmPassword() }
                )


                DefaultButton(
                    text = "REGISTRARSE",
                    onClick = { viewModel.onSignUp() },
                    enable = viewModel.isEnableLoginButton
                )
            }
        }
    }


}


