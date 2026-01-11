package com.example.vijaywfhassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.vijaywfhassignment.data.remote.api.WatchModeApi
import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.data.remote.paging.TitlesPagingSource
import com.example.vijaywfhassignment.util.ApiConstants
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class TitlesRepository @Inject constructor(
    private val api: WatchModeApi
) {

    fun getPagedMovies() = Pager(
        PagingConfig(pageSize = 10)
    ) {
        TitlesPagingSource(api, "movie")
    }.flow

    fun getPagedTvShows() = Pager(
        PagingConfig(pageSize = 10)
    ) {
        TitlesPagingSource(api, "tv_series")
    }.flow

    suspend fun getDetails(id: Int): Result<TitleDetailsResponse> {
        return try {

            val result = api.getTitleDetails(
                id = id,
                apiKey = ApiConstants.API_KEY
            )

            Result.success(result)

        } catch (e: Exception) {
            if (e is CancellationException) throw e

            Result.failure(e)
        }
    }
}
