package com.example.vijaywfhassignment.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.vijaywfhassignment.data.remote.dto.TitleItem
import com.example.vijaywfhassignment.ui.theme.AppColors
import com.example.vijaywfhassignment.util.ErrorScreen
import com.example.vijaywfhassignment.util.shimmer
import com.example.vijaywfhassignment.util.toReadableMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun TitlesListScreen(
    data: Flow<PagingData<TitleItem>>,
    onItemClick: (Int) -> Unit,
    itemColor: Color
) {
    val lazyPagingItems = data.collectAsLazyPagingItems()
    val loadState = lazyPagingItems.loadState

    when {
        loadState.refresh is LoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ShimmerLoader()
            }
        }
        loadState.refresh is LoadState.Error -> {
            val e = loadState.refresh as LoadState.Error
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    ErrorScreen(
                        errorMessage = e.error.toReadableMessage()
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.ON_BACKGROUND,
                        ),
                        onClick = { lazyPagingItems.retry() }
                    ) {
                        Text(text = "Retry", color = Color.White)
                    }
                }
            }
        }

        lazyPagingItems.itemCount == 0 -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No results found",
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        else -> {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 10.dp),
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                items(lazyPagingItems.itemCount) { index ->
                    val item = lazyPagingItems[index]
                    if (item != null) {
                        TitleListItem(
                            onItemClick = onItemClick,
                            item = item,
                            itemColor = itemColor
                        )
                    }
                }

                if (loadState.append is LoadState.Loading) {
                    item {
                        Loader()
                    }
                }

                if (loadState.append is LoadState.Error) {
                    val e = loadState.append as LoadState.Error

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Failed to load more: ${e.error.toReadableMessage()}",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(8.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = AppColors.ON_BACKGROUND,
                                ),
                                onClick = { lazyPagingItems.retry() }
                            ) {
                                Text("Retry", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier.padding(vertical = 15.dp),
        color = Color.White,
        trackColor = AppColors.ON_BACKGROUND,
        strokeWidth = 5.dp,
        strokeCap = StrokeCap.Round
    )
}

@Composable
private fun ShimmerLoader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(15) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .shimmer()
            )
        }
    }
}

@Composable
private fun TitleListItem(
    onItemClick: (Int) -> Unit,
    item: TitleItem,
    itemColor: Color
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                item.id?.let {
                    onItemClick(item.id)
                }
            }
            .background(itemColor)
    ) {

        Spacer(Modifier.width(5.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .background(AppColors.ON_BACKGROUND)
                .padding(10.dp, 15.dp, 15.dp, 15.dp)
        ) {
            Text(
                text = item.title ?: "Unknown Title",
                fontSize = 15.sp,
                color = Color.White
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = item.year?.toString() ?: "-",
                fontSize = 15.sp,
                color = Color.White.copy(alpha = 0.7f),
            )
        }
    }

}
