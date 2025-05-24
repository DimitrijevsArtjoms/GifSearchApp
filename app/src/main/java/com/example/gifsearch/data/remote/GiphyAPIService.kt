package com.example.gifsearch.data.remote

import com.example.gifsearch.data.models.GiphyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPIService {
    @GET("v1/gifs/trending")
    suspend fun getTrendingsGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GiphyResponse

    @GET("v1/gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): GiphyResponse
}
