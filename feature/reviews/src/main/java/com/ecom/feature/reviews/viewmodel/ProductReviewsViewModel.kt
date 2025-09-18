package com.ecom.feature.reviews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.common.Failure
import com.ecom.core.domain.usecase.DeleteReviewUseCase
import com.ecom.core.domain.usecase.GetReviewsForProductUseCase
import com.ecom.feature.reviews.ProductReviewsEffect
import com.ecom.feature.reviews.ProductReviewsIntent
import com.ecom.feature.reviews.ProductReviewsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProductReviewsViewModel(
    private val getReviewsForProductUseCase: GetReviewsForProductUseCase,
    private val deleteReviewUseCase: DeleteReviewUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProductReviewsState())
    val state = _state.asStateFlow()

    private val _effect = Channel<ProductReviewsEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: ProductReviewsIntent) {
        viewModelScope.launch {
            when (intent) {
                is ProductReviewsIntent.FetchReviews -> fetchReviews(intent.productId)
                is ProductReviewsIntent.DeleteReview -> deleteReview(intent.reviewId)
            }
        }
    }

    private suspend fun fetchReviews(productId: String) {
        _state.value = _state.value.copy(isLoading = true)
        getReviewsForProductUseCase(productId)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, reviews = it)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(ProductReviewsEffect.ShowError(errorMessage))
            }
    }

    private suspend fun deleteReview(reviewId: String) {
        deleteReviewUseCase(reviewId)
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _effect.send(ProductReviewsEffect.ShowError(errorMessage))
            }
    }
}
