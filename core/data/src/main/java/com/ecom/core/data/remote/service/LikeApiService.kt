package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CreateLikeRequest
import com.ecom.core.data.dto.LikeDto

interface LikeApiService {
    suspend fun likeProduct(request: CreateLikeRequest): LikeDto
    suspend fun unlikeProduct(likeId: String)
}
