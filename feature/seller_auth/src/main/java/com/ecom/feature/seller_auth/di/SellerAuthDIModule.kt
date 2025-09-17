package com.ecom.feature.seller_auth.di

import com.ecom.core.domain.usecase.LoginSellerUseCase
import com.ecom.core.domain.usecase.RegisterSellerUseCase
import com.ecom.feature.seller_auth.viewmodel.SellerLoginViewModel
import com.ecom.feature.seller_auth.viewmodel.SellerSignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sellerAuthModule = module {
    // Use Cases
    factory { LoginSellerUseCase(get()) }
    factory { RegisterSellerUseCase(get()) }

    // ViewModels
    viewModel { SellerLoginViewModel(get()) }
    viewModel { SellerSignupViewModel(get()) }
}
