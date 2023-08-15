package com.yolda.presentation.viewModel.authentication

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yolda.common.EMPTY_STRING
import com.yolda.common.SnackBarManager
import com.yolda.common.emptyUserData
import com.yolda.data.remote.SignInResult
import com.yolda.data.remote.UserData
import com.yolda.domain.GoogleAuthUiClient
import com.yolda.presentation.ui.composable.navigation.MainSections
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

data class AuthenticationUiState(
    val isSuccessful: Boolean = false,
    val phoneNumber: String = EMPTY_STRING,
    val signInError: String = EMPTY_STRING,
    val userData: UserData = emptyUserData,
    val enabledBottomTabs: List<String> = listOf(MainSections.SettingsSection.destination)
)

sealed class AuthenticationNavEvent {
    object ToSplashScreen : AuthenticationNavEvent()
    object ToConfirmationScreen : AuthenticationNavEvent()
    object ToFuelStationScreen : AuthenticationNavEvent()
}

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val snackBarManager: SnackBarManager,
    private val googleAuthUiClient: GoogleAuthUiClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationUiState())
    val uiState: StateFlow<AuthenticationUiState> = _uiState.asStateFlow()

    private val _navEvent = MutableSharedFlow<AuthenticationNavEvent>()
    val navEvent: SharedFlow<AuthenticationNavEvent> get() = _navEvent.asSharedFlow()

    suspend fun signIn(): IntentSender? = googleAuthUiClient.signIn()

    suspend fun signInWithIntent(intent: Intent) =
        googleAuthUiClient.signInWithIntent(intent = intent)

    fun onSignIntResult(result: SignInResult) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isSuccessful = result.data != null,
                    signInError = result.errorMessage ?: EMPTY_STRING,
                    phoneNumber = EMPTY_STRING,
                    userData = result.data ?: emptyUserData
                )
            }
            _navEvent.emit(AuthenticationNavEvent.ToFuelStationScreen)
        }
    }

    fun onPhoneNumberChange(phoneNumber: String) {
        _uiState.update { it.copy(phoneNumber = phoneNumber) }
    }

//    fun resetState() {
//        _uiState.update { AuthenticationUiState() }
//    }

    fun onContinueClick() {
        viewModelScope.launch { _navEvent.emit(AuthenticationNavEvent.ToFuelStationScreen) }
    }
}
