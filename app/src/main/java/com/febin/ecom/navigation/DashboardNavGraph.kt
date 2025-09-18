package com.febin.ecom.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ecom.core.data.local.AppPreferences
import com.ecom.feature.admin_dashboard.navigation.adminDashboardNavGraph
import com.ecom.feature.seller_dashboard.navigation.sellerDashboardNavGraph
import com.ecom.feature.user_dashboard.navigation.userDashboardNavGraph

fun NavGraphBuilder.dashboardNavGraph(navController: NavController, appPreferences: AppPreferences) {
    navigation(
        startDestination = getStartDestination(appPreferences),
        route = "dashboard"
    ) {
        userDashboardNavGraph(navController)
        adminDashboardNavGraph(navController)
        sellerDashboardNavGraph(navController)
    }
}

private fun getStartDestination(appPreferences: AppPreferences): String {
    return when (appPreferences.getUserType()) {
        "admin" -> "admin_dashboard"
        "seller" -> "seller_dashboard"
        else -> "user_dashboard"
    }
}
