package com.ecom.feature.category.di

import com.ecom.core.domain.usecase.CreateCategoryUseCase
import com.ecom.core.domain.usecase.DeleteCategoryUseCase
import com.ecom.core.domain.usecase.GetCategoriesUseCase
import com.ecom.core.domain.usecase.UpdateCategoryUseCase
import com.ecom.feature.category.viewmodel.AddEditCategoryViewModel
import com.ecom.feature.category.viewmodel.CategoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoryModule = module {
    // Use Cases
    factory { GetCategoriesUseCase(get()) }
    factory { CreateCategoryUseCase(get()) }
    factory { UpdateCategoryUseCase(get()) }
    factory { DeleteCategoryUseCase(get()) }

    // ViewModels
    viewModel { CategoryListViewModel(get(), get()) }
    viewModel { AddEditCategoryViewModel(get(), get()) }
}
