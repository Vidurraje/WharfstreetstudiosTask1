package com.example.wharfstreetstudiostask1.data.repository

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */

import com.example.wharfstreetstudiostask1.data.local.PostDao
import com.example.wharfstreetstudiostask1.data.local.PostEntity
import jakarta.inject.Inject


/**
 * Implementation of [PostRepository] that delegates data operations
 * to the [PostDao] provided by Room.
 *
 * This class is annotated with [Inject] to support constructor injection
 * (e.g., with Dagger/Hilt).
 *
 * @property dao The DAO used to perform database operations for [PostEntity].
 */
class PostRepositoryImpl @Inject constructor(
    private val dao: PostDao
) : PostRepository {

    /**
     * Inserts a post into the database via [PostDao.insertPost].
     */
    override suspend fun insert(post: PostEntity) = dao.insertPost(post)

    /**
     * Updates an existing post in the database via [PostDao.updatePost].
     */
    override suspend fun update(post: PostEntity) = dao.updatePost(post)

    /**
     * Deletes a post from the database via [PostDao.deletePost].
     */
    override suspend fun delete(post: PostEntity) = dao.deletePost(post)

    /**
     * Retrieves all posts from the database via [PostDao.getAllPosts].
     *
     * @return A list of [PostEntity] objects.
     */
    override suspend fun getAll(): List<PostEntity> = dao.getAllPosts()

    /**
     * Retrieves a specific post by its ID via [PostDao.getPostById].
     *
     * @param id The ID of the post to retrieve.
     * @return The [PostEntity] with the given ID, or null if not found.
     */
    override suspend fun getById(id: Int): PostEntity? = dao.getPostById(id)
}

