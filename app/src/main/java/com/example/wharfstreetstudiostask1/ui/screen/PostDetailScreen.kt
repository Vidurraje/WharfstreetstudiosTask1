package com.example.wharfstreetstudiostask1.ui.screen
/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wharfstreetstudiostask1.R
import com.example.wharfstreetstudiostask1.data.local.PostEntity
import com.example.wharfstreetstudiostask1.ui.navigation.Screen
import com.example.wharfstreetstudiostask1.ui.theme.backGround
import com.example.wharfstreetstudiostask1.ui.theme.backGroundTextFiled
import com.example.wharfstreetstudiostask1.ui.viewmodel.PostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewPostScreen(navController: NavController, viewModel: PostViewModel, postId: Int) {
    var post by remember { mutableStateOf<PostEntity?>(null) }
    LaunchedEffect(postId) {
        post = viewModel.repo.getById(postId) // needs repo reference or ViewModel method
    }

    post?.let { current ->
        var title by remember { mutableStateOf(current.title) }
        var desc by remember { mutableStateOf(current.description) }
        var author by remember { mutableStateOf(current.author) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "VIEW POST",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = backGround,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Post Title
                Text(
                    "Post Title",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    "Description",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    desc, style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Author
                Text(
                    "By Author",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    author,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Likes/Dislikes
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Like Button/Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_thumb_up_24), // Placeholder icon for likes
                            contentDescription = "Likes", modifier = Modifier.size(24.dp)
                        )
                        Text("5", modifier = Modifier.padding(start = 4.dp))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Dislike Button/Icon
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_thumb_down_24), // Placeholder icon for dislikes
                            contentDescription = "Dislikes", modifier = Modifier.size(24.dp)
                        )
                        Text("2", modifier = Modifier.padding(start = 4.dp))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))

                // Edit and Delete Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Screen.Edit.createRoute(post!!.id))
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE3F2FD)), // Light blue
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(backGroundTextFiled)
                        )
                    ) {
                        Text(
                            "EDIT",
                            color = backGroundTextFiled,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            viewModel.deletePost(
                                current.copy(
                                    title = title, description = desc, author = author
                                )
                            )
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFDE8E8)), // Light red
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = SolidColor(Color(0xFFE53935))
                        )
                    ) {
                        Text(
                            "DELETE",
                            color = Color(0xFFE53935),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}