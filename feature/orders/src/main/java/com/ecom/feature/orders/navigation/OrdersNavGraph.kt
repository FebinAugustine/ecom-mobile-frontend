package com.ecom.feature.orders.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.orders.ui.AllInvoicesScreen
import com.ecom.feature.orders.ui.CheckoutScreen
import com.ecom.feature.orders.ui.PaymentSuccessScreen
import com.ecom.feature.orders.ui.UserOrderHistoryScreen

fun NavGraphBuilder.ordersNavGraph(navController: NavController) {
    navigation(
        startDestination = "user_order_history",
        route = "orders"
    ) {
        composable("user_order_history") {
            UserOrderHistoryScreen()
        }
        composable("checkout") {
            CheckoutScreen()
        }
        composable("payment_success") {
            PaymentSuccessScreen()
        }
        composable("all_invoices") {
            AllInvoicesScreen()
        }
    }
}
