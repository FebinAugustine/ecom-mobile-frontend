package com.ecom.core.domain.repository

interface LikeRepository {
    suspend fun likeProduct(productId: String): Result<Unit>
    suspend fun unlikeProduct(productId: String): Result<Unit>
}
