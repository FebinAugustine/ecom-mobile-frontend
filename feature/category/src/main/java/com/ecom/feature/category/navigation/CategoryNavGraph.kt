package com.ecom.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ecom.feature.category.ui.AddEditCategoryScreen
import com.ecom.feature.category.ui.CategoryListScreen

fun NavGraphBuilder.categoryNavGraph(navController: NavController) {
    navigation(
        startDestination = "category_list",
        route = "category"
    ) {
        composable("category_list") {
            CategoryListScreen(
                onNavigateToEditCategory = { categoryId ->
                    navController.navigate("add_edit_category/$categoryId")
                }
            )
        }
        composable("add_edit_category/{categoryId}") { backStackEntry ->
            AddEditCategoryScreen(
                onCategorySubmitted = { navController.popBackStack() }
            )
        }
        composable("add_category") { // Route for adding a new category
            AddEditCategoryScreen(
                onCategorySubmitted = { navController.popBackStack() }
            )
        }
    }
}
