package com.febin.ecom.navigation

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecom.feature.authentication.navigation.authNavGraph
import com.ecom.feature.common_screens.navigation.commonScreensNavGraph

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "common") {
        commonScreensNavGraph(navController)
        authNavGraph(navController)
        composable("home") {
            Text("Welcome to Ecom!")
        }
    }
}
