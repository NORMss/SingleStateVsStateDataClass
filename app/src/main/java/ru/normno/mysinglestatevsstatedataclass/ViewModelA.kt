package ru.normno.mysinglestatevsstatedataclass

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ViewModelA : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    val isEmailValid = email
        .map {
            it.isValidEmail()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false,
        )

    val isPasswordValid = email
        .map {
            it.isValidPassword()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false,
        )

    val canRegister = combine(
        isEmailValid,
        isPasswordValid,
    ) { isEmailValid, isPasswordValid ->
        isPasswordValid && isEmailValid
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false,
    )

    fun updateEmail(email: String) {
        _email.update { email }
    }

    fun updatePassword(password: String) {
        _password.update { password }
    }
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() =
    !TextUtils.isEmpty(this) && !contains(" ")
