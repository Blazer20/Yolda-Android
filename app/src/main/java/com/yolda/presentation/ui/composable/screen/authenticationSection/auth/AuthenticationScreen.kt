package com.yolda.presentation.ui.composable.screen.authenticationSection.auth

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.yolda.presentation.ui.composable.YoldaAppState
import com.yolda.presentation.ui.composable.components.AuthenticationTopBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.AuthenticationSections
import com.yolda.presentation.ui.composable.navigation.MainSections
import com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components.AuthTitleWithText
import com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components.ContinueButtonWithAnnotation
import com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components.PhoneNumberInputField
import com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components.RegistrationBy
import com.yolda.presentation.viewModel.authentication.AuthenticationNavEvent
import com.yolda.presentation.viewModel.authentication.AuthenticationUiState
import com.yolda.presentation.viewModel.authentication.AuthenticationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun AuthenticationScreen(
    appState: YoldaAppState,
    authenticationViewModel: AuthenticationViewModel
) {
    val uiState by authenticationViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            coroutineScope.launch(Dispatchers.IO) {
                val signInClient = authenticationViewModel.signInWithIntent(intent = result.data ?: return@launch)
                authenticationViewModel.onSignIntResult(signInClient)
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        appState.setCurrentBottomBarItems(uiState.enabledBottomTabs)
        authenticationViewModel.navEvent.collect { navEvent ->
            when(navEvent) {
                is AuthenticationNavEvent.ToFuelStationScreen -> {
                    appState.navController.navigate(AuthenticationSections.LaunchSection.destination)
                }
                is AuthenticationNavEvent.ToConfirmationScreen -> {
                    /* TODO: Add navigation to Confirmation Screen */
                }
                is AuthenticationNavEvent.ToSplashScreen -> {
                    /* TODO: Add navigation to Splash Screen */
                }
            }

        }
    }

    AuthenticationScreen(
        uiState = uiState,
        onPhoneNumberChange = authenticationViewModel::onPhoneNumberChange,
        onGoogleClick = {
            coroutineScope.launch(Dispatchers.IO) {
                val signInIntentSender = authenticationViewModel.signIn()
                googleLauncher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        },
        onFacebookClick = { },
        onContinueClick = authenticationViewModel::onContinueClick
    )
}

@Composable
private fun AuthenticationScreen(
    uiState: AuthenticationUiState,
    onPhoneNumberChange: (String) -> Unit,
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit,
    onContinueClick: () -> Unit
) {

    YoldaScaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = {
            AuthenticationTopBar(
                onActionClick = {},
                onNavigationClick = {},
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTitleWithText()
            PhoneNumberInputField(
                phoneNumberValue = uiState.phoneNumber,
                onPhoneNumberChange = onPhoneNumberChange
            )
            RegistrationBy(
                onGoogleClick = onGoogleClick,
                onFacebookClick = onFacebookClick
            )
            ContinueButtonWithAnnotation(
                onContinueClick = onContinueClick
            )
        }
    }

}
