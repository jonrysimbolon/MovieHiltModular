package com.jonrysimbolon.core.datasource

import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.model.UserReviewModel
import com.jonrysimbolon.core.model.VideoModel
import com.jonrysimbolon.core.utils.ResultStatus
import com.jonrysimbolon.core.utils.responseGsonPattern
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : DataSource {
    override suspend fun getAllCategories(): Flow<List<CategoryModel>> = flow {
        try {
            val allCategories = apiService.getAllCategories()
            emit(allCategories.genres)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getAllMovies(page: Int, idCategory: String): Flow<List<MovieModel>> =
        flow {
            try {
                val allMovies = apiService.getAllMovies(page, idCategory)
                emit(allMovies.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override suspend fun getMovie(id: Int): ResultStatus<DetailMovieModel> {
        val detailMovie = apiService.getMovie(id)
        val data = detailMovie.body()
        return if (detailMovie.isSuccessful && data != null) {
            ResultStatus.Success(data)
        } else {
            ResultStatus.Error(
                responseGsonPattern(
                    detailMovie
                        .errorBody()
                        ?.string()
                        .toString()
                ).statusMessage
            )
        }
    }

    override suspend fun getReviews(id: Int, page: Int): Flow<List<UserReviewModel>> = flow {
        try {
            val userReview = apiService.getReviews(id, page)
            emit(userReview.results)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getVideos(id: Int): Flow<List<VideoModel>> = flow {
        try {
            val getVideos = apiService.getVideos(id)
            emit(getVideos.results)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}