package com.ecom.feature.products.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.products.ui.AllProductsScreen
import com.ecom.feature.products.ui.SingleProductScreen

fun NavGraphBuilder.productsNavGraph(navController: NavController) {
    navigation(
        startDestination = "all_products",
        route = "products"
    ) {
        composable("all_products") {
            AllProductsScreen(
                onNavigateToProductDetails = { productId ->
                    navController.navigate("single_product/$productId")
                }
            )
        }
        composable("single_product/{productId}") {
            SingleProductScreen()
        }
    }
}
