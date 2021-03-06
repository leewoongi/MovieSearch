package com.experiencers.data.remote.datasource.webservice

import com.experiencers.data.remote.entity.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WebService {
    @GET("/v1/search/movie.json")
    suspend fun getMovies(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") title: String
    ): Response<Movie>
}