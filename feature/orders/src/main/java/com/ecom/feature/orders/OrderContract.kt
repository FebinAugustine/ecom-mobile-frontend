package com.ecom.feature.orders

import com.ecom.core.domain.model.Order

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

data class OrderHistoryState(
    val isLoading: Boolean = false,
    val orders: List<Order> = emptyList(),
    val error: String? = null
) : ViewState

sealed class OrderHistoryIntent : ViewIntent {
    object FetchOrderHistory : OrderHistoryIntent()
}

sealed class OrderHistoryEffect : ViewEffect {
    data class ShowError(val message: String) : OrderHistoryEffect()
}
