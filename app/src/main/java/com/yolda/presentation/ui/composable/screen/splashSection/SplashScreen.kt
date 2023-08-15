package com.yolda.presentation.ui.composable.screen.splashSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yolda.common.ZERO
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.AuthenticationSections
import com.yolda.presentation.ui.theme.DarkPurpleBlue
import com.yolda.presentation.viewModel.splash.SplashNavEvent
import com.yolda.presentation.viewModel.splash.SplashViewModel
import com.yolda.R

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel
) {

    LaunchedEffect(key1 = Unit) {
        splashViewModel.startSplashTimer()
        splashViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is SplashNavEvent.ToOnBoardingScreen -> {
                    navController.navigate(AuthenticationSections.OnBoardingSection.destination) {
                        popUpTo(ZERO)
                    }
                }
            }
        }
    }

    SplashScreen()
}

@Composable
private fun SplashScreen() {

    YoldaScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = DarkPurpleBlue,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.yolda_logo),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        navController = rememberNavController(),
        splashViewModel = hiltViewModel()
    )
}
