package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS4
import com.yolda.common.PADDING12
import com.yolda.common.PADDING4
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE14
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.DarkLightBlue
import com.yolda.presentation.ui.theme.White

data class FuelPrice(
    val fuelName: String,
    val price: Int,
    val cardColor: Long,
)

@Composable
fun PricesSection() {
    val listOfPrices = listOf(
        FuelPrice(
            fuelName = "80",
            price = 6000,
            cardColor = 0xFF06C390
        ),
        FuelPrice(
            fuelName = "91",
            price = 8000,
            cardColor = 0xFF4A86FA
        ),
        FuelPrice(
            fuelName = "92",
            price = 9000,
            cardColor = 0xFF2B64D2
        ),
        FuelPrice(
            fuelName = "95",
            price = 10500,
            cardColor = 0xFFF26052
        )
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        listOfPrices.forEach { item ->
            Column(
                modifier = Modifier.padding(end = PADDING8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    shape = RoundedCornerShape(CORNER_RADIUS4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(item.cardColor)
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = PADDING8.dp),
                        text = item.fuelName,
                        color = White,
                        fontWeight = FontWeight.W400,
                        fontSize = TEXT_SIZE16.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    modifier = Modifier.padding(top = PADDING4.dp),
                    text = item.price.toString(),
                    color = DarkLightBlue,
                    fontWeight = FontWeight.W400,
                    fontSize = TEXT_SIZE14.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
