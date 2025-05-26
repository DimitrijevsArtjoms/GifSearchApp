package com.example.gifsearch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.gifsearch.ui.screens.GifDetailScreen
import com.example.gifsearch.ui.screens.SearchScreen


@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") {
            SearchScreen(
                onGifClick = { gifId, gifUrl, title -> navController.navigate("detail/${gifUrl}/${title}") }
            )
        }
        composable(
            route = "detail/{gifUrl}/{title}", arguments = listOf(
                navArgument("gifUrl") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val gifUrl = backStackEntry.arguments?.getString("gifUrl") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            GifDetailScreen(gifUrl = gifUrl, title = title)
        }
    }
}