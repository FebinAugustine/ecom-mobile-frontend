package com.ecom.feature.reviews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.reviews.ui.AddEditReviewScreen
import com.ecom.feature.reviews.ui.ProductReviewsScreen

fun NavGraphBuilder.reviewsNavGraph(navController: NavController) {
    navigation(
        startDestination = "product_reviews/{productId}",
        route = "reviews"
    ) {
        composable("product_reviews/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            ProductReviewsScreen(
                productId = productId,
            )
        }
        composable("add_edit_review/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            AddEditReviewScreen(
                productId = productId,
                onReviewSubmitted = { navController.popBackStack() }
            )
        }
    }
}
