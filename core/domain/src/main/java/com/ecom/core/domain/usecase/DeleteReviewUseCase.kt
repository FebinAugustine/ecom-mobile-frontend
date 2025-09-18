package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.ReviewRepository

class DeleteReviewUseCase(private val reviewRepository: ReviewRepository) {
    suspend operator fun invoke(reviewId: String) = reviewRepository.deleteReview(reviewId)
}
