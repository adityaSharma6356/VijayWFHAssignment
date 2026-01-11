package com.example.vijaywfhassignment

import androidx.paging.PagingSource
import com.example.vijaywfhassignment.data.remote.api.WatchModeApi
import com.example.vijaywfhassignment.data.remote.dto.TitleItem
import com.example.vijaywfhassignment.data.remote.dto.TitlesResponse
import com.example.vijaywfhassignment.data.remote.paging.TitlesPagingSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TitlesPagingSourceTest {

    private val api: WatchModeApi = mockk()

    @Test
    fun `load returns correct page`() = runTest {
        coEvery {
            api.getMovies(any(), any(), any(), any())
        } returns TitlesResponse(
            page = 1,
            titles = listOf(
                TitleItem(
                    id = 1,
                    title = "A",
                    year = 2023,
                    type = ""
                )
            )
        )

        val pagingSource = TitlesPagingSource(api, "movie")

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        val page = result as PagingSource.LoadResult.Page

        assertEquals(1, page.data.size)
        assertEquals(2, page.nextKey)
    }
}
