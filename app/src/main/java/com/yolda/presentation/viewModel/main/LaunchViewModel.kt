package com.yolda.presentation.viewModel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.OUT_NAVIGATION_DURATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LaunchNavEvent {
    object ToFuelStationScreen : LaunchNavEvent()
}

@HiltViewModel
class LaunchViewModel @Inject constructor() : ViewModel() {

    private val _navEvent = MutableSharedFlow<LaunchNavEvent>()
    val navEvent: SharedFlow<LaunchNavEvent> get() = _navEvent.asSharedFlow()

    fun continueTransition() {
        viewModelScope.launch {
            delay(OUT_NAVIGATION_DURATION)
            _navEvent.emit(LaunchNavEvent.ToFuelStationScreen)
        }
    }
}