package com.ecom.feature.seller_auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.seller_auth.ui.SellerLoginScreen
import com.ecom.feature.seller_auth.ui.SellerSignupScreen

fun NavGraphBuilder.sellerAuthNavGraph(navController: NavController) {
    navigation(
        startDestination = "seller_login",
        route = "seller_auth"
    ) {
        composable("seller_login") {
            SellerLoginScreen(
                onNavigateToSellerDashboard = {
                    navController.navigate("seller_dashboard") {
                        popUpTo("seller_auth") { inclusive = true }
                    }
                },
                onNavigateToSignUp = { navController.navigate("seller_signup") }
            )
        }
        composable("seller_signup") {
            SellerSignupScreen(
                onNavigateToSellerDashboard = {
                    navController.navigate("seller_dashboard") {
                        popUpTo("seller_auth") { inclusive = true }
                    }
                }
            )
        }
    }
}
