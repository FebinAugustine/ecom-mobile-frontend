package com.ecom.feature.authentication.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.authentication.ui.ForgotPasswordOtpScreen
import com.ecom.feature.authentication.ui.ForgotPasswordScreen
import com.ecom.feature.authentication.ui.LoginScreen
import com.ecom.feature.authentication.ui.SignupScreen

fun NavGraphBuilder.authNavGraph(navController: NavController) {
    navigation(
        startDestination = "login",
        route = "auth"
    ) {
        composable("login") {
            LoginScreen(
                onNavigateToHome = { 
                    navController.navigate("home") {
                        popUpTo("auth") { inclusive = true }
                    }
                },
                onNavigateToSignUp = { navController.navigate("signup") },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") }
            )
        }
        composable("signup") {
            SignupScreen(
                onNavigateToHome = {
                    navController.navigate("home") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }
        composable("forgot_password") {
            ForgotPasswordScreen(
                onNavigateToOtpVerification = { navController.navigate("forgot_password_otp") }
            )
        }
        composable("forgot_password_otp") {
            ForgotPasswordOtpScreen(
                onNavigateToLogin = { 
                    navController.navigate("login") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}
