package com.yolda.presentation.ui.composable.screen.aboutAppSection.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.presentation.ui.theme.White200

@Composable
fun CommonFollowUsCard(
    imageId: Int,
    onFollowClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(end = PADDING16.dp)
            .clickable { onFollowClick() },
        colors = CardDefaults.cardColors(containerColor = White200),
        shape = RoundedCornerShape(CORNER_RADIUS12.dp)
    ) {
        Image(
            modifier = Modifier.padding(all = PADDING12.dp),
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = R.string.image_description)
        )
    }
}