package com.ecom.feature.reviews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.common.Failure
import com.ecom.core.domain.usecase.AddReviewUseCase
import com.ecom.core.domain.usecase.UpdateReviewUseCase
import com.ecom.feature.reviews.AddEditReviewEffect
import com.ecom.feature.reviews.AddEditReviewIntent
import com.ecom.feature.reviews.AddEditReviewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditReviewViewModel(
    private val addReviewUseCase: AddReviewUseCase,
    private val updateReviewUseCase: UpdateReviewUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditReviewState())
    val state = _state.asStateFlow()

    private val _effect = Channel<AddEditReviewEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: AddEditReviewIntent) {
        viewModelScope.launch {
            when (intent) {
                is AddEditReviewIntent.SetRating -> {
                    _state.value = _state.value.copy(rating = intent.rating)
                }
                is AddEditReviewIntent.SetReviewText -> {
                    _state.value = _state.value.copy(reviewText = intent.text)
                }
                is AddEditReviewIntent.SubmitReview -> {
                    submitReview(intent.productId)
                }
            }
        }
    }

    private suspend fun submitReview(productId: String) {
        _state.value = _state.value.copy(isLoading = true)
        addReviewUseCase(productId, _state.value.rating, _state.value.reviewText)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AddEditReviewEffect.ReviewSubmitted)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AddEditReviewEffect.ShowError(errorMessage))
            }
    }
}
