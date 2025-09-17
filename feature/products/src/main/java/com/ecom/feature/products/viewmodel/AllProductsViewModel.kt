package com.ecom.feature.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecom.core.domain.usecase.GetAllProductsUseCase
import com.ecom.feature.products.AllProductsEffect
import com.ecom.feature.products.AllProductsIntent
import com.ecom.feature.products.AllProductsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AllProductsViewModel(private val getAllProductsUseCase: GetAllProductsUseCase) : ViewModel() {

    private val _state = MutableStateFlow(AllProductsState())
    val state = _state.asStateFlow()

    private val _effect = Channel<AllProductsEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(AllProductsIntent.FetchAllProducts)
    }

    fun onIntent(intent: AllProductsIntent) {
        viewModelScope.launch {
            when (intent) {
                AllProductsIntent.FetchAllProducts -> {
                    fetchAllProducts()
                }
            }
        }
    }

    private suspend fun fetchAllProducts() {
        _state.value = _state.value.copy(isLoading = true)
        getAllProductsUseCase()
            .onSuccess {
                _state.value = _state.value.copy(isLoading = false, products = it)
            }
            .onFailure {
                _state.value = _state.value.copy(isLoading = false)
                _effect.send(AllProductsEffect.ShowError(it.message ?: "An unknown error occurred"))
            }
    }
}
