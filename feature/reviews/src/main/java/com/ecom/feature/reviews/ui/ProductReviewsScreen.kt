package com.ecom.feature.reviews.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ecom.feature.reviews.ProductReviewsEffect
import com.ecom.feature.reviews.ProductReviewsIntent
import com.ecom.feature.reviews.viewmodel.ProductReviewsViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductReviewsScreen(
    productId: String,
    viewModel: ProductReviewsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onIntent(ProductReviewsIntent.FetchReviews(productId))
        viewModel.effect.collectLatest {
            when (it) {
                is ProductReviewsEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.reviews.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.reviews) { review ->
                        Text(text = "${review.rating}/5: ${review.review}")
                    }
                }
            } else {
                Text(text = "No reviews yet.")
            }
        }
    }
}
