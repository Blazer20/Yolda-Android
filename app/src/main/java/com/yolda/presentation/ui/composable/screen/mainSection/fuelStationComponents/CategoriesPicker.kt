package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING10
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.common.PADDING24
import com.yolda.common.TEXT_SIZE18
import com.yolda.common.TWO
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.White250

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesPicker(
    categoriesList: List<Int>,
    onCategoriesItemClick: (isVisible: Boolean, selectedTypeName: String) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PADDING16.dp, vertical = PADDING12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            color = SecondaryColor,
            textAlign = TextAlign.Center,
            fontSize = TEXT_SIZE18.sp,
            fontWeight = FontWeight.W500
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            maxItemsInEachRow = TWO
        ) {
            categoriesList.forEach { fuelTypeId ->
                Card(
                    modifier = Modifier
                        .padding(end = PADDING10.dp, top = PADDING10.dp, bottom = PADDING10.dp)
                        .weight(0.5f)
                        .clickable {
                            onCategoriesItemClick(false, context.getString(fuelTypeId))
                        },
                    colors = CardDefaults.cardColors(containerColor = White250),
                    shape = RoundedCornerShape(CORNER_RADIUS12.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = PADDING24.dp)
                            .fillMaxWidth(),
                        text = stringResource(fuelTypeId),
                        color = SecondaryColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
