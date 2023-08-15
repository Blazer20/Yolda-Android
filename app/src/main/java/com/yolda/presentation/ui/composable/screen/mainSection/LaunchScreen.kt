package com.yolda.presentation.ui.composable.screen.mainSection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yolda.R
import com.yolda.common.TEXT_SIZE32
import com.yolda.common.ZERO
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.MainSections
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.White
import com.yolda.presentation.viewModel.main.LaunchNavEvent
import com.yolda.presentation.viewModel.main.LaunchViewModel

@Composable
fun LaunchScreen(
    navController: NavController,
    launchViewModel: LaunchViewModel
) {
    LaunchedEffect(key1 = Unit) {
        launchViewModel.continueTransition()
        launchViewModel.navEvent.collect { navEvent ->
            when(navEvent) {
                is LaunchNavEvent.ToFuelStationScreen -> {
                    navController.navigate(MainSections.FuelStationSection.destination) {
                        popUpTo(ZERO)
                    }
                }
            }
        }
    }

    LaunchScreen()
}

@Composable
fun LaunchScreen() {
    YoldaScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = White,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W700,
                fontSize = TEXT_SIZE32.sp,
                color = Purple
            )
        }
    }
}
