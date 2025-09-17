package com.ecom.feature.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.AddToCartUseCase
import com.ecom.core.domain.usecase.GetCartUseCase
import com.ecom.core.domain.usecase.RemoveFromCartUseCase
import com.ecom.feature.cart.CartEffect
import com.ecom.feature.cart.CartIntent
import com.ecom.feature.cart.CartState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state = _state.asStateFlow()

    private val _effect = Channel<CartEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(CartIntent.FetchCart)
    }

    fun onIntent(intent: CartIntent) {
        viewModelScope.launch {
            when (intent) {
                is CartIntent.FetchCart -> fetchCart()
                is CartIntent.AddToCart -> addToCart(intent.productId, intent.quantity)
                is CartIntent.RemoveFromCart -> removeFromCart(intent.cartItemId)
            }
        }
    }

    private suspend fun fetchCart() {
        _state.value = _state.value.copy(isLoading = true)
        getCartUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, cartItems = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(CartEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }

    private suspend fun addToCart(productId: String, quantity: Int) {
        addToCartUseCase(productId, quantity)
            .onSuccess { fetchCart() } // Refresh cart after adding
            .onFailure { 
                _effect.send(CartEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }

    private suspend fun removeFromCart(cartItemId: String) {
        removeFromCartUseCase(cartItemId)
            .onSuccess { fetchCart() } // Refresh cart after removing
            .onFailure { 
                _effect.send(CartEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
