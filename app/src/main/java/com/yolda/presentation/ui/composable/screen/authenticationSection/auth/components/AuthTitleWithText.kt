package com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.PADDING4
import com.yolda.common.TEXT_SIZE15
import com.yolda.common.TEXT_SIZE28
import com.yolda.presentation.ui.theme.PrimaryTextColor
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.R


@Composable
fun AuthTitleWithText() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = PADDING4.dp),
            text = stringResource(id = R.string.auth_title),
            fontWeight = FontWeight.W500,
            fontSize = TEXT_SIZE28.sp,
            color = PrimaryTextColor,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.auth_text),
            fontWeight = FontWeight.W400,
            fontSize = TEXT_SIZE15.sp,
            color = ThirdColor,
            textAlign = TextAlign.Center
        )
    }
}