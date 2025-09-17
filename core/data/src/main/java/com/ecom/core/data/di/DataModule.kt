package com.ecom.core.data.di

import android.content.Context
import com.ecom.core.data.local.AppPreferences
import com.ecom.core.data.local.AppPreferencesImpl
import com.ecom.core.data.remote.createHttpClient
import com.ecom.core.data.remote.service.AuthApiService
import com.ecom.core.data.remote.service.AuthApiServiceImpl
import com.ecom.core.data.remote.service.UserApiService
import com.ecom.core.data.remote.service.UserApiServiceImpl
import com.ecom.core.data.repository.ProductRepositoryImpl
import com.ecom.core.data.repository.UserRepositoryImpl
import com.ecom.core.domain.repository.ProductRepository
import com.ecom.core.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { createHttpClient() }

    single {
        androidContext().getSharedPreferences("ecom_prefs", Context.MODE_PRIVATE)
    }

    single<AppPreferences> {
        AppPreferencesImpl(get())
    }

    single<AuthApiService> {
        AuthApiServiceImpl(get())
    }

    single<UserApiService> {
        UserApiServiceImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get(), get())
    }

    single<ProductRepository> {
        ProductRepositoryImpl()
    }
}
