package com.ecom.feature.products

import com.ecom.core.domain.model.Product

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- All Products ---
data class AllProductsState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
) : ViewState

sealed class AllProductsIntent : ViewIntent {
    object FetchAllProducts : AllProductsIntent()
}

sealed class AllProductsEffect : ViewEffect {
    data class ShowError(val message: String) : AllProductsEffect()
}

// --- Single Product ---
data class SingleProductState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
) : ViewState

sealed class SingleProductIntent : ViewIntent {
    data class FetchProductById(val productId: String) : SingleProductIntent()
}

sealed class SingleProductEffect : ViewEffect {
    data class ShowError(val message: String) : SingleProductEffect()
}
