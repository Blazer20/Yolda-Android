package com.yolda.presentation.ui.composable.screen.authenticationSection.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING14
import com.yolda.common.PADDING16
import com.yolda.common.PADDING20
import com.yolda.common.PADDING4
import com.yolda.common.PADDING68
import com.yolda.common.TEXT_SIZE14
import com.yolda.common.TEXT_SIZE15
import com.yolda.common.TEXT_SIZE16
import com.yolda.common.TEXT_SIZE28
import com.yolda.presentation.ui.composable.components.ConfirmationCodeTopBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.screen.authenticationSection.confirmation.components.ConfirmationCodeCard
import com.yolda.presentation.ui.theme.PrimaryTextColor
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.presentation.ui.theme.White
import com.yolda.R

@Composable
fun ConfirmationCodeScreen () {

    ConfirmationCodeScreen(
        onNavigationClick = {},
        onContinueClick = {}
    )
}

@Composable
private fun ConfirmationCodeScreen(
    onNavigationClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val confirmationCode = remember { mutableStateOf("") }
    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }

    YoldaScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ConfirmationCodeTopBar(
                onNavigationClick = onNavigationClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.code_confirmation_title),
                color = PrimaryTextColor,
                fontSize = TEXT_SIZE28.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(vertical = PADDING4.dp),
                text = stringResource(id = R.string.code_confirmation_text),
                color = ThirdColor,
                fontSize = TEXT_SIZE15.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.padding(vertical = PADDING20.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until 6) {
                    ConfirmationCodeCard(
                        value = confirmationCode.value.getOrNull(i),
                        focusRequester = focusRequesters[i],
                        onValueChange = { newValue ->
                            confirmationCode.value =
                                confirmationCode.value.replaceRange(i, i + 1, newValue)
                            if (i < 5) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        },
                        onBackspace = {
                            if (i > 0) {
                                confirmationCode.value =
                                    confirmationCode.value.replaceRange(i - 1, i, "")
                                focusRequesters[i - 1].requestFocus()
                            }
                        }
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = PADDING68.dp),
                text = stringResource(id = R.string.code_confirmation_question),
                color = ThirdColor,
                fontSize = TEXT_SIZE14.sp,
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Center
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = PADDING16.dp, end = PADDING16.dp, top = PADDING20.dp),
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
        }
    }
}

@Preview
@Composable
fun ConfirmationCodeScreenPreview() {
    ConfirmationCodeScreen(
        onNavigationClick = {},
        onContinueClick = {}
    )
}
