package com.ecom.feature.orders.di

import com.ecom.core.domain.usecase.CreateOrderUseCase
import com.ecom.core.domain.usecase.GetUserOrdersUseCase
import com.ecom.feature.orders.viewmodel.OrderHistoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ordersModule = module {
    // Use Cases
    factory { GetUserOrdersUseCase(get()) }
    factory { CreateOrderUseCase(get()) }

    // ViewModel
    viewModel { OrderHistoryViewModel(get()) }
}
