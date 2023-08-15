package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.common.PADDING8
import com.yolda.presentation.ui.theme.White250

@Composable
fun FuelStationSections(
    stationName: String,
    ratingNumber: Double
) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(start = PADDING16.dp, end = PADDING16.dp, top = PADDING12.dp),
        colors = CardDefaults.cardColors(containerColor = White250),
        shape = RoundedCornerShape(CORNER_RADIUS12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PADDING8.dp, horizontal = PADDING12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FuelTitleAndRateSection(stationName = stationName, ratingNumber = ratingNumber)
            PricesSection()
        }
    }

}
