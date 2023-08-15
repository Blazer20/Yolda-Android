package com.yolda.presentation.ui.composable.screen.mainSection.settingsComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yolda.R
import com.yolda.common.IMAGE_SIZE80
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE18
import com.yolda.presentation.ui.theme.DarkLightBlue

@Composable
fun UserImageAndFullNameTheme(
    imageUrl: String,
    fullName: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(IMAGE_SIZE80.dp).clip(shape = CircleShape),
            model = imageUrl,
            placeholder = painterResource(id = R.drawable.image_placeholder),
            fallback = painterResource(id = R.drawable.image_placeholder),
            contentDescription = stringResource(id = R.string.image_description),
            alignment = Alignment.Center
        )
        Text(
            modifier = Modifier.padding(horizontal = PADDING8.dp),
            text = fullName,
            fontSize = TEXT_SIZE18.sp,
            fontWeight = FontWeight.W600,
            color = DarkLightBlue
        )
    }

}
