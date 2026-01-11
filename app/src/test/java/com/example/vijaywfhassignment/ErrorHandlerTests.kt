package com.example.vijaywfhassignment

import com.example.vijaywfhassignment.util.toReadableMessage
import kotlinx.serialization.SerializationException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ErrorHandlerTests {

    @Test
    fun `IOException returns no internet message`() {
        val exception = IOException()

        val result = exception.toReadableMessage()

        assertEquals("No internet connection. Please try again.", result)
    }

    @Test
    fun `HttpException returns proper server error message`() {
        val response = Response.error<Any>(
            500,
            "Server failure".toResponseBody("application/json".toMediaType())
        )
        val exception = HttpException(response)

        val result = exception.toReadableMessage()

        assertEquals("Server error 500: Server failure", result)
    }

    @Test
    fun `SerializationException returns unexpected data message`() {
        val exception = SerializationException("bad json")

        val result = exception.toReadableMessage()

        assertEquals("We received unexpected data from the server.", result)
    }

    @Test
    fun `ClassCastException returns unexpected data message`() {
        val exception = ClassCastException()

        val result = exception.toReadableMessage()

        assertEquals("We received unexpected data from the server.", result)
    }

    @Test
    fun `Unknown exception returns message or toString`() {
        val exception = IllegalStateException("Something broke")

        val result = exception.toReadableMessage()

        assertEquals("Something broke", result)
    }
}
