package com.ecom.core.domain.usecase

import com.ecom.core.domain.model.Order
import com.ecom.core.domain.repository.OrderRepository

class CreateOrderUseCase(private val orderRepository: OrderRepository) {
    suspend operator fun invoke(order: Order) = orderRepository.createOrder(order)
}
