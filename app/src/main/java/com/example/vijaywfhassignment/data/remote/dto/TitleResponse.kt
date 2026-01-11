package com.example.vijaywfhassignment.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitlesResponse(
    @SerialName("page") val page: Int? = null,
    @SerialName("titles") val titles: List<TitleItem>? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null
)
