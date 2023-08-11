package com.jonrysimbolon.core.repository.review

import androidx.paging.PagingData
import com.jonrysimbolon.core.model.UserReviewModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    suspend fun getAllPagingData(movieId: Int): Flow<PagingData<UserReviewModel>>
}