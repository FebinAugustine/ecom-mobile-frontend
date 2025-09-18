package com.ecom.feature.category

import com.ecom.core.domain.model.Category

// A generic interface for MVI states
interface ViewState

// A generic interface for MVI intents
interface ViewIntent

// A generic interface for MVI effects
interface ViewEffect

// --- Category List ---
data class CategoryListState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String? = null
) : ViewState

sealed class CategoryListIntent : ViewIntent {
    object FetchCategories : CategoryListIntent()
    data class DeleteCategory(val categoryId: String) : CategoryListIntent()
}

sealed class CategoryListEffect : ViewEffect {
    data class ShowError(val message: String) : CategoryListEffect()
}

// --- Add/Edit Category ---
data class AddEditCategoryState(
    val isLoading: Boolean = false,
    val name: String = "",
    val image: String = "",
    val error: String? = null
) : ViewState

sealed class AddEditCategoryIntent : ViewIntent {
    data class SetName(val name: String) : AddEditCategoryIntent()
    data class SetImage(val image: String) : AddEditCategoryIntent()
    object SubmitCategory : AddEditCategoryIntent()
}

sealed class AddEditCategoryEffect : ViewEffect {
    object CategorySubmitted : AddEditCategoryEffect()
    data class ShowError(val message: String) : AddEditCategoryEffect()
}
