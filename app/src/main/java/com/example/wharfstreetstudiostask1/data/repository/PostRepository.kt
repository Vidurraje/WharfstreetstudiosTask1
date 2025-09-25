package com.example.wharfstreetstudiostask1.data.repository
/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.wharfstreetstudiostask1.data.local.PostEntity


/**
 * Repository interface for managing [PostEntity] data operations.
 *
 * This abstraction allows you to decouple data access logic from the rest of the app,
 * making it easier to test and maintain.
 */
interface PostRepository {

    /**
     * Inserts a new post into the data source.
     *
     * @param post The [PostEntity] to insert.
     */
    @Insert
    suspend fun insert(post: PostEntity)

    /**
     * Updates an existing post in the data source.
     *
     * @param post The [PostEntity] with updated fields.
     */
    @Update
    suspend fun update(post: PostEntity)

    /**
     * Deletes a post from the data source.
     *
     * @param post The [PostEntity] to delete.
     */
    @Delete
    suspend fun delete(post: PostEntity)

    /**
     * Retrieves all posts from the data source.
     *
     * @return A list of all [PostEntity] objects.
     */
    suspend fun getAll(): List<PostEntity>

    /**
     * Retrieves a single post by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The matching [PostEntity], or null if not found.
     */
    suspend fun getById(id: Int): PostEntity?
}


