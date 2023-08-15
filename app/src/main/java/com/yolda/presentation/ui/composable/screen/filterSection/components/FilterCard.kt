package com.yolda.presentation.ui.composable.screen.filterSection.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.ONE
import com.yolda.common.PADDING12
import com.yolda.common.PADDING6
import com.yolda.common.TEXT_SIZE16
import com.yolda.data.local.CommonSubTypesLocal
import com.yolda.presentation.ui.theme.DarkLightBlue
import com.yolda.presentation.ui.theme.LightGray
import com.yolda.presentation.ui.theme.LightPurple12
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.White

@Composable
fun FilterCard(
    item: CommonSubTypesLocal,
    onSubTypeClick: (CommonSubTypesLocal) -> Unit
) {

    val borderColor = if (item.selected) Purple else LightGray
    val backgroundColor = if (item.selected) LightPurple12 else White

    Card(
        modifier = Modifier
            .padding(horizontal = PADDING6.dp, vertical = PADDING6.dp)
            .clickable { onSubTypeClick(item) },
        border = BorderStroke(width = ONE.dp, color = borderColor),
        shape = RoundedCornerShape(CORNER_RADIUS12),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = PADDING12.dp, vertical = PADDING6.dp),
            text = item.name,
            color = DarkLightBlue,
            fontSize = TEXT_SIZE16.sp,
            fontWeight = FontWeight.W500
        )
    }
}

@Preview
@Composable
fun FilterCardPreview() {
    FilterCard(
        item = CommonSubTypesLocal(name = "AI 100", price = "asd", selected = false),
        onSubTypeClick = {}
    )
}
