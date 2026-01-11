package com.example.vijaywfhassignment.util

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val message: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
