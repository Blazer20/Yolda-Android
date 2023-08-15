package com.yolda.presentation.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yolda.common.ONE
import com.yolda.presentation.ui.theme.LightGray

@Composable
fun HorizontalDivider() {
    Box(modifier = Modifier.height(ONE.dp).fillMaxWidth().background(color = LightGray)){}
}