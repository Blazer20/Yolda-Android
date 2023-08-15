package com.yolda.presentation.ui.composable.screen.onBoardingSection.onBoardingTabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.PADDING37
import com.yolda.common.PADDING47
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE14
import com.yolda.common.TEXT_SIZE18
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.ThirdColor
import com.yolda.presentation.ui.theme.White
import com.yolda.R

@Composable
fun OnBoardingTabTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = PADDING47.dp, bottom = PADDING37.dp),
            painter = painterResource(id = R.drawable.on_boarding_purple_two),
            contentDescription = stringResource(id = R.string.image_description),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = PADDING37.dp),
            text = stringResource(id = R.string.on_boarding_two_text_title),
            fontSize = TEXT_SIZE18.sp,
            fontWeight = FontWeight.W500,
            color = SecondaryColor,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = PADDING37.dp, vertical = PADDING8.dp),
            text = stringResource(id = R.string.common_on_boarding_text),
            fontSize = TEXT_SIZE14.sp,
            fontWeight = FontWeight.W300,
            color = ThirdColor,
            textAlign = TextAlign.Start
        )
    }
}
