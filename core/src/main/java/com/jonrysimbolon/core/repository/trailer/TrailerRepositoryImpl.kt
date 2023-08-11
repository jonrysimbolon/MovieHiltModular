package com.jonrysimbolon.core.repository.trailer

import com.jonrysimbolon.core.model.VideoModel
import com.jonrysimbolon.core.datasource.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrailerRepositoryImpl @Inject constructor(
    private val remote: ApiService
): TrailerRepository {
    override suspend fun getAll(movieId: Int): List<VideoModel> = remote.getVideos(movieId).results
}