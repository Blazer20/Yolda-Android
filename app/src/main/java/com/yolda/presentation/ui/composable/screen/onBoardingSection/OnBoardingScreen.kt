package com.yolda.presentation.ui.composable.screen.onBoardingSection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.yolda.common.ZERO
import com.yolda.presentation.ui.composable.YoldaAppState
import com.yolda.presentation.ui.composable.components.OnBoardingBottomAppBars
import com.yolda.presentation.ui.composable.components.OnBoardingTopBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.AuthenticationSections
import com.yolda.presentation.ui.composable.navigation.MainSections
import com.yolda.presentation.ui.composable.navigation.OnBoardingTabItems
import com.yolda.presentation.ui.composable.screen.onBoardingSection.onBoardingTabs.OnBoardingTabOne
import com.yolda.presentation.ui.composable.screen.onBoardingSection.onBoardingTabs.OnBoardingTabThree
import com.yolda.presentation.ui.composable.screen.onBoardingSection.onBoardingTabs.OnBoardingTabTwo
import com.yolda.presentation.viewModel.onBoarding.OnBoardingNavEvent
import com.yolda.presentation.viewModel.onBoarding.OnBoardingUiState
import com.yolda.presentation.viewModel.onBoarding.OnBoardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    appState: YoldaAppState,
    onBoardingViewModel: OnBoardingViewModel
) {

    val uiState by onBoardingViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        appState.setCurrentBottomBarItems(uiState.enabledBottomTabs)
        onBoardingViewModel.loggedInCheck()
        onBoardingViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is OnBoardingNavEvent.ToAuthenticationSection -> {
                    appState.navController.navigate(AuthenticationSections.AuthenticationSection.destination)
                }
                is OnBoardingNavEvent.ToFuelStationSection -> {
                    appState.navController.navigate(MainSections.FuelStationSection.destination)
                }
            }
        }
    }

    OnBoardingScreen(
        uiState = uiState,
        onActionClick = onBoardingViewModel::onSkipClicked,
        onNextButtonClick = onBoardingViewModel::onTransitionButtonClick,
        saveCurrentPage = onBoardingViewModel::saveCurrentPage
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnBoardingScreen(
    uiState: OnBoardingUiState,
    onActionClick: () -> Unit,
    onNextButtonClick: (currentPage: Int) -> Unit,
    saveCurrentPage: (currentPage: Int) -> Unit
) {

    val pagerState = rememberPagerState()

    val listOfTabs = listOf(
        OnBoardingTabItems.OnBoardingOne {
            OnBoardingTabOne()
        },
        OnBoardingTabItems.OnBoardingTwo {
            OnBoardingTabTwo()
        },
        OnBoardingTabItems.OnBoardingThree {
            OnBoardingTabThree()
        }
    )
    val coroutineScope = rememberCoroutineScope()

    saveCurrentPage(pagerState.currentPage)

    YoldaScaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        topBar = { OnBoardingTopBar(onActionClick = onActionClick) },
        bottomBar = {
            OnBoardingBottomAppBars(
                pagerState = pagerState,
                tabs = listOfTabs,
                currentTab = uiState.currentPage,
                onNextButtonClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                    onNextButtonClick(pagerState.currentPage + 1)
                }
            )
        }
    ) {
        Box {
            Column {
                Row(
                    modifier = Modifier
                        .padding(top = ZERO.dp)
                        .fillMaxWidth()
                ) {
                    OnBoardingTabContent(tabs = listOfTabs, pagerState = pagerState)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun OnBoardingTabContent(
    tabs: List<OnBoardingTabItems>, pagerState: PagerState
) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screen()
    }
}
