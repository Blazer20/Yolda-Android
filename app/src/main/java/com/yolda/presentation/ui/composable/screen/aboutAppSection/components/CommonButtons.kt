package com.yolda.presentation.ui.composable.screen.aboutAppSection.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.ONE
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.common.PADDING4
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.BorderStrokeColor
import com.yolda.presentation.ui.theme.DarkLightBlue
import com.yolda.presentation.ui.theme.White

@Composable
fun CommonButtons(
    imageId: Int,
    textId: Int,
    onButtonClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = PADDING12.dp)
            .clickable { onButtonClick() },
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        border = BorderStroke(width = ONE.dp, color = BorderStrokeColor),
        shape = RoundedCornerShape(CORNER_RADIUS12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = PADDING16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(end = PADDING4.dp),
                painter = painterResource(id = imageId),
                contentDescription = stringResource(
                    id = R.string.image_description
                )
            )
            Text(
                text = stringResource(id = textId),
                fontSize = TEXT_SIZE16.sp,
                fontWeight = FontWeight.W500,
                color = DarkLightBlue
            )
        }
    }
}