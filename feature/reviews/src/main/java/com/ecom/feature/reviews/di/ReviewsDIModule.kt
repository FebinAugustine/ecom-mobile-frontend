package com.ecom.feature.reviews.di

import com.ecom.core.domain.usecase.AddReplyUseCase
import com.ecom.core.domain.usecase.AddReviewUseCase
import com.ecom.core.domain.usecase.DeleteReviewUseCase
import com.ecom.core.domain.usecase.GetReviewsForProductUseCase
import com.ecom.core.domain.usecase.UpdateReviewUseCase
import com.ecom.feature.reviews.viewmodel.AddEditReviewViewModel
import com.ecom.feature.reviews.viewmodel.ProductReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reviewsModule = module {
    // Use Cases
    factory { GetReviewsForProductUseCase(get()) }
    factory { AddReviewUseCase(get()) }
    factory { UpdateReviewUseCase(get()) }
    factory { DeleteReviewUseCase(get()) }
    factory { AddReplyUseCase(get()) }

    // ViewModels
    viewModel { ProductReviewsViewModel(get(), get()) }
    viewModel { AddEditReviewViewModel(get(), get()) }
}
