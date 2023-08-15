package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.DarkLightBlue

@Composable
fun FuelTitleAndRateSection(
    stationName: String,
    ratingNumber: Double
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stationName,
            fontWeight = FontWeight.W500,
            fontSize = TEXT_SIZE16.sp,
            textAlign = TextAlign.Center,
            color = DarkLightBlue
        )
        RatingScoreSection(ratingNumber.toFloat())
    }
}

@Preview
@Composable
fun FuelTitleAndRateSectionPreview() {
    FuelTitleAndRateSection(
        stationName = "",
        ratingNumber = 5.0
    )
}
