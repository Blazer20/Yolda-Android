package com.yolda.presentation.viewModel.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.TWO
import com.yolda.domain.GoogleAuthUiClient
import com.yolda.presentation.ui.composable.navigation.MainSections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val currentPage: Int = 0,
    val enabledBottomTabs: List<String> = listOf(MainSections.SettingsSection.destination)
)

sealed class OnBoardingNavEvent {
    object ToAuthenticationSection : OnBoardingNavEvent()
    object ToFuelStationSection : OnBoardingNavEvent()
}

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnBoardingUiState())
    val uiState: StateFlow<OnBoardingUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<OnBoardingNavEvent>()
    val navEvent: SharedFlow<OnBoardingNavEvent> = _navEvent.asSharedFlow()

    fun loggedInCheck() {
        viewModelScope.launch(Dispatchers.IO) {
            if(googleAuthUiClient.getSignedInUser() != null) {
                _navEvent.emit(OnBoardingNavEvent.ToFuelStationSection)
            }
        }
    }

    fun saveCurrentPage(currentPage: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(currentPage = currentPage) }
        }
    }

    fun onTransitionButtonClick(currentPage: Int) {
        viewModelScope.launch {
            if (currentPage > TWO)
                _navEvent.emit(OnBoardingNavEvent.ToAuthenticationSection)
        }
    }

    fun onSkipClicked() {
        viewModelScope.launch { _navEvent.emit(OnBoardingNavEvent.ToAuthenticationSection) }
    }
}