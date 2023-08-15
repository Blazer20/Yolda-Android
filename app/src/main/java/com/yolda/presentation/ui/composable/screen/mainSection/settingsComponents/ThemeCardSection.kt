package com.yolda.presentation.ui.composable.screen.mainSection.settingsComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS16
import com.yolda.common.PADDING16
import com.yolda.common.PADDING4
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.White200
import com.yolda.R

@Composable
fun ThemeCardSection(
    iconId: Int,
    onToggleClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PADDING4.dp),
        backgroundColor = White200,
        shape = RoundedCornerShape(CORNER_RADIUS16.dp)
    ) {
        Row(
            modifier = Modifier.padding(PADDING16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = stringResource(id = R.string.image_description)
                )
                Text(
                    modifier = Modifier.padding(start = PADDING16.dp),
                    text = stringResource(id = R.string.dark_theme_title),
                    fontSize = TEXT_SIZE16.sp,
                    fontWeight = FontWeight.W500,
                    color = SecondaryColor,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
        }
    }
}

@Preview
@Composable
fun ThemeCardSectionPreview() {
    ThemeCardSection(
        iconId = R.drawable.ic_theme,
        onToggleClick = {}
    )
}
