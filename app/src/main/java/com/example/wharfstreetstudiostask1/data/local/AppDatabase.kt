package com.example.wharfstreetstudiostask1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Provides access to the [PostDao] for performing database operations
 * related to the [PostEntity] table.
 *
 * @return an instance of [PostDao]
 */
@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the [PostDao] for performing database operations
     * related to the [PostEntity] table.
     *
     * @return an instance of [PostDao]
     */
    abstract fun postDao(): PostDao
}

