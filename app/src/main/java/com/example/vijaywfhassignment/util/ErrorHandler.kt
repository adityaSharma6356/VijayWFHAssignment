package com.example.vijaywfhassignment.util

import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.lang.ClassCastException

fun Throwable.toReadableMessage(): String {
    return when (this) {
        is IOException ->
            "No internet connection. Please try again."

        is HttpException -> {
            val errorBody = response()?.errorBody()?.string()
            val message = if (!errorBody.isNullOrBlank()) errorBody else message()
            "Server error ${code()}: $message"
        }

        is SerializationException, is ClassCastException ->
            "We received unexpected data from the server."

        else ->
            this.message ?: this.toString()
    }
}
