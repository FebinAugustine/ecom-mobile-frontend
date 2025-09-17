package com.ecom.feature.orders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.GetUserOrdersUseCase
import com.ecom.feature.orders.OrderHistoryEffect
import com.ecom.feature.orders.OrderHistoryIntent
import com.ecom.feature.orders.OrderHistoryState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    private val getUserOrdersUseCase: GetUserOrdersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OrderHistoryState())
    val state = _state.asStateFlow()

    private val _effect = Channel<OrderHistoryEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(OrderHistoryIntent.FetchOrderHistory)
    }

    fun onIntent(intent: OrderHistoryIntent) {
        viewModelScope.launch {
            when (intent) {
                is OrderHistoryIntent.FetchOrderHistory -> fetchOrderHistory()
            }
        }
    }

    private suspend fun fetchOrderHistory() {
        _state.value = _state.value.copy(isLoading = true)
        getUserOrdersUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, orders = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(OrderHistoryEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
