package com.yolda.presentation.ui.composable.components

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yolda.common.PADDING8
import com.yolda.common.TEXT_SIZE18
import com.yolda.common.TEXT_SIZE24
import com.yolda.common.TEXT_SIZE32
import com.yolda.common.ZERO
import com.yolda.presentation.ui.theme.Gray
import com.yolda.presentation.ui.theme.LightPurple
import com.yolda.presentation.ui.theme.PrimaryTextColor
import com.yolda.presentation.ui.theme.White
import com.yolda.R
import com.yolda.common.TEXT_SIZE16
import com.yolda.presentation.ui.theme.Purple
import com.yolda.presentation.ui.theme.SecondaryColor

@Composable
fun OnBoardingTopBar(
    onActionClick: () -> Unit = {},
    withAction: Boolean = true
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    textAlign = TextAlign.Center,
                    color = PrimaryTextColor,
                    fontSize = TEXT_SIZE24.sp,
                    fontWeight = FontWeight.W500,
                )
            }
        },
        actions = {
            if (withAction)
                TextButton(
                    onClick = { onActionClick() },
                ) {
                    Text(
                        text = stringResource(id = R.string.on_boarding_skip_button_text),
                        color = LightPurple,
                    )
                }
        },
        elevation = ZERO.dp
    )
}

@Composable
fun AuthenticationTopBar(
    onActionClick: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    withAction: Boolean = true
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White,
        title = {},
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navigation_icon),
                    contentDescription = stringResource(id = R.string.image_description),
                    alignment = Alignment.Center
                )
            }
        },
        actions = {
            if (withAction)
                TextButton(
                    onClick = { onActionClick() },
                ) {
                    Text(
                        text = stringResource(id = R.string.on_boarding_skip_button_text),
                        color = LightPurple,
                    )
                }
        },
        elevation = ZERO.dp
    )
}

@Composable
fun ConfirmationCodeTopBar(
    onActionClick: () -> Unit = {},
    onNavigationClick: () -> Unit = {},
    withAction: Boolean = false
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.code_confirmation_label),
                    textAlign = TextAlign.Center,
                    color = PrimaryTextColor,
                    fontSize = TEXT_SIZE18.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navigation_icon),
                    contentDescription = stringResource(id = R.string.image_description),
                    alignment = Alignment.Center
                )
            }
        },
        actions = {
            if (withAction)
                TextButton(
                    onClick = { onActionClick() },
                ) {
                    Text(
                        text = stringResource(id = R.string.on_boarding_skip_button_text),
                        color = LightPurple,
                    )
                }

        },
        elevation = ZERO.dp
    )
}

@Composable
fun TextRightAlignedStartAppBar(
    labelId: Int
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().statusBarsPadding(),
        backgroundColor = White,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = PADDING8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = labelId),
                    textAlign = TextAlign.Center,
                    color = PrimaryTextColor,
                    fontSize = TEXT_SIZE32.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        },
        elevation = ZERO.dp
    )
}

@Composable
fun TextRightAlignedStartWithNavigationButtonAppBar(
    labelId: Int,
    onNavigationClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().statusBarsPadding(),
        backgroundColor = White,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = PADDING8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = labelId),
                    textAlign = TextAlign.Center,
                    color = PrimaryTextColor,
                    fontSize = TEXT_SIZE32.sp,
                    fontWeight = FontWeight.W600,
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.navigation_icon),
                    contentDescription = stringResource(id = R.string.image_description),
                    alignment = Alignment.Center
                )
            }
        },
        elevation = ZERO.dp
    )
}

@Composable
fun TextAppBarWithIconAction(
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    @DrawableRes navigationIconId: Int = R.drawable.ic_arrow_back_24,
    @StringRes titleTextId: Int,
    textColor: Color = PrimaryTextColor,
    @DrawableRes actionIconId: Int = R.drawable.ic_share,
    actionContent: @Composable () -> Unit = {
        IconButton(
            onClick = { onActionClick() }) {
            Icon(
                painter = painterResource(id = actionIconId),
                contentDescription = stringResource(id = R.string.image_description),
                tint = Purple
            )
        }
    },
    iconsColor: Color = Gray,
    withActions: Boolean = false,
    withNavigation: Boolean = true,
    elevation: Dp = ZERO.dp,
    @ColorRes backgroundColor: Color = White,
) {
    TopAppBar(
        modifier = Modifier.background(backgroundColor),
        navigationIcon = {
            if (withNavigation) {
                IconButton(
                    onClick = { onNavigationClick() }) {
                    Icon(
                        painter = painterResource(id = navigationIconId),
                        contentDescription = stringResource(id = R.string.image_description),
                        tint = iconsColor
                    )
                }
            } else HorizontalSpacer(width = 48.dp)
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = titleTextId), color = textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
            }

        },
        actions = {
            if (withActions) actionContent() else HorizontalSpacer(width = 48.dp)
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun TextAppBarWithTextAction(
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    @DrawableRes navigationIconId: Int = R.drawable.ic_arrow_back_24,
    @StringRes titleTextId: Int,
    textColor: Color = PrimaryTextColor,
    @StringRes actionStringId: Int = R.string.filters_screen_reset,
    actionContent: @Composable () -> Unit = {
        IconButton(
            onClick = { onActionClick() }) {
            Text(
                text = stringResource(id = actionStringId),
                color = SecondaryColor,
                fontWeight = FontWeight.W400,
                fontSize = TEXT_SIZE16.sp,
                textAlign = TextAlign.Center
            )
        }
    },
    iconsColor: Color = Gray,
    withActions: Boolean = false,
    withNavigation: Boolean = true,
    elevation: Dp = ZERO.dp,
    @ColorRes backgroundColor: Color = White,
) {
    TopAppBar(
        modifier = Modifier.background(backgroundColor),
        navigationIcon = {
            if (withNavigation) {
                IconButton(
                    onClick = { onNavigationClick() }) {
                    Icon(
                        painter = painterResource(id = navigationIconId),
                        contentDescription = stringResource(id = R.string.image_description),
                        tint = iconsColor
                    )
                }
            } else HorizontalSpacer(width = 48.dp)
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = titleTextId), color = textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700
                )
            }

        },
        actions = {
            if (withActions) actionContent() else HorizontalSpacer(width = 48.dp)
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}
