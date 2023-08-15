package com.yolda.presentation.ui.composable.screen.filterSection.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yolda.data.local.CommonSubTypesLocal

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubTypeFulterSection(
    listOfItems: List<CommonSubTypesLocal>,
    onSubTypeClick: (CommonSubTypesLocal) -> Unit
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        listOfItems.forEachIndexed { index, item ->
            FilterCard(
                item = item,
                onSubTypeClick = onSubTypeClick
            )
        }
    }
}