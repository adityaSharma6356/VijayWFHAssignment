package com.example.vijaywfhassignment

import com.example.vijaywfhassignment.data.remote.api.WatchModeApi
import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.data.repository.TitlesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*
import java.io.IOException

class DetailsRepositoryTest {

    private val api: WatchModeApi = mockk()
    private val repository = TitlesRepository(api)

    @Test
    fun `getDetails returns success when api succeeds`() = runTest {
        val fakeResponse = TitleDetailsResponse(
            id = 1,
            title = "Test Movie"
        )

        coEvery { api.getTitleDetails(1, any()) } returns fakeResponse

        val result = repository.getDetails(1)

        assertTrue(result.isSuccess)
        assertEquals(fakeResponse.title, result.getOrNull()?.title)
    }

    @Test
    fun `getDetails returns failure when api throws`() = runTest {
        coEvery { api.getTitleDetails(1, any()) } throws IOException("No Internet")

        val result = repository.getDetails(1)

        assertTrue(result.isFailure)
    }

}
