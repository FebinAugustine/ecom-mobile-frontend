package com.ecom.feature.cart.di

import com.ecom.core.domain.usecase.AddToCartUseCase
import com.ecom.core.domain.usecase.GetCartUseCase
import com.ecom.core.domain.usecase.RemoveFromCartUseCase
import com.ecom.feature.cart.viewmodel.CartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cartModule = module {
    // Use Cases
    factory { GetCartUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { RemoveFromCartUseCase(get()) }

    // ViewModel
    viewModel { CartViewModel(get(), get(), get()) }
}
