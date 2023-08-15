package com.yolda.presentation.ui.composable.screen.aboutAppSection.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.PADDING47
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.DarkLightBlue

@Composable
fun LogoSection(
    appVersionCode: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PADDING47.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(
                id = R.string.image_description
            )
        )
        Text(
            text = stringResource(id = R.string.app_version, appVersionCode),
            fontWeight = FontWeight.W500,
            fontSize = TEXT_SIZE16.sp,
            textAlign = TextAlign.Center,
            color = DarkLightBlue
        )
    }
}