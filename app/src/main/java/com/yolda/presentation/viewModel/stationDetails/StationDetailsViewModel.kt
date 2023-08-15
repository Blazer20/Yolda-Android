package com.yolda.presentation.viewModel.stationDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.emptyStation
import com.yolda.data.remote.Station
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

data class StationDetailsUiState(
    val stationData: Station = emptyStation
)

sealed class StationDetailsNavEvent {
    object ToBackNavigation : StationDetailsNavEvent()
}

@HiltViewModel
class StationDetailsViewModel @Inject constructor() : ViewModel() {

    private val _navEvent = MutableSharedFlow<StationDetailsNavEvent>()
    val navEvent: SharedFlow<StationDetailsNavEvent> get() = _navEvent.asSharedFlow()

    private val _uiState = MutableStateFlow(StationDetailsUiState())
    val uiState: StateFlow<StationDetailsUiState> = _uiState.asStateFlow()

    fun saveNavArguments(stationData: Station) {
        _uiState.update { it.copy(stationData = stationData) }
    }

    fun onNavigationClick() {
        viewModelScope.launch { _navEvent.emit(StationDetailsNavEvent.ToBackNavigation) }
    }

}