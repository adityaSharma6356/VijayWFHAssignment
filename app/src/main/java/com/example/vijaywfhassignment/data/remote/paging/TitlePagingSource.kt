package com.example.vijaywfhassignment.data.remote.paging

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vijaywfhassignment.data.remote.api.WatchModeApi
import com.example.vijaywfhassignment.data.remote.dto.TitleItem
import com.example.vijaywfhassignment.util.ApiConstants
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class TitlesPagingSource(
    private val api: WatchModeApi,
    private val type: String
) : PagingSource<Int, TitleItem>() {

    override fun getRefreshKey(state: PagingState<Int, TitleItem>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TitleItem> {
        val page = params.key ?: 1

        return try {
            val response =
                if (type == "movie")
                    api.getMovies(ApiConstants.API_KEY, "movie", 10, page)
                else
                    api.getTvShows(ApiConstants.API_KEY, "tv_series", 10, page)

            LoadResult.Page(
                data = response.titles ?: emptyList(),
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.titles.isNullOrEmpty()) null else page + 1
            )
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: IOException) {
            LoadResult.Error(e)
        }
        catch (e: HttpException) {
            LoadResult.Error(e)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
