package com.yolda.presentation.ui.composable.screen.mainSection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yolda.R
import com.yolda.common.PADDING10
import com.yolda.common.PADDING16
import com.yolda.common.SELECTED_FUEL_TYPE_KEY
import com.yolda.common.STATION_INFO_KEY
import com.yolda.common.TEXT_SIZE14
import com.yolda.common.TEXT_SIZE18
import com.yolda.common.anyToJson
import com.yolda.common.encode
import com.yolda.presentation.ui.composable.components.RotatingProgressBar
import com.yolda.presentation.ui.composable.components.TextRightAlignedStartAppBar
import com.yolda.presentation.ui.composable.components.TextRightAlignedStartWithNavigationButtonAppBar
import com.yolda.presentation.ui.composable.components.YoldaScaffold
import com.yolda.presentation.ui.composable.navigation.FuelStationsSections
import com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents.BannerSection
import com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents.CategoriesPicker
import com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents.FuelStationSections
import com.yolda.presentation.ui.theme.SecondaryColor
import com.yolda.presentation.viewModel.main.FuelStationNavEvent
import com.yolda.presentation.viewModel.main.FuelStationUiState
import com.yolda.presentation.viewModel.main.FuelStationViewModel

@Composable
fun FuelStationScreen(
    navController: NavController,
    fuelStationViewModel: FuelStationViewModel
) {

    val uiState by fuelStationViewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        fuelStationViewModel.fillCategoriesList()
        fuelStationViewModel.getAds()
        fuelStationViewModel.getStationBrands()
        fuelStationViewModel.navEvent.collect { navEvent ->
            when (navEvent) {
                is FuelStationNavEvent.ToFueStationDetailsScreen -> {
                    navController.navigate(
                        FuelStationsSections.StationDetails.destination
                            .replace( "{$STATION_INFO_KEY}", navEvent.stationData.anyToJson().encode())
                    )
                }
                is FuelStationNavEvent.ToFilterScreen -> {
                    navController.navigate(
                        FuelStationsSections.FilterSection.destination
                            .replace("{$SELECTED_FUEL_TYPE_KEY}", navEvent.selectedTypeName)
                    )
                }
                is FuelStationNavEvent.ToAdDetailsScreen -> {

                }
            }
        }
    }

    FuelStationScreen(
        uiState = uiState,
        onFilerClick = fuelStationViewModel::onFilterCLick,
        onNavigationClick = fuelStationViewModel::onNavigationClick,
        onCategoriesItemClick = fuelStationViewModel::onCategoriesItemClick,
    )
}

@Composable
private fun FuelStationScreen(
    uiState: FuelStationUiState,
    onFilerClick: () -> Unit,
    onNavigationClick: () -> Unit,
    onCategoriesItemClick: (isVisible: Boolean, selectedTypeName: String) -> Unit
) {

    YoldaScaffold(
        topBar = {
            if (uiState.isCategoriesVisible)
                TextRightAlignedStartAppBar(labelId = R.string.fuel_stations_label)
            else
                TextRightAlignedStartWithNavigationButtonAppBar(
                    labelId = R.string.fuel_stations_label,
                    onNavigationClick = { onNavigationClick() }
                )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { BannerSection(listOfBannerItems = uiState.listOfAds) }
                item {
                    AnimatedVisibility(visible = uiState.isCategoriesVisible) {
                        CategoriesPicker(
                            categoriesList = uiState.listOfCategories,
                            onCategoriesItemClick = onCategoriesItemClick
                        )
                    }
                }
                if (uiState.isCategoriesVisible) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = PADDING16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = stringResource(id = R.string.favorites),
                                color = SecondaryColor,
                                textAlign = TextAlign.Center,
                                fontSize = TEXT_SIZE18.sp,
                                fontWeight = FontWeight.W500
                            )
                        }
                    }
                    if (uiState.listOfFavorites.isEmpty()) {
                        item {
                            Text(
                                modifier = Modifier.padding(
                                    top = PADDING10.dp,
                                    start = PADDING16.dp,
                                    end = PADDING16.dp
                                ),
                                text = stringResource(id = R.string.there_is_no_result),
                                color = SecondaryColor,
                                textAlign = TextAlign.Center,
                                fontSize = TEXT_SIZE14.sp,
                                fontWeight = FontWeight.W400,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    } else {
                        itemsIndexed(uiState.listOfFavorites) { index, favorite ->
                            favorite.stations.forEachIndexed { index, station ->
                                FuelStationSections(
                                    stationName = favorite.name,
                                    ratingNumber = station.average_rating ?: 0.0
                                )
                            }
                        }
                    }
                } else {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = PADDING16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = uiState.selectedTypeName.ifEmpty { stringResource(id = R.string.favorites) },
                                color = SecondaryColor,
                                textAlign = TextAlign.Center,
                                fontSize = TEXT_SIZE18.sp,
                                fontWeight = FontWeight.W500
                            )
                            AnimatedVisibility(visible = !uiState.isCategoriesVisible) {
                                Text(
                                    modifier = Modifier.clickable { onFilerClick() },
                                    text = stringResource(id = R.string.filter_title),
                                    color = SecondaryColor,
                                    textAlign = TextAlign.Center,
                                    fontSize = TEXT_SIZE18.sp,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }
                    }
                    itemsIndexed(uiState.listOfStationBrands) { index, stationBrand ->
                        stationBrand.stations.forEachIndexed { index, station ->
                            FuelStationSections(
                                stationName = stationBrand.name,
                                ratingNumber = station.average_rating ?: 0.0
                            )
                        }
                    }
                }
            }
            AnimatedVisibility(
                visible = uiState.isLoading,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                RotatingProgressBar()
            }
        }
    }
}
