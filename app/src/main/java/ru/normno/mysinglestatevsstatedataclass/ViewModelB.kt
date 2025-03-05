package ru.normno.mysinglestatevsstatedataclass

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel

class ViewModelB: ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
}