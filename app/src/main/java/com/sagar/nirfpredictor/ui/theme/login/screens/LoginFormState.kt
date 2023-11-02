package com.sagar.nirfpredictor.ui.theme.login.screens

data class LoginFormState(
    val userName: String = "",
    val userNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null
)
