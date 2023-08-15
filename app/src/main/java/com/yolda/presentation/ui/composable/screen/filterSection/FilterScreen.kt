package com.yolda.presentation.ui.composable.screen.filterSection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING10
import com.yolda.common.PADDING12
import com.yolda.common.PADDING16
import com.yolda.common.PADDING5
import com.yolda.common.TEXT_SIZE16
import com.yolda.common.TEXT_SIZE18
import com.yolda.common.emptyElectroSubTypesLocal
import com.yolda.data.local.CommonSubTypesLocal
import com.yolda.data.local.ElectroSubTypesLocal
import com.yolda.presentation.ui.composable.components.TextAppBarWithTextAction
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.screen.filterSection.components.AZSSection
import com.yolda.presentation.ui.composable.screen.filterSection.components.AscendingDescendingSection
import com.yolda.presentation.ui.composable.screen.filterSection.components.ElectroTypeSection
import com.yolda.presentation.ui.composable.screen.filterSection.components.FilterSubTypesSection
import com.yolda.presentation.ui.composable.screen.filterSection.components.SortPriceBySection
import com.yolda.presentation.ui.theme.LightBlack900
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.White
import com.yolda.presentation.viewModel.filter.FilterNavEvent
import com.yolda.presentation.viewModel.filter.FilterUiState
import com.yolda.presentation.viewModel.filter.FilterViewModel

@Composable
fun FilterScreen(
    navController: NavController,
    filterViewModel: FilterViewModel,
    selectedFuelType: String
) {

    val uiState by filterViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        filterViewModel.saveNavArguments(selectedFuelType)
        if (uiState.electroName == selectedFuelType)
            filterViewModel.setElectroDefaultType()
        else
            filterViewModel.defineFilerList()

        filterViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is FilterNavEvent.ToBackNavigation -> {
                    navController.popBackStack()
                }
            }
        }
    }

    LaunchedEffect(key1 = uiState.currentElectroSubType?.name) {
        filterViewModel.defineElectroFilterList()
    }

    FilterScreen(
        uiState = uiState,
        onNavigationClick = filterViewModel::onNavigationClicked,
        onActionClick = filterViewModel::onActionClick,
        onElectroSubTypeClick = filterViewModel::onElectroSubTypeClick,
        onSubTypeClick = filterViewModel::onSubTypeClick,
        onSortSubTypeClick = filterViewModel::onSortSubTypeClick,
        onSortByOptionsClick = filterViewModel::onSortByOptionsClick,
        onAZSOptionsClick = filterViewModel::onAZSOptionsClick,
    )
}

@Composable
private fun FilterScreen(
    uiState: FilterUiState,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onElectroSubTypeClick: (ElectroSubTypesLocal) -> Unit,
    onSubTypeClick: (CommonSubTypesLocal) -> Unit,
    onSortSubTypeClick: (CommonSubTypesLocal) -> Unit,
    onSortByOptionsClick: (Boolean, Boolean, Boolean) -> Unit,
    onAZSOptionsClick: (Boolean, Boolean, Boolean, Boolean) -> Unit,
) {
    YoldaScaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TextAppBarWithTextAction(
                withActions = true,
                titleTextId = R.string.filters_screen_title,
                onNavigationClick = onNavigationClick,
                onActionClick = onActionClick,
                iconsColor = Purple,
                actionStringId = R.string.filters_screen_reset
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = PADDING16.dp,
                        end = PADDING16.dp,
                        top = PADDING10.dp
                    )
                    .weight(0.9f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    AnimatedVisibility(visible = uiState.electroName == uiState.selectedFuelType) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = PADDING12.dp),
                                text = stringResource(id = R.string.electro_type),
                                fontWeight = FontWeight.W700,
                                fontSize = TEXT_SIZE18.sp,
                                textAlign = TextAlign.Center,
                                color = LightBlack900
                            )
                            ElectroTypeSection(
                                electroSubTypes = uiState.fuelTypes.electro.electroSubTypes,
                                currentItem = uiState.currentElectroSubType
                                    ?: emptyElectroSubTypesLocal,
                                onElectroSubTypeClick = onElectroSubTypeClick
                            )
                        }
                    }
                }
                item {
                    FilterSubTypesSection(
                        selectedFuelTypeName = uiState.selectedFuelType,
                        subTypes = uiState.subTypes,
                        onSubTypeClick = onSubTypeClick
                    )
                }
                item {
                    SortPriceBySection(
                        sortPriceBy = uiState.sortPriceBy,
                        onSortSubTypeClick = onSortSubTypeClick
                    )
                }
                item {
                    AscendingDescendingSection(
                        isNoneSelected = uiState.isNoneSelected,
                        isAscendingSelected = uiState.isAscendingSelected,
                        isDescendingSelected = uiState.isDescendingSelected,
                        onSortByOptionsClick = onSortByOptionsClick,
                    )
                }
                item {
                    AZSSection(
                        isAzsNoneSelected = uiState.isAzsNoneSelected,
                        isLastUpdatedSelected = uiState.isLastUpdatedSelected,
                        isByDistanceSelected = uiState.isByDistanceSelected,
                        isByPopularitySelected = uiState.isByPopularitySelected,
                        onAZSOptionsClick = onAZSOptionsClick
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .padding(horizontal = PADDING16.dp, vertical = PADDING10.dp),
                shape = RoundedCornerShape(CORNER_RADIUS12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple),
                onClick = { onNavigationClick() }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = PADDING5.dp),
                    text = stringResource(id = R.string.filters_apply),
                    fontWeight = FontWeight.W500,
                    fontSize = TEXT_SIZE16.sp,
                    textAlign = TextAlign.Center,
                    color = White
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterScreenPreview() {
    FilterScreen(
        navController = rememberNavController(),
        filterViewModel = FilterViewModel(),
        selectedFuelType = "Petrol"
    )
}
