package com.ecom.feature.seller_dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.seller_dashboard.ui.AllOrdersScreen
import com.ecom.feature.seller_dashboard.ui.AllProductScreen
import com.ecom.feature.seller_dashboard.ui.CreateProductScreen
import com.ecom.feature.seller_dashboard.ui.EditProductScreen
import com.ecom.feature.seller_dashboard.ui.SellerChangePasswordScreen
import com.ecom.feature.seller_dashboard.ui.SellerDashboardScreen
import com.ecom.feature.seller_dashboard.ui.SellerInvoicesScreen
import com.ecom.feature.seller_dashboard.ui.SellerProfileScreen

fun NavGraphBuilder.sellerDashboardNavGraph(navController: NavController) {
    navigation(
        startDestination = "seller_dashboard_main",
        route = "seller_dashboard"
    ) {
        composable("seller_dashboard_main") {
            SellerDashboardScreen()
        }
        composable("seller_profile") {
            SellerProfileScreen()
        }
        composable("seller_change_password") {
            SellerChangePasswordScreen()
        }
        composable("seller_all_products") {
            AllProductScreen()
        }
        composable("seller_create_product") {
            CreateProductScreen()
        }
        composable("seller_edit_product") {
            EditProductScreen()
        }
        composable("seller_all_orders") {
            AllOrdersScreen()
        }
        composable("seller_invoices") {
            SellerInvoicesScreen()
        }
    }
}
