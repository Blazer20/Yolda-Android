package com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.PADDING173
import com.yolda.common.PADDING24
import com.yolda.common.PADDING6
import com.yolda.common.TEXT_SIZE14
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.R

@Composable
fun RegistrationBy(
    onGoogleClick: () -> Unit,
    onFacebookClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(bottom = PADDING24.dp, top = PADDING173.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = PADDING6.dp),
            text = stringResource(id = R.string.auth_register_by),
            fontSize = TEXT_SIZE14.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            color = ThirdColor
        )
        Row(
            modifier = Modifier.padding(top = PADDING6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.width(8.dp))
            CommonRegisterItems(
                imageId = R.drawable.google_image,
                onRegisterItemClick = onGoogleClick
            )
            Box(modifier = Modifier.width(8.dp))
            CommonRegisterItems(
                imageId = R.drawable.facebook_image,
                onRegisterItemClick = onFacebookClick
            )
        }
    }
}
