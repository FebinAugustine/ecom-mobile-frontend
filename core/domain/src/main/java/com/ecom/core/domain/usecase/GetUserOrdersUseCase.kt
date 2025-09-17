package com.ecom.core.domain.usecase

import com.ecom.core.domain.repository.OrderRepository

class GetUserOrdersUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke() = orderRepository.getUserOrders()
}
