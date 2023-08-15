package com.yolda.presentation.viewModel.aboutApp

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AboutAppUiState(
    val appVersionCode: String = "1.0.1"
)

sealed class AboutAppNavEvent {
    object ToBackNavigation : AboutAppNavEvent()
}

@HiltViewModel
class AboutAppViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(AboutAppUiState())
    val uiState: StateFlow<AboutAppUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<AboutAppNavEvent>()
    val navEvent: SharedFlow<AboutAppNavEvent> get() = _navEvent.asSharedFlow()

    fun onNavigationClick() {
        viewModelScope.launch { _navEvent.emit(AboutAppNavEvent.ToBackNavigation) }
    }

    fun onFeedbackClick() {

    }

    fun onRateClick() {

    }

}