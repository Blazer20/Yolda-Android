package com.yolda.presentation.ui.composable.screen.aboutAppSection

import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.yolda.R
import com.yolda.common.INSTAGRAM_URL
import com.yolda.common.PADDING16
import com.yolda.common.TELEGRAM_URL
import com.yolda.presentation.ui.composable.screen.aboutAppSection.components.CommonButtons
import com.yolda.presentation.ui.composable.screen.aboutAppSection.components.FollowUsSection
import com.yolda.presentation.ui.composable.screen.aboutAppSection.components.LogoSection
import com.yolda.presentation.ui.composable.components.TextAppBarWithIconAction
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.White
import com.yolda.presentation.viewModel.aboutApp.AboutAppNavEvent
import com.yolda.presentation.viewModel.aboutApp.AboutAppUiState
import com.yolda.presentation.viewModel.aboutApp.AboutAppViewModel

@Composable
fun AboutAppScreen(
    navController: NavController,
    aboutAppViewModel: AboutAppViewModel
) {
    val uiState by aboutAppViewModel.uiState.collectAsState()

    val uriHandler = LocalUriHandler.current

    LaunchedEffect(key1 = Unit) {
        aboutAppViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is AboutAppNavEvent.ToBackNavigation -> {
                    navController.popBackStack()
                }
            }
        }
    }

    AboutAppScreen(
        uiState = uiState,
        onNavigationClick = aboutAppViewModel::onNavigationClick,
        onFeedbackClick = aboutAppViewModel::onFeedbackClick,
        onRateClick = aboutAppViewModel::onRateClick,
        onTelegramClick = { uriHandler.openUri(TELEGRAM_URL) },
        onInstagramClick = { uriHandler.openUri(INSTAGRAM_URL) },
        onFacebookClick = { }
    )

}

@Composable
private fun AboutAppScreen(
    uiState: AboutAppUiState,
    onNavigationClick: () -> Unit,
    onFeedbackClick: () -> Unit,
    onRateClick: () -> Unit,
    onTelegramClick: () -> Unit,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.e("TAG", "AboutAppScreen: ${result.data}")
    }

    YoldaScaffold(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        topBar = {
            TextAppBarWithIconAction(
                withActions = true,
                titleTextId = R.string.about_app_title,
                onNavigationClick = onNavigationClick,
                onActionClick = {
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    launcher.launch(shareIntent)
                },
                iconsColor = Purple
            )
        },
        backgroundColor = White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PADDING16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                LogoSection(appVersionCode = uiState.appVersionCode)
                CommonButtons(
                    imageId = R.drawable.ic_message,
                    textId = R.string.give_a_feedback,
                    onButtonClick = onFeedbackClick
                )
                CommonButtons(
                    imageId = R.drawable.ic_star,
                    textId = R.string.rate_us_at_google_store,
                    onButtonClick = onRateClick
                )
            }
            FollowUsSection(
                onTelegramClick = onTelegramClick,
                onInstagramClick = onInstagramClick,
                onFacebookClick = onFacebookClick
            )
        }
    }

}

@Preview
@Composable
private fun AboutAppScreenPreview() {
    AboutAppScreen(
        uiState = AboutAppUiState(),
        onNavigationClick = {},
        onFeedbackClick = {},
        onRateClick = {},
        onTelegramClick = {},
        onInstagramClick = {},
        onFacebookClick = {}
    )
}
