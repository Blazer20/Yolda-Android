package com.yolda.presentation.ui.composable.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.yolda.presentation.ui.composable.AuthenticationSection.AUTHENTICATION_ROUTE
import com.yolda.presentation.ui.composable.AuthenticationSection.ON_BOARDING_ROUTE
import com.yolda.presentation.ui.composable.AuthenticationSection.SPLASH_ROUTE
import com.yolda.presentation.ui.composable.MainSection.FUEL_STATION_ROUTE
import com.yolda.presentation.ui.composable.YoldaAppState
import com.yolda.presentation.ui.composable.screen.authenticationSection.auth.AuthenticationScreen
import com.yolda.presentation.ui.composable.screen.onBoardingSection.OnBoardingScreen
import com.yolda.presentation.ui.composable.screen.splashSection.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.yolda.R
import com.yolda.common.ENTER_NAVIGATION_DURATION
import com.yolda.common.SELECTED_FUEL_TYPE_KEY
import com.yolda.common.decode
import com.yolda.common.fromJsonTo
import com.yolda.data.remote.Station
import com.yolda.presentation.ui.composable.AuthenticationSection.LAUNCH_ROUTE
import com.yolda.presentation.ui.composable.FuelStationSection.FILTER_ROUTE
import com.yolda.presentation.ui.composable.FuelStationSection.STATION_ROUTE
import com.yolda.presentation.ui.composable.LanguageSection.ABOUT_APP_ROUTE
import com.yolda.presentation.ui.composable.LanguageSection.LANGUAGE_ROUTE
import com.yolda.presentation.ui.composable.MainSection.SETTINGS_ROUTE
import com.yolda.presentation.ui.composable.screen.aboutAppSection.AboutAppScreen
import com.yolda.presentation.ui.composable.screen.filterSection.FilterScreen
import com.yolda.presentation.ui.composable.screen.languageSection.LanguageScreen
import com.yolda.presentation.ui.composable.screen.mainSection.FuelStationScreen
import com.yolda.presentation.ui.composable.screen.mainSection.LaunchScreen
import com.yolda.presentation.ui.composable.screen.mainSection.SettingsScreen

sealed class OnBoardingTabItems(
    open val screen: @Composable () -> Unit
) {

    class OnBoardingOne(override val screen: @Composable () -> Unit) : OnBoardingTabItems({})

    class OnBoardingTwo(override val screen: @Composable () -> Unit) : OnBoardingTabItems({})

    class OnBoardingThree(override val screen: @Composable () -> Unit) : OnBoardingTabItems({})
}

sealed class AuthenticationSections(val destination: String) {

    object SplashSection : AuthenticationSections(SPLASH_ROUTE)

    object OnBoardingSection : AuthenticationSections(ON_BOARDING_ROUTE)

    object AuthenticationSection : AuthenticationSections(AUTHENTICATION_ROUTE)

    object LaunchSection : AuthenticationSections(LAUNCH_ROUTE)
}

sealed class MainSections(
    val destination: String,
    @DrawableRes val disableIconId: Int
) {
    object FuelStationSection :
        MainSections(
            destination = FUEL_STATION_ROUTE,
            disableIconId = R.drawable.ic_fuel_station_disable
        )

    object SettingsSection :
        MainSections(
            destination = SETTINGS_ROUTE,
            disableIconId = R.drawable.ic_settings_disable
        )
}

sealed class FuelStationsSections(val destination: String) {
    object FilterSection : FuelStationsSections(FILTER_ROUTE)
    object StationDetails : FuelStationsSections(STATION_ROUTE)
}

sealed class FuelStateBannerSections(
    @DrawableRes open val imageId: Int,
    @StringRes open val label: Int,
    val fuelStationName: String,
    open val banner: @Composable () -> Unit,
) {
    class FuelStationBanner(
        override var imageId: Int,
        override val banner: @Composable () -> Unit
    ) : FuelStateBannerSections(
        imageId = R.drawable.station_image,
        label = R.string.best_price_label,
        fuelStationName = "Pegas",
        banner = {}
    )
}

sealed class SettingsSections(val destination: String) {
    object LanguageSection : SettingsSections(LANGUAGE_ROUTE)
    object AboutAppSection : SettingsSections(ABOUT_APP_ROUTE)
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun YoldaNavGraph(
    appState: YoldaAppState,
    modifier: Modifier,
    startDestination: String = AuthenticationSections.SplashSection.destination
) {
    AnimatedNavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { fadeIn(tween(300)) },
        exitTransition = { fadeOut(tween(300)) },
        popEnterTransition = { fadeIn(tween(300)) },
        popExitTransition = { fadeOut(tween(300)) }
    ) {
        composable(route = AuthenticationSections.SplashSection.destination) {
            SplashScreen(
                navController = appState.navController,
                splashViewModel = hiltViewModel()
            )
        }
        composable(route = AuthenticationSections.OnBoardingSection.destination) {
            OnBoardingScreen(
                appState = appState,
                onBoardingViewModel = hiltViewModel()
            )
        }
        composable(route = AuthenticationSections.AuthenticationSection.destination) {
            AuthenticationScreen(
                appState = appState,
                authenticationViewModel = hiltViewModel()
            )
        }
        composable(
            route = AuthenticationSections.LaunchSection.destination,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        ENTER_NAVIGATION_DURATION,
                        easing = LinearEasing
                    )
                )
            }
        ) {
            LaunchScreen(navController = appState.navController, launchViewModel = hiltViewModel())
        }

        composable(route = MainSections.FuelStationSection.destination) {
            FuelStationScreen(
                navController = appState.navController,
                fuelStationViewModel = hiltViewModel()
            )
        }
        composable(route = FuelStationsSections.FilterSection.destination) { navBackStackEntry ->
            val selectedFuelType = navBackStackEntry.arguments?.getString(SELECTED_FUEL_TYPE_KEY).toString()
            FilterScreen(
                navController = appState.navController,
                filterViewModel = hiltViewModel(),
                selectedFuelType = selectedFuelType
            )
        }
        composable(route = FuelStationsSections.StationDetails.destination) { navBackStackEntry ->
            val stationData = navBackStackEntry.arguments?.getString(STATION_ROUTE)?.decode()?.fromJsonTo(
                Station::class.java)

        }

        composable(route = MainSections.SettingsSection.destination) {
            SettingsScreen(
                navController = appState.navController,
                settingsViewModel = hiltViewModel()
            )
        }
        composable(route = SettingsSections.LanguageSection.destination) {
            LanguageScreen(
                navController = appState.navController,
                languageViewModel = hiltViewModel()
            )
        }
        composable(route = SettingsSections.AboutAppSection.destination) {
            AboutAppScreen(
                navController = appState.navController,
                aboutAppViewModel = hiltViewModel()
            )
        }
    }
}