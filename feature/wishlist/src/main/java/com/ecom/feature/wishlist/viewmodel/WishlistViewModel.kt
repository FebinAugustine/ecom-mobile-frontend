package com.ecom.feature.wishlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.AddToWishlistUseCase
import com.ecom.core.domain.usecase.GetWishlistUseCase
import com.ecom.core.domain.usecase.RemoveFromWishlistUseCase
import com.ecom.feature.wishlist.WishlistEffect
import com.ecom.feature.wishlist.WishlistIntent
import com.ecom.feature.wishlist.WishlistState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val getWishlistUseCase: GetWishlistUseCase,
    private val addToWishlistUseCase: AddToWishlistUseCase,
    private val removeFromWishlistUseCase: RemoveFromWishlistUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WishlistState())
    val state = _state.asStateFlow()

    private val _effect = Channel<WishlistEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(WishlistIntent.FetchWishlist)
    }

    fun onIntent(intent: WishlistIntent) {
        viewModelScope.launch {
            when (intent) {
                is WishlistIntent.FetchWishlist -> fetchWishlist()
                is WishlistIntent.AddToWishlist -> addToWishlist(intent.productId)
                is WishlistIntent.RemoveFromWishlist -> removeFromWishlist(intent.wishlistItemId)
            }
        }
    }

    private suspend fun fetchWishlist() {
        _state.value = _state.value.copy(isLoading = true)
        getWishlistUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, wishlistItems = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(WishlistEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }

    private suspend fun addToWishlist(productId: String) {
        addToWishlistUseCase(productId)
            .onSuccess { fetchWishlist() } // Refresh wishlist after adding
            .onFailure { 
                _effect.send(WishlistEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }

    private suspend fun removeFromWishlist(wishlistItemId: String) {
        removeFromWishlistUseCase(wishlistItemId)
            .onSuccess { fetchWishlist() } // Refresh wishlist after removing
            .onFailure { 
                _effect.send(WishlistEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
