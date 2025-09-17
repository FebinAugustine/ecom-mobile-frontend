package com.ecom.feature.authentication.di

import com.ecom.core.domain.usecase.LoginUserUseCase
import com.ecom.core.domain.usecase.RegisterUserUseCase
import com.ecom.core.domain.usecase.RequestForgotPasswordUseCase
import com.ecom.core.domain.usecase.VerifyOtpAndResetPasswordUseCase
import com.ecom.feature.authentication.viewmodel.ForgotPasswordOtpViewModel
import com.ecom.feature.authentication.viewmodel.ForgotPasswordViewModel
import com.ecom.feature.authentication.viewmodel.LoginViewModel
import com.ecom.feature.authentication.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    // Use Cases
    factory { LoginUserUseCase(get()) }
    factory { RegisterUserUseCase(get()) }
    factory { RequestForgotPasswordUseCase(get()) }
    factory { VerifyOtpAndResetPasswordUseCase(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { ForgotPasswordViewModel(get()) }
    viewModel { ForgotPasswordOtpViewModel(get(), get()) }
}
