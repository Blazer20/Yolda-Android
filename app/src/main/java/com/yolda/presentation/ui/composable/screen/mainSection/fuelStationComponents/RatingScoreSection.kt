package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.TEXT_SIZE14
import com.yolda.presentation.ui.theme.DarkLightBlue

//- if (averageRating % 1 != 0f) 1 else 0

@Composable
fun RatingScoreSection(
    averageRating: Float
) {

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = averageRating.toString(),
            fontSize = TEXT_SIZE14.sp,
            fontWeight = FontWeight.W400,
            color = DarkLightBlue
        )
        repeat(averageRating.toInt()) {
            Image(
                painter = painterResource(id = R.drawable.ic_orange_star),
                contentDescription = stringResource(id = R.string.image_description),
                modifier = Modifier.size(24.dp)
            )
        }
        repeat(5 - averageRating.toInt()) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_start),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}