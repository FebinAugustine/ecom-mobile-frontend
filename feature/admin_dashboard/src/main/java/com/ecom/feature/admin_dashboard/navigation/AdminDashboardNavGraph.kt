package com.ecom.feature.admin_dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.admin_dashboard.ui.AdminAllOrdersScreen
import com.ecom.feature.admin_dashboard.ui.AdminAllProductsScreen
import com.ecom.feature.admin_dashboard.ui.AdminChangePasswordScreen
import com.ecom.feature.admin_dashboard.ui.AdminDashboardScreen
import com.ecom.feature.admin_dashboard.ui.AdminProfileScreen
import com.ecom.feature.admin_dashboard.ui.AllSellersScreen
import com.ecom.feature.admin_dashboard.ui.AllUsersScreen

fun NavGraphBuilder.adminDashboardNavGraph(navController: NavController) {
    navigation(
        startDestination = "admin_dashboard_main",
        route = "admin_dashboard"
    ) {
        composable("admin_dashboard_main") {
            AdminDashboardScreen()
        }
        composable("admin_profile") {
            AdminProfileScreen()
        }
        composable("admin_change_password") {
            AdminChangePasswordScreen()
        }
        composable("admin_all_products") {
            AdminAllProductsScreen()
        }
        composable("admin_all_orders") {
            AdminAllOrdersScreen()
        }
        composable("admin_all_users") {
            AllUsersScreen()
        }
        composable("admin_all_sellers") {
            AllSellersScreen()
        }
    }
}
