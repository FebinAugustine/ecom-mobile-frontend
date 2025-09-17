package com.ecom.feature.products.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.GetProductByIdUseCase
import com.ecom.feature.products.SingleProductEffect
import com.ecom.feature.products.SingleProductIntent
import com.ecom.feature.products.SingleProductState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SingleProductViewModel(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(SingleProductState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SingleProductEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        savedStateHandle.get<String>("productId")?.let {
            onIntent(SingleProductIntent.FetchProductById(it))
        }
    }

    fun onIntent(intent: SingleProductIntent) {
        viewModelScope.launch {
            when (intent) {
                is SingleProductIntent.FetchProductById -> {
                    fetchProductById(intent.productId)
                }
            }
        }
    }

    private suspend fun fetchProductById(productId: String) {
        _state.value = _state.value.copy(isLoading = true)
        getProductByIdUseCase(productId)
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, product = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(SingleProductEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
