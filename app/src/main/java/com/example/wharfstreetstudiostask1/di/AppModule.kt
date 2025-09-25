package com.example.wharfstreetstudiostask1.di

import com.example.wharfstreetstudiostask1.data.repository.PostRepository
import com.example.wharfstreetstudiostask1.data.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(
        impl: PostRepositoryImpl
    ): PostRepository
}
