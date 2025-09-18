package com.ecom.core.data.repository

import com.ecom.core.common.Failure
import com.ecom.core.data.dto.CreateReplyRequest
import com.ecom.core.data.dto.CreateReviewRequest
import com.ecom.core.data.dto.UpdateReviewRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.ReviewApiService
import com.ecom.core.domain.model.Reply
import com.ecom.core.domain.model.Review
import com.ecom.core.domain.repository.ReviewRepository

class ReviewRepositoryImpl(
    private val reviewApiService: ReviewApiService,
    private val appPreferences: AppPreferences
) : ReviewRepository {

    override suspend fun getReviewsForProduct(productId: String): Result<List<Review>> {
        return try {
            val reviews = reviewApiService.getReviewsForProduct(productId).map { it.toDomain() }
            Result.success(reviews)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun addReview(productId: String, rating: Int, review: String): Result<Review> {
        return try {
            val userId = appPreferences.getUserId() ?: return Result.failure(Failure.UnknownFailure("User not logged in"))
            val request = CreateReviewRequest(userId, productId, rating, review)
            val createdReview = reviewApiService.addReview(request).toDomain()
            Result.success(createdReview)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun updateReview(reviewId: String, rating: Int, review: String): Result<Review> {
        return try {
            val request = UpdateReviewRequest(rating, review)
            val updatedReview = reviewApiService.updateReview(reviewId, request).toDomain()
            Result.success(updatedReview)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun deleteReview(reviewId: String): Result<Unit> {
        return try {
            reviewApiService.deleteReview(reviewId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun addReply(reviewId: String, reply: String): Result<Reply> {
        return try {
            val userId = appPreferences.getUserId() ?: return Result.failure(Failure.UnknownFailure("User not logged in"))
            val request = CreateReplyRequest(userId, reviewId, reply)
            val createdReply = reviewApiService.addReply(request).toDomain()
            Result.success(createdReply)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }
}
