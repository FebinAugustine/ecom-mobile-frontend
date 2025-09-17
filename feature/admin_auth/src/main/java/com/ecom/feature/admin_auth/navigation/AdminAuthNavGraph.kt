package com.ecom.feature.admin_auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.admin_auth.ui.AdminLoginScreen
import com.ecom.feature.admin_auth.ui.AdminSignupScreen

fun NavGraphBuilder.adminAuthNavGraph(navController: NavController) {
    navigation(
        startDestination = "admin_login",
        route = "admin_auth"
    ) {
        composable("admin_login") {
            AdminLoginScreen(
                onNavigateToAdminDashboard = {
                    navController.navigate("admin_dashboard") {
                        popUpTo("admin_auth") { inclusive = true }
                    }
                },
                onNavigateToSignUp = { navController.navigate("admin_signup") }
            )
        }
        composable("admin_signup") {
            AdminSignupScreen(
                onNavigateToAdminDashboard = {
                    navController.navigate("admin_dashboard") {
                        popUpTo("admin_auth") { inclusive = true }
                    }
                }
            )
        }
    }
}
