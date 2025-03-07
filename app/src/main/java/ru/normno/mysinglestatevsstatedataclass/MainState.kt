package ru.normno.mysinglestatevsstatedataclass

data class MainState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val canRegister: Boolean = false,
)
