package com.sagar.nirfpredictor.ui.theme.login.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sagar.nirfpredictor.ui.theme.LoginBackgroundScreen
import com.sagar.nirfpredictor.ui.theme.PredictButton
import com.sagar.nirfpredictor.ui.theme.SignupCard
import com.sagar.nirfpredictor.ui.theme.login.SignupFormEvent

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginClick: (Int) -> Unit
 ){
    val state = viewModel.state
    Box(modifier = modifier.fillMaxSize()){
        LoginBackgroundScreen()
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SignupCard(
                onEmailChange = {
                    viewModel.onEvent(SignupFormEvent.EmailChanged(it))
                },
                onPasswordChange = {
                    viewModel.onEvent(SignupFormEvent.PasswordChanged(it))
                },
                onUserNameChange = {
                    viewModel.onEvent(SignupFormEvent.UsernameChanged(it))
                },
                modifier = Modifier
                    .weight(7f),
                userNameError = state.userNameError,
                emailError = state.emailError,
                passwordError = state.passwordError,
        )
            Column(
                modifier = Modifier.weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                PredictButton(onClick = { viewModel.onEvent(SignupFormEvent.Signup)} , text = "Sign Up")
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(
                        text = "Already Have an account?  "
                    )
                    ClickableText(text = AnnotatedString(text = "Login"),
                        onClick = onLoginClick,
                        style = TextStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline)
                        )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewScreen(){
}