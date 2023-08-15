package com.yolda.presentation.ui.composable.screen.languageSection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.ONE
import com.yolda.common.PADDING10
import com.yolda.common.PADDING16
import com.yolda.common.SIZE44
import com.yolda.common.TEXT_SIZE15
import com.yolda.presentation.ui.composable.components.HorizontalDivider
import com.yolda.presentation.ui.theme.Gray
import com.yolda.presentation.ui.theme.LightBlack800
import com.yolda.presentation.ui.theme.LightPurple
import com.yolda.presentation.viewModel.language.LanguageModel
import com.yolda.R

@Composable
fun LanguageOptionSection(
//    languageItem: LanguageModel,
//    languageIndex: Int,
//    languagesSize: Int,
//    savedLanguage: LanguageModel,
    onLanguageSelected: (LanguageModel) -> Unit
) {
    val languageOptions = listOf(
            LanguageModel(
                id = 0,
                language = stringResource(id = R.string.uzbek_language)
            ),
            LanguageModel(
                id = 1,
                language = stringResource(id = R.string.russian_language)
            ),
            LanguageModel(
                id = 2,
                language = stringResource(id = R.string.english_language)
            )
        )
    var selectedOption by remember { mutableStateOf(languageOptions[0]) }

    languageOptions.forEachIndexed { index, item ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.language,
                    fontSize = TEXT_SIZE15.sp,
                    color = LightBlack800,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.padding(start = 16.dp)
                )
                RadioButton(
                    modifier = Modifier
                        .padding(all = PADDING10.dp)
                        .size(SIZE44.dp),
                    selected = selectedOption == item,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = LightPurple,
                        unselectedColor = Gray
                    ),
                    onClick = {
                        selectedOption = item
                    }
                )
            }
            if ((index + ONE) != languageOptions.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PADDING16.dp)
                ) { HorizontalDivider() }
            }
        }
    }
}
