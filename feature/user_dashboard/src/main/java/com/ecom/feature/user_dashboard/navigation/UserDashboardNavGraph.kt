package com.ecom.feature.user_dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.user_dashboard.ui.UserChangePasswordScreen
import com.ecom.feature.user_dashboard.ui.UserDashboardScreen
import com.ecom.feature.user_dashboard.ui.UserProfileScreen

fun NavGraphBuilder.userDashboardNavGraph(navController: NavController) {
    navigation(
        startDestination = "user_dashboard",
        route = "home" // This makes the dashboard the main "home" route
    ) {
        composable("user_dashboard") {
            UserDashboardScreen()
        }
        composable("user_profile") {
            UserProfileScreen()
        }
        composable("user_change_password") {
            UserChangePasswordScreen()
        }
    }
}
