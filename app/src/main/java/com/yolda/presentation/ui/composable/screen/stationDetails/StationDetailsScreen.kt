package com.yolda.presentation.ui.composable.screen.stationDetails

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.yolda.data.remote.Station
import com.yolda.presentation.viewModel.stationDetails.StationDetailsNavEvent
import com.yolda.presentation.viewModel.stationDetails.StationDetailsUiState
import com.yolda.presentation.viewModel.stationDetails.StationDetailsViewModel

@Composable
fun StationDetailsScreen(
    navController: NavController,
    stationDetailsViewModel: StationDetailsViewModel,
    station: Station
) {

    val uiState by stationDetailsViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        Log.e("TAG", "StationDetailsScreen: $station")
        stationDetailsViewModel.saveNavArguments(stationData = station)
        stationDetailsViewModel.navEvent.collect { navEvent ->
            when(navEvent) {
                is StationDetailsNavEvent.ToBackNavigation -> {
                    navController.popBackStack()
                }
            }
        }
    }

    StationDetailsScreen(
        uiState = uiState,
        onNavigationClick = stationDetailsViewModel::onNavigationClick
    )
}

@Composable
fun StationDetailsScreen(
    uiState: StationDetailsUiState,
    onNavigationClick: () -> Unit
) {

}
