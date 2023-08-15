package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents.categoriesComponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING10
import com.yolda.common.PADDING24
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.White250

@Composable
fun FuelTypeCard(
    fuelTypeName: String
) {
    Card(
        modifier = Modifier.padding(all = PADDING10.dp),
        colors = CardDefaults.cardColors(containerColor = White250),
        shape = RoundedCornerShape(CORNER_RADIUS12.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = PADDING24.dp).fillMaxWidth(),
            text = fuelTypeName,
            color = SecondaryColor,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun FuelTypeCardPreview() {
    FuelTypeCard(
        fuelTypeName = "Gasoline"
    )
}
