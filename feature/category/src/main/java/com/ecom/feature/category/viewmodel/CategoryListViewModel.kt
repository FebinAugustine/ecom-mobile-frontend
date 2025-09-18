package com.ecom.feature.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.common.Failure
import com.ecom.core.domain.usecase.DeleteCategoryUseCase
import com.ecom.core.domain.usecase.GetCategoriesUseCase
import com.ecom.feature.category.CategoryListEffect
import com.ecom.feature.category.CategoryListIntent
import com.ecom.feature.category.CategoryListState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CategoryListViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryListState())
    val state = _state.asStateFlow()

    private val _effect = Channel<CategoryListEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(CategoryListIntent.FetchCategories)
    }

    fun onIntent(intent: CategoryListIntent) {
        viewModelScope.launch {
            when (intent) {
                is CategoryListIntent.FetchCategories -> fetchCategories()
                is CategoryListIntent.DeleteCategory -> deleteCategory(intent.categoryId)
            }
        }
    }

    private suspend fun fetchCategories() {
        _state.value = _state.value.copy(isLoading = true)
        getCategoriesUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, categories = it)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(CategoryListEffect.ShowError(errorMessage))
            }
    }

    private suspend fun deleteCategory(categoryId: String) {
        deleteCategoryUseCase(categoryId)
            .onSuccess { fetchCategories() } // Refresh the list after deleting
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _effect.send(CategoryListEffect.ShowError(errorMessage))
            }
    }
}
