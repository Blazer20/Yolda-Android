package com.yolda.presentation.ui.composable.screen.authenticationSection.confirmation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS8
import com.yolda.common.ONE
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE24
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.White100
import com.yolda.presentation.ui.theme.White300

@Composable
fun ConfirmationCodeCard(
    value: Char?,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
    onBackspace: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = PADDING8.dp)
            .width(44.dp)
            .height(44.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(CORNER_RADIUS8.dp),
        border = BorderStroke(width = ONE.dp, color = White300),
        backgroundColor = White100
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester),
            value = value?.toString() ?: "",
            onValueChange = { onValueChange(it.takeLast(1)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                autoCorrect = false,
                imeAction = ImeAction.Go
            ),
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(
                color = SecondaryColor,
                fontWeight = FontWeight.W400,
                fontSize = TEXT_SIZE24.sp,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = White100
            )
        )
    }
}
