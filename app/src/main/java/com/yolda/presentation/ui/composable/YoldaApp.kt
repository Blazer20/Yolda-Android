package com.yolda.presentation.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.yolda.presentation.ui.composable.components.BottomNavigationBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.components.YoldaSnackBar
import com.yolda.presentation.ui.composable.navigation.YoldaNavGraph
import com.yolda.presentation.ui.theme.YoldaTheme

@Composable
fun YoldaApp() {
    YoldaTheme {
        val appState = rememberYoldaAppState()

        YoldaScaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    BottomNavigationBar(appState)
                }
            },
            scaffoldState = appState.scaffoldState,
            snackBarHost = {
                SnackbarHost(
                    hostState = SnackbarHostState(),
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackBarData -> YoldaSnackBar(snackBarData) }
                )
            }
        ) { innerPaddingModifier ->
            Box {
                YoldaNavGraph(
                    appState = appState,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}