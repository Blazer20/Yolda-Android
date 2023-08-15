package com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yolda.common.PADDING12
import com.yolda.presentation.ui.theme.White200
import com.yolda.R

@Composable
fun CommonRegisterItems(
    imageId: Int,
    onRegisterItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(48.dp)
            .clickable { onRegisterItemClick() },
        colors = CardDefaults.cardColors(
            containerColor = White200
        ),
    ) {
        Image(
            modifier = Modifier.fillMaxSize().padding(PADDING12.dp),
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = R.string.image_description)
        )
    }
}
