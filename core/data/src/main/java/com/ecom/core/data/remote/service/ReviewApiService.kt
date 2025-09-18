package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CreateReplyRequest
import com.ecom.core.data.dto.CreateReviewRequest
import com.ecom.core.data.dto.ReplyDto
import com.ecom.core.data.dto.ReviewDto
import com.ecom.core.data.dto.UpdateReviewRequest

interface ReviewApiService {
    suspend fun getReviewsForProduct(productId: String): List<ReviewDto>
    suspend fun addReview(request: CreateReviewRequest): ReviewDto
    suspend fun updateReview(reviewId: String, request: UpdateReviewRequest): ReviewDto
    suspend fun deleteReview(reviewId: String)

    suspend fun addReply(request: CreateReplyRequest): ReplyDto
}
