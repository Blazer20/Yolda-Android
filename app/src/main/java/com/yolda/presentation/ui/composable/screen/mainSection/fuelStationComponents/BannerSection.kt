package com.yolda.presentation.ui.composable.screen.mainSection.fuelStationComponents

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.yolda.R
import com.yolda.common.CORNER_RADIUS12
import com.yolda.common.PADDING12
import com.yolda.common.PADDING18
import com.yolda.common.PADDING20
import com.yolda.common.TEXT_SIZE22
import com.yolda.data.remote.Ad
import com.yolda.presentation.ui.composable.components.DotsIndicator
import com.yolda.presentation.ui.composable.navigation.FuelStateBannerSections
import com.yolda.presentation.ui.theme.DarkPurpleBlue
import com.yolda.presentation.ui.theme.White
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSection(
    listOfBannerItems: List<Ad>
) {
    val pagerState = rememberPagerState()

    val listOfBanners = mutableListOf<FuelStateBannerSections>()
    listOfBannerItems.forEach { banner ->
        listOfBanners.add(
            FuelStateBannerSections.FuelStationBanner(imageId = R.string.best_price_label) {
                BannerCards(
                    imageURL = banner.imageURL,
                    label = banner.title
                )
            }
        )
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannerTabContent(
            tabs = listOfBanners,
            pagerState = pagerState,
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DotsIndicator(
                modifier = Modifier,
                dotsCount = pagerState.pageCount,
                currentIndex = pagerState.currentPage,
                selectedDotColor = DarkPurpleBlue,
                unselectedDotColor = Color.LightGray
            )
        }
    }

    LaunchedEffect(key1 = listOfBanners.isNotEmpty()) {
        while (true) {
            delay(10000)
            if (listOfBannerItems.isNotEmpty()) {
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }
}

@Composable
private fun BannerCards(
    imageURL: String,
    label: String
) {
    Box(
        modifier = Modifier
            .padding(all = PADDING18.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 343.dp, height = 192.dp)
                .clip(shape = RoundedCornerShape(CORNER_RADIUS12.dp)),
            model = imageURL,
            contentDescription = stringResource(id = R.string.image_description),
            placeholder = painterResource(id = R.drawable.station_image),
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier.padding(start = PADDING20.dp, bottom = PADDING12.dp),
            text = label,
            fontSize = TEXT_SIZE22.sp,
            fontWeight = FontWeight.W700,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun BannerTabContent(
    tabs: List<FuelStateBannerSections>, pagerState: PagerState
) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].banner()
    }
}
