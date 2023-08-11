package com.jonrysimbolon.core.repository.trailer

import com.jonrysimbolon.core.model.VideoModel

interface TrailerRepository {
    suspend fun getAll(movieId: Int): List<VideoModel>
}