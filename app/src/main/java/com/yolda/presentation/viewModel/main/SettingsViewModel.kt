package com.yolda.presentation.viewModel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.SnackBarManager
import com.yolda.common.emptyUserData
import com.yolda.data.remote.UserData
import com.yolda.domain.GoogleAuthUiClient
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

data class SettingsUiState(
    val userData: UserData = emptyUserData,
    val isLoading: Boolean = false
)

sealed class SettingsNavEvents {
    object ToLanguageScreen : SettingsNavEvents()
    object ToAboutAppScreen : SettingsNavEvents()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val snackBarManager: SnackBarManager,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<SettingsNavEvents>()
    val navEvent: SharedFlow<SettingsNavEvents> get() = _navEvent.asSharedFlow()

    fun getUser() {
        viewModelScope.launch {
            changeProgressBarVisibility()
            val userInf = googleAuthUiClient.getSignedInUser() ?: emptyUserData
            _uiState.update { it.copy(userData = userInf, isLoading = false) }
        }
    }

    fun onLanguageClick() {
        viewModelScope.launch { _navEvent.emit(SettingsNavEvents.ToLanguageScreen) }
    }

    fun onTechSupportClick() {
        /* TODO: Add logic */
    }

    fun onAboutAppClick() {
        viewModelScope.launch { _navEvent.emit(SettingsNavEvents.ToAboutAppScreen) }
    }

    private fun changeProgressBarVisibility() { _uiState.update { it.copy(isLoading = !it.isLoading) }}
}