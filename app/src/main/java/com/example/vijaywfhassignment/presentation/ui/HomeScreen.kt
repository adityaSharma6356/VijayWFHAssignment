package com.example.vijaywfhassignment.presentation.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.vijaywfhassignment.presentation.navigation.TitleDetails
import com.example.vijaywfhassignment.presentation.viewmodel.MainViewModel
import com.example.vijaywfhassignment.ui.theme.AppColors

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val animatedBackGroundColor by animateColorAsState(
        targetValue = if(selectedIndex==0) AppColors.THEME_RED else AppColors.THEME_BLUE,
        label = "background color animation",
        animationSpec = tween(easing = LinearEasing, durationMillis = 300)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBackGroundColor)
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            ) {
                val animatedWeight by animateFloatAsState(if(selectedIndex==0) 0.7f else 0.3f)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(animatedWeight)
                        .background(AppColors.THEME_RED, RoundedCornerShape(20))
                        .clickable {
                            selectedIndex = 0
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = "TV Shows",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f - animatedWeight)
                        .background(AppColors.THEME_BLUE, RoundedCornerShape(20))
                        .clickable {
                            selectedIndex = 1
                        }
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Movies",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }


            val pagerState = rememberPagerState(pageCount = {2})

            LaunchedEffect(selectedIndex) {
                if(pagerState.currentPage==selectedIndex) return@LaunchedEffect
                if(selectedIndex == 0){
                    pagerState.animateScrollToPage(0)
                } else {
                    pagerState.animateScrollToPage(1)
                }
            }

            LaunchedEffect(pagerState.currentPage) {
                if(pagerState.currentPage!=selectedIndex){
                    selectedIndex = pagerState.currentPage
                }
            }

            Spacer(Modifier.height(10.dp))

            HorizontalPager(
                beyondViewportPageCount = 1,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .background(AppColors.BACKGROUND)
                    .padding(horizontal = 10.dp)
            ) { index ->
                if(index==0){
                    TvShowsList(
                        viewModel = viewModel,
                        onItemClick = {
                            navController.navigate(TitleDetails(it)){
                                launchSingleTop = true
                            }
                        }
                    )
                } else {
                    MoviesList(
                        viewModel = viewModel,
                        onItemClick = {
                            navController.navigate(TitleDetails(it)){
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }

        }
    }
}


@Composable
fun TvShowsList(
    viewModel: MainViewModel,
    onItemClick: (Int) -> Unit
) {
    TitlesListScreen(
        data = viewModel.tvShows,
        onItemClick = onItemClick,
        itemColor = AppColors.THEME_RED
    )
}

@Composable
fun MoviesList(
    viewModel: MainViewModel,
    onItemClick: (Int) -> Unit
) {
    TitlesListScreen(
        data = viewModel.movies,
        onItemClick = onItemClick,
        itemColor = AppColors.THEME_BLUE
    )
}

























