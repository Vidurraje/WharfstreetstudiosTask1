package com.example.wharfstreetstudiostask1.ui.screen

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wharfstreetstudiostask1.data.local.PostEntity
import com.example.wharfstreetstudiostask1.ui.navigation.Screen
import com.example.wharfstreetstudiostask1.ui.theme.backGround
import com.example.wharfstreetstudiostask1.ui.theme.backGroundTextFiled
import com.example.wharfstreetstudiostask1.ui.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostScreen(navController: NavController, viewModel: PostViewModel, postId: Int) {

    var post by remember { mutableStateOf<PostEntity?>(null) }

    LaunchedEffect(postId) {
        post = viewModel.repo.getById(postId)
    }

    post?.let { current ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Edit POST",
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
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var postTitle by remember { mutableStateOf(current.title) }
                var description by remember { mutableStateOf(current.description) }
                var author by remember { mutableStateOf(current.author) }
                var likes by remember { mutableIntStateOf(current.likes) }
                var dislikes by remember { androidx.compose.runtime.mutableIntStateOf(current.dislikes) }

                // Post Title
                Text(
                    "Post Title",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = postTitle,
                    onValueChange = { postTitle = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundTextFiled.copy(alpha = 0.2f),
                        unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    "Description",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundTextFiled.copy(alpha = 0.2f),
                        unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Author
                Text("Author", modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = author,
                    onValueChange = { author = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = backGroundTextFiled.copy(alpha = 0.2f),
                        unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f)
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Submit Button
                Button(
                    onClick = {
                        viewModel.addPost(
                            PostEntity(
                                title = postTitle,
                                description = description,
                                author = author,
                                likes = likes,
                                dislikes = dislikes
                            )
                        )
                        navController.navigate(Screen.List.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backGroundTextFiled)
                ) {
                    Text(
                        "Save Changes",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

