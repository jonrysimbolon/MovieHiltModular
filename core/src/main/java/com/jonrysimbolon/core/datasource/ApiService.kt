package com.jonrysimbolon.core.datasource

import com.jonrysimbolon.core.model.CategoryResponses
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.model.MovieResponse
import com.jonrysimbolon.core.model.ReviewResponses
import com.jonrysimbolon.core.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getAllCategories(): CategoryResponses

    @GET("discover/movie")
    suspend fun getAllMovies(
        @Query("page") page: Int,
        @Query("with_genres") idCategory: String,
    ): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
    ): retrofit2.Response<DetailMovieModel>

    @GET("movie/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: Int,
        @Query("page") page: Int,
    ): ReviewResponses

    @GET("movie/{id}/videos")
    suspend fun getVideos(
        @Path("id") id: Int,
    ): VideoResponse

}