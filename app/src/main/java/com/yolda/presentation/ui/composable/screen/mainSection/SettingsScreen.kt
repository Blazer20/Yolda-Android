package com.yolda.presentation.ui.composable.screen.mainSection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yolda.R
import com.yolda.common.PADDING10
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.presentation.ui.composable.components.RotatingProgressBar
import com.yolda.presentation.ui.composable.components.TextRightAlignedStartAppBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.SettingsSections
import com.yolda.presentation.ui.composable.screen.mainSection.settingsComponents.CommonCardSection
import com.yolda.presentation.ui.composable.screen.mainSection.settingsComponents.UserImageAndFullNameTheme
import com.yolda.presentation.viewModel.main.SettingsNavEvents
import com.yolda.presentation.viewModel.main.SettingsUiState
import com.yolda.presentation.viewModel.main.SettingsViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {

    val uiState by settingsViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        settingsViewModel.getUser()
        settingsViewModel.navEvent.collect { navEvent ->
            when(navEvent) {
                is SettingsNavEvents.ToLanguageScreen -> {
                    navController.navigate(SettingsSections.LanguageSection.destination)
                }
                is SettingsNavEvents.ToAboutAppScreen -> {
                    navController.navigate(SettingsSections.AboutAppSection.destination)
                }
            }
        }
    }

    SettingsScreen(
        uiState = uiState,
        onLanguageClick = settingsViewModel::onLanguageClick,
        onTechSupportClick = settingsViewModel::onTechSupportClick,
        onAboutAppClick = settingsViewModel::onAboutAppClick
    )
}

@Composable
private fun SettingsScreen(
    uiState: SettingsUiState,
    onLanguageClick: () -> Unit,
    onTechSupportClick: () -> Unit,
    onAboutAppClick: () -> Unit
) {

    YoldaScaffold(
        topBar = { TextRightAlignedStartAppBar(R.string.settings_label) },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                contentPadding = PaddingValues(horizontal = PADDING16.dp, vertical = PADDING12.dp)
            ) {
                item {
                    UserImageAndFullNameTheme(
                        imageUrl = uiState.userData.photoUrl,
                        fullName = uiState.userData.name ?: uiState.userData.email
                    )
                }
                item { Box(modifier = Modifier.padding(vertical = PADDING10.dp)) }
                item {
                    CommonCardSection(
                        iconId = R.drawable.ic_globe,
                        title = stringResource(id = R.string.language_title),
                        onCardClick = onLanguageClick
                    )
                }
                item {
                    CommonCardSection(
                        iconId = R.drawable.ic_headphones,
                        title = stringResource(id = R.string.tech_support_title),
                        onCardClick = onTechSupportClick
                    )
                }
                item {
                    CommonCardSection(
                        iconId = R.drawable.ic_tablet_phone,
                        title = stringResource(id = R.string.about_app_title),
                        onCardClick = onAboutAppClick
                    )
                }
            }
            AnimatedVisibility(
                visible = uiState.isLoading,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                RotatingProgressBar()
            }
        }
    }
}
