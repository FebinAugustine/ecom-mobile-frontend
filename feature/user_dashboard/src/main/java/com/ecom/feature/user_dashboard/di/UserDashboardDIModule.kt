package com.ecom.feature.user_dashboard.di

import com.ecom.core.domain.usecase.GetUserProfileUseCase
import com.ecom.feature.user_dashboard.viewmodel.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userDashboardModule = module {
    factory { GetUserProfileUseCase(get()) }
    viewModel { UserProfileViewModel(get()) }
}
