package com.febin.ecom.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ecom.core.ui.composables.EcomButton

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            EcomButton(text = "Hello, Ecom!") { /* TODO */ }
        }
    }
}
