package com.ecom.feature.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.common.Failure
import com.ecom.core.domain.usecase.LikeProductUseCase
import com.ecom.core.domain.usecase.UnlikeProductUseCase
import com.ecom.feature.products.LikeEffect
import com.ecom.feature.products.LikeIntent
import com.ecom.feature.products.LikeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LikeViewModel(
    private val likeProductUseCase: LikeProductUseCase,
    private val unlikeProductUseCase: UnlikeProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LikeState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LikeEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: LikeIntent) {
        viewModelScope.launch {
            when (intent) {
                is LikeIntent.LikeProduct -> likeProduct(intent.productId)
                is LikeIntent.UnlikeProduct -> unlikeProduct(intent.likeId)
            }
        }
    }

    private suspend fun likeProduct(productId: String) {
        likeProductUseCase(productId)
            .onSuccess {
                _state.value = _state.value.copy(isLiked = true, likeId = it.id)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _effect.send(LikeEffect.ShowError(errorMessage))
            }
    }

    private suspend fun unlikeProduct(likeId: String) {
        unlikeProductUseCase(likeId)
            .onSuccess {
                _state.value = _state.value.copy(isLiked = false, likeId = null)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _effect.send(LikeEffect.ShowError(errorMessage))
            }
    }
}
