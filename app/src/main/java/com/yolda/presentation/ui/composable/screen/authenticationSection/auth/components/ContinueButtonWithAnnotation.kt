package com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING14
import com.yolda.common.PADDING16
import com.yolda.common.PADDING20
import com.yolda.common.PADDING30
import com.yolda.common.TEXT_SIZE12
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.presentation.ui.theme.White
import com.yolda.R

@Composable
fun ContinueButtonWithAnnotation(
    onContinueClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = PADDING20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = PADDING16.dp, end = PADDING16.dp, top = PADDING30.dp),
            onClick = { onContinueClick() },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Purple),
            shape = RoundedCornerShape(CORNER_RADIUS12.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = PADDING14.dp),
                text = stringResource(id = R.string.auth_continue_text_button),
                fontSize = TEXT_SIZE16.sp,
                fontWeight = FontWeight.W500,
                color = White,
                textAlign = TextAlign.Center
            )
        }
        Text(
            modifier = Modifier.padding(top = PADDING30.dp),
            text = stringResource(id = R.string.auth_annotation),
            fontWeight = FontWeight.W500,
            fontSize = TEXT_SIZE12.sp,
            textAlign = TextAlign.Center,
            color = ThirdColor,
        )
    }
}
