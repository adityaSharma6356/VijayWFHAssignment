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

//@Serializable
//data class TitleDetailsResponse(
//    @SerialName("id")
//    val id: Int,
//
//    @SerialName("title")
//    val title: String,
//
//    @SerialName("original_title")
//    val originalTitle: String? = null,
//
//    @SerialName("plot_overview")
//    val plotOverview: String? = null,
//
//    @SerialName("type")
//    val type: String? = null,
//
//    @SerialName("runtime_minutes")
//    val runtimeMinutes: String? = null,
//
//    @SerialName("year")
//    val year: Int? = null,
//
//    @SerialName("end_year")
//    val endYear: String? = null,
//
//    @SerialName("release_date")
//    val releaseDate: String? = null,
//
//    @SerialName("imdb_id")
//    val imdbId: String? = null,
//
//    @SerialName("tmdb_id")
//    val tmdbId: Int? = null,
//
//    @SerialName("tmdb_type")
//    val tmdbType: String? = null,
//
//    @SerialName("genres")
//    val genres: List<Int> = emptyList(),
//
//    @SerialName("genre_names")
//    val genreNames: List<String>? = emptyList(),
//
//    @SerialName("user_rating")
//    val userRating: Double? = null,
//
//    @SerialName("critic_score")
//    val criticScore: Int? = null,
//
//    @SerialName("us_rating")
//    val usRating: String? = null,
//
//    @SerialName("poster")
//    val poster: String? = null,
//
//    @SerialName("poster_medium")
//    val posterMedium: String? = null,
//
//    @SerialName("poster_large")
//    val posterLarge: String? = null,
//
//    @SerialName("backdrop")
//    val backdrop: String? = null,
//
//    @SerialName("original_language")
//    val originalLanguage: String? = null,
//
//    @SerialName("similar_titles")
//    val similarTitles: List<Int> = emptyList(),
//
//    @SerialName("networks")
//    val networks: List<Int> = emptyList(),
//
//    @SerialName("network_names")
//    val networkNames: List<String> = emptyList(),
//
//    @SerialName("relevance_percentile")
//    val relevancePercentile: Double? = null,
//
//    @SerialName("popularity_percentile")
//    val popularityPercentile: Double? = null,
//
//    @SerialName("trailer")
//    val trailer: String? = null,
//
//    @SerialName("trailer_thumbnail")
//    val trailerThumbnail: String? = null,
//)
