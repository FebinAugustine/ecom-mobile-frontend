package com.ecom.core.domain.repository

import com.ecom.core.domain.model.Review
import com.ecom.core.domain.model.Reply

interface ReviewRepository {
    suspend fun getReviewsForProduct(productId: String): Result<List<Review>>
    suspend fun addReview(productId: String, rating: Int, review: String): Result<Review>
    suspend fun updateReview(reviewId: String, rating: Int, review: String): Result<Review>
    suspend fun deleteReview(reviewId: String): Result<Unit>

    suspend fun addReply(reviewId: String, reply: String): Result<Reply>
}
