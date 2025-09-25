package com.example.wharfstreetstudiostask1.ui.navigation

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wharfstreetstudiostask1.ui.screen.AddPostScreen
import com.example.wharfstreetstudiostask1.ui.screen.EditPostScreen
import com.example.wharfstreetstudiostask1.ui.screen.PostListScreen
import com.example.wharfstreetstudiostask1.ui.screen.ViewPostScreen
import com.example.wharfstreetstudiostask1.ui.viewmodel.PostViewModel

/**
 * Represents the different navigation destinations/screens in the app,
 * with their associated route strings.
 *
 * Routes with parameters (like postId) include helper functions to
 * create full route strings dynamically.
 */
sealed class Screen(val route: String) {
    /** Screen showing the list of posts */
    object List : Screen("list")

    /** Screen for adding a new post */
    object Add : Screen("add")

    /** Screen for viewing a specific post */
    object View : Screen("view/{postId}") {
        /**
         * Creates a concrete route string for viewing a post with the given [postId].
         */
        fun createRoute(postId: Int) = "view/$postId"
    }

    /** Screen for editing a specific post */
    object Edit : Screen("edit/{postId}") {
        /**
         * Creates a concrete route string for editing a post with the given [postId].
         */
        fun createRoute(postId: Int) = "edit/$postId"
    }
}

/**
 * Sets up the navigation graph for the app using Jetpack Compose Navigation.
 *
 * Defines the navigation routes and their corresponding composable screens.
 *
 * @param navController Controller to manage navigation actions.
 * @param modifier Optional [Modifier] to be applied to the NavHost.
 * @param viewModel Instance of [PostViewModel] used across screens (default injected via Hilt).
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List.route,
        modifier = modifier
    ) {
        // List screen
        composable(Screen.List.route) {
            PostListScreen(viewModel, navController)
        }

        // Add post screen
        composable(Screen.Add.route) {
            AddPostScreen(navController, viewModel)
        }

        // View post screen, expects an integer postId argument
        composable(
            route = Screen.View.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            ViewPostScreen(navController, viewModel, postId)
        }

        // Edit post screen, expects an integer postId argument
        composable(
            route = Screen.Edit.route,
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId") ?: 0
            EditPostScreen(navController, viewModel, postId)
        }
    }
}



