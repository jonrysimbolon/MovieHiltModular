package com.jonrysimbolon.core.datasource

import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.model.UserReviewModel
import com.jonrysimbolon.core.model.VideoModel
import com.jonrysimbolon.core.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface DataSource {

    suspend fun getAllCategories(
    ): Flow<List<CategoryModel>>

    suspend fun getAllMovies(
        page: Int,
        idCategory: String,
    ): Flow<List<MovieModel>>

    suspend fun getMovie(
        id: Int,
    ): ResultStatus<DetailMovieModel>

    suspend fun getReviews(
        id: Int,
        page: Int,
    ): Flow<List<UserReviewModel>>

    suspend fun getVideos(
        id: Int,
    ): Flow<List<VideoModel>>
}