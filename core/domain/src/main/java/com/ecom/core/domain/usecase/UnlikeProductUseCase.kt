package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.LikeRepository

class UnlikeProductUseCase(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(productId: String) = likeRepository.unlikeProduct(productId)
}
