package com.ecom.core.data.repository

import com.ecom.core.data.dto.CreateOrderRequest
import com.ecom.core.data.dto.toDomain
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.remote.service.OrderApiService
import com.ecom.core.domain.model.Order
import com.ecom.core.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val orderApiService: OrderApiService,
    private val appPreferences: AppPreferences
) : OrderRepository {

    override suspend fun getUserOrders(): Result<List<Order>> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            val orders = orderApiService.getUserOrders(userId).map { it.toDomain() }
            Result.success(orders)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createOrder(order: Order): Result<Order> {
        return try {
            val userId = appPreferences.getUserId()
            if (userId == null) return Result.failure(Exception("User not logged in"))

            // This is a simplified mapping. A real app would need more details.
            val request = CreateOrderRequest(
                userId = userId,
                sellerId = "", // Placeholder
                products = emptyList(), // Placeholder
                total = order.total,
                deliveryAddress = "", // Placeholder
                paymentMethod = "", // Placeholder
                paymentStatus = "", // Placeholder
                status = order.status
            )
            val createdOrder = orderApiService.createOrder(request).toDomain()
            Result.success(createdOrder)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
