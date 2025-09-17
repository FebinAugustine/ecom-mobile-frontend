package com.ecom.core.domain.repository

import com.ecom.core.domain.model.Order

interface OrderRepository {
    suspend fun getUserOrders(): Result<List<Order>>
    suspend fun createOrder(order: Order): Result<Order>
}
