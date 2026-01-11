package com.example.vijaywfhassignment.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleItem(
    @SerialName("id") val id: Int? = null,
    @SerialName("imdb_id") val imdbId: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("tmdb_id") val tmdbId: Int? = null,
    @SerialName("tmdb_type") val tmdbType: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("year") val year: Int? = null
)
