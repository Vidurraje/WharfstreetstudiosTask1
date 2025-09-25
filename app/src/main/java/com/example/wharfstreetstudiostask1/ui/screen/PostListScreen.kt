package com.example.wharfstreetstudiostask1.ui.screen
/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wharfstreetstudiostask1.ui.navigation.Screen
import com.example.wharfstreetstudiostask1.ui.theme.backGround
import com.example.wharfstreetstudiostask1.ui.viewmodel.PostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostListScreen(
    viewModel: PostViewModel,
    navController: NavController
) {
    val posts = viewModel.posts.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadPosts()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "All POST",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backGround,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = backGround,
                onClick = { navController.navigate(Screen.Add.route) },
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Post", tint = Color.White)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(posts) { post ->
                    PostCard(
                        post = post, onReadMoreClick = {
                            navController.navigate(Screen.View.createRoute(post.id))
                        },
                        onDislikeClick = {
                            viewModel.updatePost(post.copy(dislikes = post.dislikes + 1))
                        },
                        onlikeClick = { viewModel.updatePost(post.copy(likes = post.likes + 1)) }
                    )
                }
            }
        }
    }
}

