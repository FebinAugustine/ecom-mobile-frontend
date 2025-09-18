package com.ecom.core.data.repository

import com.ecom.core.common.Failure
import com.ecom.core.data.dto.CreateLikeRequest
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.LikeApiService
import com.ecom.core.domain.repository.LikeRepository

class LikeRepositoryImpl(
    private val likeApiService: LikeApiService,
    private val appPreferences: AppPreferences
) : LikeRepository {

    override suspend fun likeProduct(productId: String): Result<Unit> {
        return try {
            val userId = appPreferences.getUserId() ?: return Result.failure(Failure.UnknownFailure("User not logged in"))
            val request = CreateLikeRequest(userId, productId)
            likeApiService.likeProduct(request)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }

    override suspend fun unlikeProduct(productId: String): Result<Unit> {
        return try {
            // This is a simplification. In a real app, you would need the like ID to delete it.
            // For now, we'll assume the API can handle unliking by product ID for a given user.
            likeApiService.unlikeProduct(productId) // This will likely need adjustment based on real API behavior
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Failure.fromException(e))
        }
    }
}
