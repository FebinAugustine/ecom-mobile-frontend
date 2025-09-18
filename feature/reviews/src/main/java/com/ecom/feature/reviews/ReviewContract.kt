package com.ecom.feature.reviews

import com.ecom.core.domain.model.Review

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- Product Reviews ---
data class ProductReviewsState(
    val isLoading: Boolean = false,
    val reviews: List<Review> = emptyList(),
    val error: String? = null
) : ViewState

sealed class ProductReviewsIntent : ViewIntent {
    data class FetchReviews(val productId: String) : ProductReviewsIntent()
    data class DeleteReview(val reviewId: String) : ProductReviewsIntent()
}

sealed class ProductReviewsEffect : ViewEffect {
    data class ShowError(val message: String) : ProductReviewsEffect()
}

// --- Add/Edit Review ---
data class AddEditReviewState(
    val isLoading: Boolean = false,
    val rating: Int = 0,
    val reviewText: String = "",
    val error: String? = null
) : ViewState

sealed class AddEditReviewIntent : ViewIntent {
    data class SetRating(val rating: Int) : AddEditReviewIntent()
    data class SetReviewText(val text: String) : AddEditReviewIntent()
    data class SubmitReview(val productId: String) : AddEditReviewIntent()
}

sealed class AddEditReviewEffect : ViewEffect {
    object ReviewSubmitted : AddEditReviewEffect()
    data class ShowError(val message: String) : AddEditReviewEffect()
}
