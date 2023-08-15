package com.yolda.presentation.ui.composable.screen.languageSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING10
import com.yolda.common.PADDING16
import com.yolda.common.PADDING5
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.composable.components.TextAppBarWithIconAction
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.screen.languageSection.components.LanguageOptionSection
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.White
import com.yolda.presentation.viewModel.language.LanguageModel
import com.yolda.presentation.viewModel.language.LanguageNavEvent
import com.yolda.presentation.viewModel.language.LanguageUiState
import com.yolda.presentation.viewModel.language.LanguageViewModel
import com.yolda.R

@Composable
fun LanguageScreen(
    navController: NavController,
    languageViewModel: LanguageViewModel,
) {

    val uiState by languageViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        languageViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is LanguageNavEvent.ToBackStackNavigation -> {
                    navController.popBackStack()
                }
            }
        }
    }

    LanguageScreen(
        uiState = uiState,
        onLanguageSelected = languageViewModel::onLanguageSelected,
        onNavigationCLick = languageViewModel::onNavigationCLick
    )
}

@Composable
private fun LanguageScreen(
    uiState: LanguageUiState,
    onNavigationCLick: () -> Unit,
    onLanguageSelected: (LanguageModel) -> Unit
) {

    YoldaScaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TextAppBarWithIconAction(
                onNavigationClick = onNavigationCLick,
                titleTextId = R.string.settings_label
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                LanguageOptionSection(
                    onLanguageSelected = onLanguageSelected
                )

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PADDING16.dp, vertical = PADDING10.dp),
                shape = RoundedCornerShape(CORNER_RADIUS12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple),
                onClick = { onNavigationCLick() }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = PADDING5.dp),
                    text = stringResource(id = R.string.back),
                    fontWeight = FontWeight.W500,
                    fontSize = TEXT_SIZE16.sp,
                    textAlign = TextAlign.Center,
                    color = White
                )
            }
        }
    }

}

@Preview
@Composable
fun LanguageScreenPreview() {
    LanguageScreen(
        uiState = LanguageUiState(),
        onNavigationCLick = {},
        onLanguageSelected = {}
    )
}
