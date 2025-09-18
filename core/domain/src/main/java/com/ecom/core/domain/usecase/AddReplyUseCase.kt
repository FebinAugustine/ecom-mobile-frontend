package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ReviewRepository

class AddReplyUseCase(private val reviewRepository: ReviewRepository) {
    suspend operator fun invoke(reviewId: String, reply: String) = reviewRepository.addReply(reviewId, reply)
}
