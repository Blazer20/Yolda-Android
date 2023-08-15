package com.yolda.presentation.ui.composable.screen.authenticationSection.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS8
import com.yolda.common.PADDING16
import com.yolda.common.TEXT_SIZE15
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.presentation.ui.theme.White100
import com.yolda.presentation.ui.theme.White300
import com.yolda.R
import com.yolda.common.ONE
import com.yolda.common.isValidPhoneNumber
import com.yolda.common.phoneNumberFormat

@Composable
fun PhoneNumberInputField(
    phoneNumberValue: String,
    onPhoneNumberChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = PADDING16.dp)
            .background(color = White100),
        value = phoneNumberValue.phoneNumberFormat(),
        onValueChange = { onPhoneNumberChange(it) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = SecondaryColor,
            disabledBorderColor = White300,
            focusedBorderColor = White300
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        keyboardActions = KeyboardActions(onNext = {}),
        isError = phoneNumberValue.isValidPhoneNumber(),
        textStyle = TextStyle(fontSize = TEXT_SIZE15.sp, fontWeight = FontWeight.W400),
        placeholder = {
            Text(
                text = stringResource(id = R.string.auth_phone_hint),
                fontSize = TEXT_SIZE15.sp,
                fontWeight = FontWeight.W400,
                color = ThirdColor,
                maxLines = ONE
            )
        },
        maxLines = ONE,
        shape = RoundedCornerShape(CORNER_RADIUS8.dp)
    )
}
