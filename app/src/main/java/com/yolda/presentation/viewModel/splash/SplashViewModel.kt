package com.yolda.presentation.viewModel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SplashNavEvent {
    object ToOnBoardingScreen : SplashNavEvent()
}

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val SPLASH_DELAY_MS = 2000L
    }

    private val _navEvent = MutableSharedFlow<SplashNavEvent>()
    val navEvent: SharedFlow<SplashNavEvent> get() = _navEvent.asSharedFlow()

    fun startSplashTimer() {
        viewModelScope.launch {
            delay(SPLASH_DELAY_MS)
            _navEvent.emit(SplashNavEvent.ToOnBoardingScreen)
        }
    }

}