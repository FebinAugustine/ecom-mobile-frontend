package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CreateReplyRequest
import com.ecom.core.data.dto.CreateReviewRequest
import com.ecom.core.data.dto.ReplyDto
import com.ecom.core.data.dto.ReviewDto
import com.ecom.core.data.dto.UpdateReviewRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody

class ReviewApiServiceImpl(private val httpClient: HttpClient) : ReviewApiService {

    override suspend fun getReviewsForProduct(productId: String): List<ReviewDto> {
        // Assuming an endpoint like /reviews/product/{productId}
        return httpClient.get("reviews/product/$productId").body()
    }

    override suspend fun addReview(request: CreateReviewRequest): ReviewDto {
        return httpClient.post("reviews") {
            setBody(request)
        }.body()
    }

    override suspend fun updateReview(reviewId: String, request: UpdateReviewRequest): ReviewDto {
        return httpClient.put("reviews/$reviewId") {
            setBody(request)
        }.body()
    }

    override suspend fun deleteReview(reviewId: String) {
        httpClient.delete("reviews/$reviewId")
    }

    override suspend fun addReply(request: CreateReplyRequest): ReplyDto {
        return httpClient.post("replies") {
            setBody(request)
        }.body()
    }
}
