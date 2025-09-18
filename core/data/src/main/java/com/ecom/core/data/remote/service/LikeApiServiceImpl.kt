package com.ecom.core.data.remote.service

import com.ecom.core.data.dto.CreateLikeRequest
import com.ecom.core.data.dto.LikeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class LikeApiServiceImpl(private val httpClient: HttpClient) : LikeApiService {

    override suspend fun likeProduct(request: CreateLikeRequest): LikeDto {
        return httpClient.post("likes") {
            setBody(request)
        }.body()
    }

    override suspend fun unlikeProduct(likeId: String) {
        httpClient.delete("likes/$likeId")
    }
}
