package com.sagar.nirfpredictor.ui.theme.login.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.sagar.nirfpredictor.ui.theme.login.SignupFormEvent
import com.sagar.nirfpredictor.ui.theme.login.use_cases.EmailValidation
import com.sagar.nirfpredictor.ui.theme.login.use_cases.PasswordValidation
import com.sagar.nirfpredictor.ui.theme.login.use_cases.UserNameValidation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val emailValidation: EmailValidation = EmailValidation(),
    private val passwordValidation: PasswordValidation = PasswordValidation(),
    private val userNameValidation: UserNameValidation = UserNameValidation()
): ViewModel() {
    var state by mutableStateOf(LoginFormState())
    private set

    private val _channel = Channel<String?>()
    val channel = _channel.receiveAsFlow()

    fun onEvent(event: SignupFormEvent){
        when(event){
            is SignupFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignupFormEvent.UsernameChanged -> {
                state = state.copy(userName = event.name)
            }
            is SignupFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignupFormEvent.Signup -> {
                signup()
            }
            is SignupFormEvent.Login -> {
                login()
            }
        }
    }

    private fun login(){
        Log.d("TAG", "inside login")
        val emailEvent = emailValidation.execute(state.email)
        val passwordEvent = passwordValidation.execute(state.password)

        val hasError =
            setOf(
                emailEvent,
                passwordEvent,
            ) .all{it.success}

        if(!hasError){
            state = state.copy(
                emailError = emailEvent.error,
                passwordError = passwordEvent.error,
            )
            return
        }

        loginUser(state.email, state.password){result->
            Log.d("TAG", "inside function loginUser")
            viewModelScope.launch {
                _channel.send(result)
            }
        }
    }


    private fun signup(){

        val emailEvent = emailValidation.execute(state.email)
        val passwordEvent = passwordValidation.execute(state.password)
        val userNameEvent = userNameValidation.execute(state.userName)

        val noError =
            setOf(
                emailEvent,
                passwordEvent,
                userNameEvent
            ) .all{it.success}

        if(!noError){
            state = state.copy(
                emailError = emailEvent.error,
                passwordError = passwordEvent.error,
                userNameError = userNameEvent.error
            )
            return
        }

        createUser(state.email, state.password){result ->
            viewModelScope.launch {
                _channel.send(result)
            }
        }
    }

    private fun createUser(email: String, password: String, callback:(String?) -> Unit){
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {result ->
                if(result.isSuccessful){
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(it.localizedMessage)
            }
    }

    private fun loginUser(email: String, password: String, callback: (String?) -> Unit){
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if(result.isSuccessful){
                    Log.d("TAG", "Logged in successfuly")
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(it.localizedMessage)
                Log.d("TAG", it.localizedMessage)
            }
    }

}