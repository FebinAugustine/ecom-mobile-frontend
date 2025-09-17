package com.ecom.feature.wishlist.di

import com.ecom.core.domain.usecase.AddToWishlistUseCase
import com.ecom.core.domain.usecase.GetWishlistUseCase
import com.ecom.core.domain.usecase.RemoveFromWishlistUseCase
import com.ecom.feature.wishlist.viewmodel.WishlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val wishlistModule = module {
    // Use Cases
    factory { GetWishlistUseCase(get()) }
    factory { AddToWishlistUseCase(get()) }
    factory { RemoveFromWishlistUseCase(get()) }

    // ViewModel
    viewModel { WishlistViewModel(get(), get(), get()) }
}
