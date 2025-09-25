package com.example.wharfstreetstudiostask1.ui.screen

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wharfstreetstudiostask1.R
import com.example.wharfstreetstudiostask1.data.local.PostEntity
import com.example.wharfstreetstudiostask1.ui.theme.backGround

@Composable
fun PostCard(
    post: PostEntity,
    onReadMoreClick: () -> Unit,
    onlikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title, fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.description,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_thumb_up_24),
                        contentDescription = "likes",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onlikeClick()
                            },
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.likes.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.outline_thumb_down_24),
                        contentDescription = "Dislikes", modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onDislikeClick()
                            }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.dislikes.toString())
                }

                // Submit Button
                Button(
                    onClick = {
                        onReadMoreClick()
                    },
                    modifier = Modifier
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backGround)
                ) {
                    Text(
                        "READ MORE",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}
