package com.ecom.feature.admin_auth.di

import com.ecom.core.domain.usecase.LoginAdminUseCase
import com.ecom.core.domain.usecase.RegisterAdminUseCase
import com.ecom.feature.admin_auth.viewmodel.AdminLoginViewModel
import com.ecom.feature.admin_auth.viewmodel.AdminSignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val adminAuthModule = module {
    // Use Cases
    factory { LoginAdminUseCase(get()) }
    factory { RegisterAdminUseCase(get()) }

    // ViewModels
    viewModel { AdminLoginViewModel(get()) }
    viewModel { AdminSignupViewModel(get()) }
}
