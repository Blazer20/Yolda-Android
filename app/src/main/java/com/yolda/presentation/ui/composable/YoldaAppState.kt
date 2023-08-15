package com.yolda.presentation.ui.composable

import android.content.res.Resources
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yolda.common.SnackBarManager
import com.yolda.presentation.ui.composable.navigation.MainSections
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.yolda.common.SELECTED_FUEL_TYPE_KEY
import com.yolda.common.STATION_INFO_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object AuthenticationSection {
    const val SPLASH_ROUTE = "splashRoute"
    const val ON_BOARDING_ROUTE = "onBoardingRoute"
    const val AUTHENTICATION_ROUTE = "authenticationRoute"
    const val LAUNCH_ROUTE = "launchRoute"
}

object MainSection {
    const val FUEL_STATION_ROUTE = "fuelStationRoute"
    const val SETTINGS_ROUTE = "settingsRoute"
}
object LanguageSection {
    const val LANGUAGE_ROUTE = "languageRoute"
    const val ABOUT_APP_ROUTE = "aboutAppRoute"
}

object FuelStationSection {
    const val FILTER_ROUTE = "filterRoute?selectedFuelType={$SELECTED_FUEL_TYPE_KEY}"
    const val STATION_ROUTE = "stationRoute?stationInfo={$STATION_INFO_KEY}"
}

/**
 * Remembers and creates an instance of [YoldaAppState]
 * **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberYoldaAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberAnimatedNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackBarManager, resources, coroutineScope) {
        YoldaAppState(
            scaffoldState = scaffoldState,
            navController = navController,
            snackBarManager = snackBarManager,
            coroutineScope = coroutineScope
        )
    }

/**
 * Responsible for holding state related to [YoldaApp] and containing UI-related logic
 * **/
@Stable
class YoldaAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackBarManager: SnackBarManager,
    coroutineScope: CoroutineScope
) {
    // Process snackBars coming from SnackBarManager
    init {
        coroutineScope.launch {
            snackBarManager.message.collect { currentMessage ->
                if (currentMessage.isNotEmpty()) {
                    val message = currentMessage[0]
                    val text = message.message

                    // Display the snackBar on the screen. 'showSnackBar' os a finction
                    // that suspends unit the snackBar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text)
                    // Once the snackBar is gone or dismissed, notify the SnackBarManager
                    snackBarManager.setMessageShown(message.id)
                }
            }
        }
    }

    // ---------------------------------------
    // BottomBar state source of truth
    // ---------------------------------------
    private val allBottomBarTabs = listOf(
        MainSections.FuelStationSection,
        MainSections.SettingsSection,
    )

    var currentBottomBarTabs = listOf<MainSections>(MainSections.FuelStationSection)

    fun setCurrentBottomBarItems(enabledTabs: List<String>) {
        val currentBottomBarTabsList = mutableListOf<MainSections>()
        currentBottomBarTabsList.add(MainSections.FuelStationSection)
        enabledTabs.forEach { enabledTab ->
            if (currentBottomBarTabsList.size < 3) {
                currentBottomBarTabsList.addAll(allBottomBarTabs
                    .filter { it.destination.uppercase() == enabledTab.uppercase() }
                )
            }
        }

        currentBottomBarTabs = currentBottomBarTabsList
        bottomBarRoutes = currentBottomBarTabs.map { it.destination }
    }

    private var bottomBarRoutes = currentBottomBarTabs.map { it.destination }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when 'Configuration'
 * gets updated
 * **/
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
