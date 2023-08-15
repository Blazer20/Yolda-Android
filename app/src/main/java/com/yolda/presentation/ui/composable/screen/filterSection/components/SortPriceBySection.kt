package com.yolda.presentation.ui.composable.screen.filterSection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.PADDING12
import com.yolda.common.TEXT_SIZE18
import com.yolda.data.local.CommonSubTypesLocal
import com.yolda.presentation.ui.theme.LightBlack900

@Composable
fun SortPriceBySection(
    sortPriceBy: List<CommonSubTypesLocal>,
    onSortSubTypeClick: (CommonSubTypesLocal) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(vertical = PADDING12.dp),
            text = stringResource(id = R.string.filters_screen_sort_price_by),
            fontWeight = FontWeight.W700,
            fontSize = TEXT_SIZE18.sp,
            textAlign = TextAlign.Center,
            color = LightBlack900
        )
        SubTypeFulterSection(
            listOfItems = sortPriceBy,
            onSubTypeClick = onSortSubTypeClick
        )
    }
}