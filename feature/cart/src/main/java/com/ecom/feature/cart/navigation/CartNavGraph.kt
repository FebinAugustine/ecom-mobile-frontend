package com.ecom.feature.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.cart.ui.UserCartScreen

fun NavGraphBuilder.cartNavGraph(navController: NavController) {
    navigation(
        startDestination = "user_cart",
        route = "cart"
    ) {
        composable("user_cart") {
            UserCartScreen()
        }
    }
}
