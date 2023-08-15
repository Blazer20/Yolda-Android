package com.yolda.presentation.ui.composable.screen.aboutAppSection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.PADDING37
import com.yolda.common.PADDING6
import com.yolda.common.TEXT_SIZE15
import com.yolda.presentation.ui.theme.DarkLightBlue

@Composable
fun FollowUsSection(
    onTelegramClick: () -> Unit,
    onInstagramClick: () -> Unit,
    onFacebookClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = PADDING37.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = PADDING6.dp),
            text = stringResource(id = R.string.follow_us),
            fontSize = TEXT_SIZE15.sp,
            color = DarkLightBlue,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.padding(top = PADDING6.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonFollowUsCard(
                imageId = R.drawable.ic_telegram,
                onFollowClick = onTelegramClick
            )
            CommonFollowUsCard(
                imageId = R.drawable.ic_instagram,
                onFollowClick = onInstagramClick
            )
            CommonFollowUsCard(
                imageId = R.drawable.facebook_image,
                onFollowClick = onFacebookClick
            )
        }
    }
}