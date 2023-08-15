package com.yolda.presentation.ui.composable.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yolda.common.PADDING12
import com.yolda.common.PADDING15
import com.yolda.common.PADDING16
import com.yolda.common.PADDING26
import com.yolda.common.TEXT_SIZE16
import com.yolda.common.ZERO
import com.yolda.presentation.ui.composable.YoldaAppState
import com.yolda.presentation.ui.composable.navigation.MainSections
import com.yolda.presentation.ui.composable.navigation.OnBoardingTabItems
import com.yolda.presentation.ui.theme.DarkPurpleBlue
import com.yolda.presentation.ui.theme.Gray
import com.yolda.presentation.ui.theme.LightPurple
import com.yolda.presentation.ui.theme.LightPurple12
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.Transparent95
import com.yolda.presentation.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.yolda.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingBottomAppBars(
    pagerState: PagerState,
    tabs: List<OnBoardingTabItems>,
    currentTab: Int,
    onNextButtonClick: (PagerState) -> Unit
) {
    BottomAppBar(
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PADDING16.dp)
            ) {
                DotsIndicator(
                    modifier = Modifier.weight(0.5f),
                    dotsCount = tabs.size,
                    currentIndex = currentTab,
                    selectedDotColor = DarkPurpleBlue,
                    unselectedDotColor = Color.LightGray
                )
                TextButton(
                    modifier = Modifier.weight(0.5f),
                    onClick = { onNextButtonClick(pagerState) },
                    colors = ButtonDefaults.textButtonColors(backgroundColor = LightPurple12),
                    shape = RoundedCornerShape(PADDING12.dp),
                    contentPadding = PaddingValues(
                        vertical = PADDING15.dp,
                        horizontal = PADDING26.dp
                    ),
                ) {
                    Text(
                        text = stringResource(id = R.string.on_boarding_next_button_text),
                        fontWeight = FontWeight.W500,
                        fontSize = TEXT_SIZE16.sp,
                        color = LightPurple
                    )
                }
            }
        },
        backgroundColor = White,
        elevation = ZERO.dp,
        contentPadding = PaddingValues(bottom = PADDING16.dp)
    )
}


@Composable
fun DotsIndicator(
    modifier: Modifier,
    dotsCount: Int,
    currentIndex: Int,
    selectedDotColor: Color,
    unselectedDotColor: Color,
    dotWidth: Dp = 16.dp,
    dotHeight: Dp = 5.dp,
    spacing: Dp = 8.dp,
    cornerRadius: Dp = 4.dp,
    selectedDotLength: Dp = 24.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dotsCount) { index ->
            val isSelected = index == currentIndex
            Box(
                modifier = Modifier
                    .size(dotWidth, dotHeight)
                    .clip(RoundedCornerShape(cornerRadius)),
                contentAlignment = Alignment.Center,
            ) {
                val color = if (isSelected) selectedDotColor else unselectedDotColor
                Canvas(modifier = Modifier.matchParentSize()) {
                    drawRect(
                        color = color,
                        size = Size(dotWidth.toPx(), dotHeight.toPx())
                    )
                    if (isSelected) {
                        drawRect(
                            color = color,
                            size = Size(selectedDotLength.toPx(), dotHeight.toPx()),
                            topLeft = Offset((dotWidth.toPx() - selectedDotLength.toPx()) / 2f, 0f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    appState: YoldaAppState
) {

    val bottomBarItems = appState.currentBottomBarTabs

    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = White.copy(alpha = Transparent95)
    ) {

        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomBarItems.forEach { item ->

            val selected = item.destination == currentRoute
            CustomBottomNavigationItem(
                selected = selected,
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = item.disableIconId),
                        contentDescription = stringResource(id = R.string.icon)
                    )
                },
                onClick = {
                    appState.navController.navigate(item.destination) {
                        val navigationRoutes = bottomBarItems
                            .map(MainSections::destination)
                        val firstBottomBarDestination = appState.navController.backQueue
                            .firstOrNull { navigationRoutes.contains(it.destination.route) }
                            ?.destination

                        firstBottomBarDestination?.let { route ->
                            popUpTo(route.id) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Purple,
                unselectedContentColor = Gray
            )
        }
    }
}
