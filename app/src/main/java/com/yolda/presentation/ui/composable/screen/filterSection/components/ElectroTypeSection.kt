package com.yolda.presentation.ui.composable.screen.filterSection.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.EMPTY_STRING
import com.yolda.common.ONE
import com.yolda.common.PADDING12
import com.yolda.common.PADDING6
import com.yolda.common.SIZE44
import com.yolda.common.TEXT_SIZE16
import com.yolda.data.local.ElectroSubTypesLocal
import com.yolda.presentation.ui.theme.DividerGrayColor
import com.yolda.presentation.ui.theme.FadedBlue
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.ui.theme.White
import com.yolda.presentation.ui.theme.White150
import com.yolda.presentation.ui.theme.White200

@Composable
fun ElectroTypeSection(
    electroSubTypes: List<ElectroSubTypesLocal>,
    currentItem: ElectroSubTypesLocal,
    onElectroSubTypeClick: (ElectroSubTypesLocal) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val iconState = animateFloatAsState(
        targetValue = if (!expanded) (-1f) else (1f),
        tween(150, easing = LinearEasing)
    )
    var dropDownWidth by remember { mutableStateOf(0) }
    val shape = RoundedCornerShape(CORNER_RADIUS12.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                dropDownWidth = it.width
            }
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    expanded = expanded.not()
                }
                .fillMaxWidth()
                .height(SIZE44.dp)
                .border(
                    width = ONE.dp,
                    color = White200,
                    shape = RoundedCornerShape(CORNER_RADIUS12.dp)
                )
                .clip(shape = RoundedCornerShape(CORNER_RADIUS12.dp))
                .padding(horizontal = PADDING12.dp, vertical = PADDING6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentItem.name?.isEmpty() == true)
                    stringResource(id = R.string.electro_type)
                else
                    currentItem.name ?: EMPTY_STRING,
                color = SecondaryColor,
                fontSize = TEXT_SIZE16.sp,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier.scale(1f, iconState.value),
                painter = painterResource(id = R.drawable.chevron),
                contentDescription = stringResource(id = R.string.image_description)
            )
        }
        Surface(
            shape = shape
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .requiredHeightIn(max = 185.dp)
                    .width(with(LocalDensity.current) { dropDownWidth.toDp() })
                    .background(White)
            ) {
                electroSubTypes.forEachIndexed { index, itemForSelect ->
                    val isSelected = itemForSelect == currentItem

                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            onElectroSubTypeClick(itemForSelect)
                        },
                        modifier = Modifier
                            .background(White)
                            .requiredHeightIn(max = 50.dp, min = 34.dp)
                            .height(34.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = itemForSelect.name ?: "Unknown Name",
                                fontWeight = FontWeight.W400,
                                fontSize = TEXT_SIZE16.sp,
                                color = FadedBlue,
                                textAlign = TextAlign.Center
                            )
                            if (isSelected)
                                Image(
                                    painter = painterResource(id = R.drawable.tick_circle),
                                    contentDescription = stringResource(
                                        id = R.string.image_description
                                    )
                                )
                        }
                    }
                    if (index != electroSubTypes.size - 1) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = DividerGrayColor,
                            thickness = 1.dp
                        )
                    }
                }
            }
        }
    }
}