package com.sagar.nirfpredictor.ui.theme.login

sealed class SignupFormEvent {
    data class UsernameChanged(val name:String) : SignupFormEvent()
    data class EmailChanged(val email:String) : SignupFormEvent()
    data class PasswordChanged(val password: String): SignupFormEvent()

    object Signup: SignupFormEvent()
    object Login: SignupFormEvent()
}