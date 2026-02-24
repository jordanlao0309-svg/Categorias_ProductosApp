package com.example.categoriasproductosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.categoriasproductosapp.ui.CategoryScreen
import com.example.categoriasproductosapp.ui.CategoryViewModel
import com.example.categoriasproductosapp.ui.ProductScreen
import com.example.categoriasproductosapp.ui.ProductViewModel
import com.example.categoriasproductosapp.ui.theme.CategoriasProductosAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CategoriasProductosAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val categoryViewModel: CategoryViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()

    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            CategoryScreen(
                viewModel = categoryViewModel,
                onCategoryClick = { categoryId ->
                    navController.navigate("products/$categoryId")
                }
            )
        }
        composable(
            route = "products/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            ProductScreen(
                viewModel = productViewModel,
                categoryId = categoryId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
