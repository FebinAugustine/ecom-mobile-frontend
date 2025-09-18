package com.ecom.feature.products

// A generic interface for MVI states
interface LikeViewState

// A generic interface for MVI intents
interface LikeViewIntent

// A generic interface for MVI effects
interface LikeViewEffect

data class LikeState(
    val isLiked: Boolean = false,
    val likeId: String? = null,
    val error: String? = null
) : LikeViewState

sealed class LikeIntent : LikeViewIntent {
    data class LikeProduct(val productId: String) : LikeIntent()
    data class UnlikeProduct(val likeId: String) : LikeIntent()
}

sealed class LikeEffect : LikeViewEffect {
    data class ShowError(val message: String) : LikeEffect()
}
