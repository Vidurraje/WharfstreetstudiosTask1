package com.example.wharfstreetstudiostask1.ui.viewmodel

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wharfstreetstudiostask1.data.local.PostEntity
import com.example.wharfstreetstudiostask1.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing post-related data and operations.
 *
 * Interacts with the [PostRepository] to perform CRUD operations and
 * exposes a reactive [StateFlow] of the list of posts for UI observation.
 *
 * @property repo Repository to access post data.
 */
@HiltViewModel
class PostViewModel @Inject constructor(
    val repo: PostRepository
) : ViewModel() {

    // Backing property for posts state flow
    private val _posts = MutableStateFlow<List<PostEntity>>(emptyList())

    /**
     * Publicly exposed immutable [StateFlow] of the list of posts.
     */
    val posts: StateFlow<List<PostEntity>> = _posts

    /**
     * Loads all posts from the repository asynchronously and updates the [posts] flow.
     */
    fun loadPosts() {
        viewModelScope.launch {
            _posts.value = repo.getAll()
        }
    }

    /**
     * Adds a new post by delegating to the repository, then reloads the posts list.
     *
     * @param post The [PostEntity] to add.
     */
    fun addPost(post: PostEntity) {
        viewModelScope.launch {
            repo.insert(post)
            loadPosts()
        }
    }

    /**
     * Updates an existing post by delegating to the repository, then reloads the posts list.
     *
     * @param post The [PostEntity] with updated data.
     */
    fun updatePost(post: PostEntity) {
        viewModelScope.launch {
            repo.update(post)
            loadPosts()
        }
    }

    /**
     * Deletes a post by delegating to the repository, then reloads the posts list.
     *
     * @param post The [PostEntity] to delete.
     */
    fun deletePost(post: PostEntity) {
        viewModelScope.launch {
            repo.delete(post)
            loadPosts()
        }
    }
}
