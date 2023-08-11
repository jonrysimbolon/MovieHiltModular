package com.jonrysimbolon.core.repository.review

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jonrysimbolon.core.datasource.DataSource
import com.jonrysimbolon.core.model.UserReviewModel
import com.jonrysimbolon.core.repository.review.pagingsource.ReviewPagingSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewRepositoryImpl @Inject constructor(
    private val remote: DataSource
): ReviewRepository {
    override suspend fun getAllPagingData(movieId: Int): Flow<PagingData<UserReviewModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ReviewPagingSourceImpl(
                    remote,
                    movieId
                )
            },
        ).flow
    }
}