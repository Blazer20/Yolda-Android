package com.yolda.presentation.viewModel.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.EMPTY_STRING
import com.yolda.common.ZERO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LanguageUiState(
    val savedLanguageItem: LanguageModel = LanguageModel()
)

data class LanguageModel(
    val id: Int = ZERO,
    val language: String = EMPTY_STRING
)

sealed class LanguageNavEvent {
    object ToBackStackNavigation : LanguageNavEvent()
}

@HiltViewModel
class LanguageViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LanguageUiState())
    val uiState: StateFlow<LanguageUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<LanguageNavEvent>()
    val navEvent: SharedFlow<LanguageNavEvent> get() = _navEvent.asSharedFlow()

    fun onNavigationCLick() =
        viewModelScope.launch { _navEvent.emit(LanguageNavEvent.ToBackStackNavigation) }

    fun onLanguageSelected(language: LanguageModel) {
        /* TODO: Add logic for selected language */
    }
}