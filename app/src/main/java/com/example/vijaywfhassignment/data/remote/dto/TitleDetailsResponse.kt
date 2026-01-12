package com.example.vijaywfhassignment.data.remote.dto

import com.google.gson.JsonElement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class TitleDetailsResponse(
    val id: Int,
    val title: String,
    val original_title: String? = null,
    val plot_overview: String? = null,
    val type: String? = null,
    val runtime_minutes: String? = null,
    val year: Int? = null,
    val end_year: String? = null,
    val release_date: String? = null,
    val imdb_id: String? = null,
    val tmdb_id: Int? = null,
    val tmdb_type: String? = null,
    val genres: List<Int> = emptyList(),
    val genre_names: List<String>? = emptyList(),
    val user_rating: Double? = null,
    val critic_score: Int? = null,
    val us_rating: String? = null,
    val poster: String? = null,
    val posterMedium: String? = null,
    val posterLarge: String? = null,
    val backdrop: String? = null,
    val original_language: String? = null,
    val similar_titles: List<Int> = emptyList(),
    val networks: List<Int> = emptyList(),
    val network_names: List<String> = emptyList(),
    val relevance_percentile: Double? = null,
    val popularity_percentile: Double? = null,
    val trailer: String? = null,
    val trailer_thumbnail: String? = null
)
