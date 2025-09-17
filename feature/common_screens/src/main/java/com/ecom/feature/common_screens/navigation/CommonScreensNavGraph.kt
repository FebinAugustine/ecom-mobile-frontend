package com.ecom.feature.common_screens.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.common_screens.ui.PrivacyPolicyScreen
import com.ecom.feature.common_screens.ui.ReturnPolicyScreen
import com.ecom.feature.common_screens.ui.SplashScreen
import com.ecom.feature.common_screens.ui.TermsAndConditionsScreen
import com.ecom.feature.common_screens.ui.WelcomeOnboardingScreen

fun NavGraphBuilder.commonScreensNavGraph(navController: NavController) {
    navigation(
        startDestination = "splash",
        route = "common"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateToNext = { 
                    navController.navigate("auth") {
                        popUpTo("common") { inclusive = true }
                    }
                }
            )
        }
        composable("welcome") {
            WelcomeOnboardingScreen(
                onNavigateToNext = { 
                    navController.navigate("auth") {
                        popUpTo("common") { inclusive = true }
                    }
                }
            )
        }
        composable("privacy_policy") {
            PrivacyPolicyScreen()
        }
        composable("terms_and_conditions") {
            TermsAndConditionsScreen()
        }
        composable("return_policy") {
            ReturnPolicyScreen()
        }
    }
}
