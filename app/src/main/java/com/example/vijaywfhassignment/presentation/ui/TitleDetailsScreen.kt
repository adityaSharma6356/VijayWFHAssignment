package com.example.vijaywfhassignment.presentation.ui

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.vijaywfhassignment.R
import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.presentation.viewmodel.TitleDetailsViewModel
import com.example.vijaywfhassignment.ui.theme.AppColors
import com.example.vijaywfhassignment.util.ErrorScreen
import com.example.vijaywfhassignment.util.Resource
import com.example.vijaywfhassignment.util.shimmer
import kotlinx.coroutines.handleCoroutineException


@Composable
fun TitleDetailsScreen(
    viewModel: TitleDetailsViewModel = hiltViewModel(),
    titleId: Int
) {
    LaunchedEffect(titleId) {
        viewModel.loadDetails(titleId)
    }

    val response by viewModel.details.collectAsState()

    when (response) {

        is Resource.Loading -> {
            ShimmerLoader()
        }

        is Resource.Success -> {
            val details = (response as Resource.Success).data
            TitleDetailsUi(details)
        }

        is Resource.Error -> {
            val error = (response as Resource.Error).message ?: "Unknown error"
            ErrorScreen(errorMessage = error)
        }
    }

}


@Composable
fun TitleDetailsUi(
    data: TitleDetailsResponse
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var backdropImage by remember { mutableStateOf(data.backdrop) }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            ) {
                var loading by remember { mutableStateOf(true) }
                AsyncImage(
                    model = backdropImage,
                    error = painterResource(R.drawable.no_image),
                    onError = {
                        backdropImage = data.posterLarge
                        loading = false
                    },
                    onLoading = {
                        loading = true
                    },
                    onSuccess = {
                        loading = false
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .then(if (loading) Modifier.shimmer() else Modifier)
                )

                val darkOverlay = remember {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.9f),
                            Color.Black.copy(alpha = 0.6f),
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkOverlay)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .blur(10.dp, 100.dp)
                    .graphicsLayer {
                        scaleY = -1f
                    }
            ) {
                var loading by remember { mutableStateOf(true) }
                AsyncImage(
                    model = backdropImage,
                    error = painterResource(R.drawable.no_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize().then(if (loading) Modifier.shimmer() else Modifier)
                )

                val darkOverlay = remember {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkOverlay)
                )
            }
        }




        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 15.dp)
        ) {
            Spacer(Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                var loading by remember { mutableStateOf(true) }
                AsyncImage(
                    model = data.posterLarge,
                    contentDescription = data.title+"poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth(0.5f)
                        .aspectRatio(3 / 4f)
                        .then(if (loading) Modifier.shimmer() else Modifier)

                )

                Spacer(Modifier.width(10.dp))

                Column(modifier = Modifier.align(Alignment.Bottom)) {
                    Text(
                        text = "User rating:",
                        fontSize = 18.sp,
                        color = Color.White.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "${data.user_rating ?: "--"}/10",
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = data.title,
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = (data.release_date ?: "-") +" • "+data.genre_names?.joinToString(postfix = "."),
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f),
                fontWeight = FontWeight.Bold,
            )


            Spacer(Modifier.height(10.dp))

            data.plot_overview?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Thin
                )
            }

        }

        Crossfade(scrollState.canScrollBackward) {
            if(it){
                TopBar(data.title, data.type == "movie")
            }
        }

    }
}

@Composable
fun TopBar(title: String, isMovie: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if(isMovie) AppColors.THEME_BLUE else AppColors.THEME_RED)
            .statusBarsPadding()
            .padding(15.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ShimmerLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            ) {
                var loading by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(if (loading) Modifier.shimmer() else Modifier)
                )

                val darkOverlay = remember {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.1f),
                            Color.Black.copy(alpha = 0.3f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkOverlay)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .blur(10.dp, 100.dp)
                    .graphicsLayer {
                        scaleY = -1f
                    }
            ) {
                var loading by remember { mutableStateOf(true) }
                Box(
                    modifier = Modifier.fillMaxSize().then(if (loading) Modifier.shimmer() else Modifier)
                )

                val darkOverlay = remember {
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 0.8f),
                            Color.Black.copy(alpha = 1f)
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkOverlay)
                )
            }
        }


        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(20.dp))
            var loading by remember { mutableStateOf(true) }
            Box(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth(0.5f)
                    .aspectRatio(3 / 4f)
                    .then(if (loading) Modifier.shimmer() else Modifier)

            )
        }

    }
}






































