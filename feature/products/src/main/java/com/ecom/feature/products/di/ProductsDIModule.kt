package com.ecom.feature.products.di

import com.ecom.core.domain.usecase.GetAllProductsUseCase
import com.ecom.core.domain.usecase.GetProductByIdUseCase
import com.ecom.core.domain.usecase.GetProductsByCategoryUseCase
import com.ecom.feature.products.viewmodel.AllProductsViewModel
import com.ecom.feature.products.viewmodel.SingleProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModule = module {
    // Use Cases
    factory { GetAllProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }
    factory { GetProductsByCategoryUseCase(get()) }

    // ViewModels
    viewModel { AllProductsViewModel(get()) }
    viewModel { SingleProductViewModel(get(), get()) }
}
