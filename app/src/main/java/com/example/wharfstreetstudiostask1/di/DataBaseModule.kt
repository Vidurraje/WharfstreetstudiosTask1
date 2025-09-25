package com.example.wharfstreetstudiostask1.di

/**
 * Author: Vidurraje Deshmukh
 * Date: 2025-09-26
 */
import android.app.Application
import androidx.room.Room
import com.example.wharfstreetstudiostask1.data.local.AppDatabase
import com.example.wharfstreetstudiostask1.data.local.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



/**
 * Hilt module to provide database-related dependencies.
 *
 * This module provides a singleton instance of [AppDatabase] and
 * the corresponding [PostDao] for data access.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of the Room database [AppDatabase].
     *
     * @param app The application context required to build the database.
     * @return A singleton [AppDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "todo_db").build()

    /**
     * Provides a singleton instance of [PostDao] using the provided database.
     *
     * @param db The [AppDatabase] instance.
     * @return The [PostDao] for performing post-related database operations.
     */
    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()
}

