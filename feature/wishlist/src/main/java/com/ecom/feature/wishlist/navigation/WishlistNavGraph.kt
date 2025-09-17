package com.ecom.feature.wishlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.wishlist.ui.UserWishlistScreen

fun NavGraphBuilder.wishlistNavGraph(navController: NavController) {
    navigation(
        startDestination = "user_wishlist",
        route = "wishlist"
    ) {
        composable("user_wishlist") {
            UserWishlistScreen()
        }
    }
}
