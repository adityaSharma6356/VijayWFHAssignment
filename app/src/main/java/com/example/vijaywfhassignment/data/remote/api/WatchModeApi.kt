package com.example.vijaywfhassignment.data.remote.api

import com.example.vijaywfhassignment.data.remote.dto.TitleDetailsResponse
import com.example.vijaywfhassignment.data.remote.dto.TitlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WatchModeApi {

    @GET("list-titles/")
    suspend fun getMovies(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "movie",
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): TitlesResponse

    @GET("list-titles/")
    suspend fun getTvShows(
        @Query("apiKey") apiKey: String,
        @Query("types") type: String = "tv_series",
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): TitlesResponse

    @GET("title/{id}/details/")
    suspend fun getTitleDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): TitleDetailsResponse
}
