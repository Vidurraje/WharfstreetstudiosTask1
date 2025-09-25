package com.example.wharfstreetstudiostask1.data.local

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */
import androidx.room.*
@Dao
interface PostDao {

    /**
     * Inserts a [PostEntity] into the database.
     * If a conflict occurs (e.g. duplicate primary key), the existing record is replaced.
     *
     * @param post The post to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)

    /**
     * Updates an existing [PostEntity] in the database.
     *
     * @param post The post with updated values.
     */
    @Update
    suspend fun updatePost(post: PostEntity)

    /**
     * Deletes a [PostEntity] from the database.
     *
     * @param post The post to delete.
     */
    @Delete
    suspend fun deletePost(post: PostEntity)

    /**
     * Retrieves all posts from the database, ordered by descending ID (most recent first).
     *
     * @return A list of all [PostEntity] records.
     */
    @Query("SELECT * FROM posts ORDER BY id DESC")
    suspend fun getAllPosts(): List<PostEntity>

    /**
     * Retrieves a single post from the database by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The [PostEntity] with the given ID, or null if not found.
     */
    @Query("SELECT * FROM posts WHERE id = :id")
    suspend fun getPostById(id: Int): PostEntity?
}

