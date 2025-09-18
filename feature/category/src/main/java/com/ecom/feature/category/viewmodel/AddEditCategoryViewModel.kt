package com.ecom.feature.category.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.common.Failure
import com.ecom.core.domain.usecase.CreateCategoryUseCase
import com.ecom.core.domain.usecase.UpdateCategoryUseCase
import com.ecom.feature.category.AddEditCategoryEffect
import com.ecom.feature.category.AddEditCategoryIntent
import com.ecom.feature.category.AddEditCategoryState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditCategoryViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditCategoryState())
    val state = _state.asStateFlow()

    private val _effect = Channel<AddEditCategoryEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: AddEditCategoryIntent) {
        viewModelScope.launch {
            when (intent) {
                is AddEditCategoryIntent.SetName -> {
                    _state.value = _state.value.copy(name = intent.name)
                }
                is AddEditCategoryIntent.SetImage -> {
                    _state.value = _state.value.copy(image = intent.image)
                }
                is AddEditCategoryIntent.SubmitCategory -> {
                    submitCategory()
                }
            }
        }
    }

    private suspend fun submitCategory() {
        _state.value = _state.value.copy(isLoading = true)
        // In a real app, you would differentiate between create and update
        createCategoryUseCase(_state.value.name, _state.value.image)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AddEditCategoryEffect.CategorySubmitted)
            }
            .onFailure { throwable ->
                val errorMessage = (throwable as? Failure)?.message ?: "An unknown error occurred"
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AddEditCategoryEffect.ShowError(errorMessage))
            }
    }
}
