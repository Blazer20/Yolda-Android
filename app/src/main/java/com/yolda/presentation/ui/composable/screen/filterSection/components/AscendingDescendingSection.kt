package com.yolda.presentation.ui.composable.screen.filterSection.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE14
import com.yolda.presentation.ui.theme.LightBlack900

@Composable
fun AscendingDescendingSection(
    isNoneSelected: Boolean,
    isAscendingSelected: Boolean,
    isDescendingSelected: Boolean,
    onSortByOptionsClick: (Boolean, Boolean, Boolean) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.clickable {
                onSortByOptionsClick(
                    true,
                    false,
                    false
                )
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(vertical = PADDING8.dp),
                text = stringResource(id = R.string.none),
                fontWeight = FontWeight.W500,
                fontSize = TEXT_SIZE14.sp,
                textAlign = TextAlign.Center,
                color = LightBlack900
            )
            if (isNoneSelected) {
                Image(
                    painter = painterResource(id = R.drawable.tick_circle),
                    contentDescription = stringResource(id = R.string.image_description)
                )
            }
        }
        Row(
            modifier = Modifier.clickable {
                onSortByOptionsClick(
                    false,
                    true,
                    false
                )
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(vertical = PADDING8.dp),
                text = stringResource(id = R.string.ascending),
                fontWeight = FontWeight.W500,
                fontSize = TEXT_SIZE14.sp,
                textAlign = TextAlign.Center,
                color = LightBlack900
            )
            if (isAscendingSelected) {
                Image(
                    painter = painterResource(id = R.drawable.tick_circle),
                    contentDescription = stringResource(id = R.string.image_description)
                )
            }
        }
        Row(
            modifier = Modifier.clickable {
                onSortByOptionsClick(
                    false,
                    false,
                    true
                )
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                modifier = Modifier.padding(vertical = PADDING8.dp),
                text = stringResource(id = R.string.descending),
                fontWeight = FontWeight.W500,
                fontSize = TEXT_SIZE14.sp,
                textAlign = TextAlign.Center,
                color = LightBlack900
            )
            if (isDescendingSelected) {
                Image(
                    painter = painterResource(id = R.drawable.tick_circle),
                    contentDescription = stringResource(id = R.string.image_description)
                )
            }
        }
    }
}