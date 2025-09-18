package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.LikeRepository

class LikeProductUseCase(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(productId: String) = likeRepository.likeProduct(productId)
}
