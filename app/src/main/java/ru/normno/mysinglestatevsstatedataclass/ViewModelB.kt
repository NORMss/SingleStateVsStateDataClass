package ru.normno.mysinglestatevsstatedataclass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class ViewModelB : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        state
            .distinctUntilChangedBy { it.email }
            .map { it.email.isValidEmail() }
            .onEach { isEmailValid ->
                _state.update {
                    it.copy(
                        isEmailValid = isEmailValid,
                    )
                }
            }.launchIn(viewModelScope)

        state
            .distinctUntilChangedBy { it.password }
            .map { it.password.isValidPassword() }
            .onEach { isPasswordValid ->
                _state.update {
                    it.copy(
                        isPasswordValid = isPasswordValid,
                    )
                }
            }.launchIn(viewModelScope)

        state
            .onEach { state ->
                _state.update {
                    it.copy(
                        canRegister = state.isEmailValid && state.isPasswordValid,
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun updateEmail(email: String) {
        _state.update {
            it.copy(
                email = email,
            )
        }
    }

    fun updatePassword(password: String) {
        _state.update {
            it.copy(
                password = password,
            )
        }
    }
}